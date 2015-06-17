/**
 * 
 */
package com.dianping.wed.monitor.web.other;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.w3c.pagelet.InvocationContext;
import com.dianping.w3c.pagelet.Pagelet;
import com.dianping.w3c.pagelet.interceptor.Interceptor;

/**
 * @author cong.yu
 *
 */
public class ExclusionInterceptor implements Interceptor {

    /* (non-Javadoc)
     * @see com.dianping.w3c.pagelet.interceptor.Interceptor#intercept(com.dianping.w3c.pagelet.InvocationContext)
     */
    @Override
    public String intercept(InvocationContext invocation) throws Exception {
        String exclusionList = LionConfigUtils.getProperty("shop-web.pagelet.exclusion", "");
        String[] list=exclusionList.split("\\|");
        for(String templateKey:list) {
            if(invocation.getTemplate().getTemplateMeta().getTemplateKey().equals(templateKey)) {
                return Pagelet.NONE;
            }
        }
        String resultCode = invocation.invoke();
        return resultCode;
    }

}
