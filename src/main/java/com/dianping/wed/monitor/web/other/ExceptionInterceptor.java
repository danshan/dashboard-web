/**
 * 
 */
package com.dianping.wed.monitor.web.other;

import com.dianping.w3c.pagelet.InvocationContext;
import com.dianping.w3c.pagelet.Pagelet;
import com.dianping.w3c.pagelet.interceptor.Interceptor;
import org.apache.log4j.Logger;

/**
 * @author cong.yu
 *
 */
public class ExceptionInterceptor implements Interceptor {

    private Logger logger = Logger.getLogger(ExceptionInterceptor.class);

    @Override
    public String intercept(InvocationContext invocation) throws Exception {
        String result = Pagelet.NONE;
        try {
            result = invocation.invoke();
        } catch (Throwable exception) {
            logger.error("exception thrown by pagelet:"+invocation.getPagelet(),exception);
        }
        return result;
    }

}