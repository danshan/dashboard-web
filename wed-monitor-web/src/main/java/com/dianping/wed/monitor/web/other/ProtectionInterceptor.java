package com.dianping.wed.monitor.web.other;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.mailremote.remote.MailService;
import com.dianping.w3c.pagelet.InvocationContext;
import com.dianping.w3c.pagelet.Pagelet;
import com.dianping.w3c.pagelet.interceptor.Interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ltebean
 */
public class ProtectionInterceptor implements Interceptor {

    private MailService mailService;

    private final ConcurrentMap<String,AtomicInteger> counter=new ConcurrentHashMap<String,AtomicInteger>();
    @Override
    public String intercept(InvocationContext invocation) throws Exception {
        int maxCount=Integer.parseInt(LionConfigUtils.getProperty("shop-web.protection.maxErrorCount", "30"));
        if(LionConfigUtils.getProperty("shop-web.protection.switch","n").equals("n")){
             return invocation.invoke();
        }

        String templateKey=invocation.getTemplate().getTemplateMeta().getTemplateKey();
        if(counter.get(templateKey)==null){
            counter.putIfAbsent(templateKey,new AtomicInteger(0));
        }
        AtomicInteger errorCount=counter.get(templateKey);

        //reset if needed
        if(LionConfigUtils.getProperty("shop-web.protection.reset","n").equals("y")){
            errorCount.set(0);
            counter.put(templateKey,errorCount);
        }

        //check if need to bypass
        if(errorCount.get()>maxCount){
            return Pagelet.NONE;
        }

        try {
            String resultCode=invocation.invoke();
            errorCount.set(0);
            counter.put(templateKey,errorCount);
            return resultCode;
        }catch (Exception e){
            errorCount.getAndIncrement();
            if(errorCount.get()>maxCount){
                sendMail(templateKey+"被关闭:"+e.getMessage());
            }
            counter.put(templateKey,errorCount);
            throw e;
        }
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    private void sendMail(String content){
        Map<String,String> map=new HashMap<String, String>();
        map.put("title",content);
        mailService.send(880,"w3c-101@dianping.com",map) ;

    }
}
