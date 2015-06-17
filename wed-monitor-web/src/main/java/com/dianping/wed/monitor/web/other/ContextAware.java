/**
 * 
 */
package com.dianping.wed.monitor.web.other;

import java.util.Map;

/**
 * @author cong.yu
 *
 */
public interface ContextAware {
    
    public void setContext(Map<String, Object> context);

}
