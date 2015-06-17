/**
 * 
 */
package com.dianping.wed.monitor.web.other;

import com.dianping.w3c.pagelet.InvocationContext;
import com.dianping.w3c.pagelet.interceptor.Interceptor;

/**
 * @author cong.yu
 *
 */
public class ContextInterceptor implements Interceptor {

    /* (non-Javadoc)
     * @see com.dianping.w3c.pagelet.interceptor.Interceptor#intercept(com.dianping.w3c.pagelet.InvocationContext)
     */
    @Override
    public String intercept(InvocationContext invocation) throws Exception {
        if(invocation.getPagelet() instanceof ContextAware) {
            ContextAware contextAware=(ContextAware)invocation.getPagelet();
            contextAware.setContext(invocation.getContext());
        }
        return invocation.invoke();
    }

}
