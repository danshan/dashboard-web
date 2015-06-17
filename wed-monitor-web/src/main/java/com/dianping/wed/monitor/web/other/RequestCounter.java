/**
 * 
 */
package com.dianping.wed.monitor.web.other;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cong.yu
 *
 */
public class RequestCounter {
    
    private static AtomicInteger counter=new AtomicInteger(0);
    
    private static AtomicBoolean needsCount=new AtomicBoolean(true);
    
    public static boolean isAboveCount() {
        if(needsCount.get()) {
            if(counter.getAndIncrement()<10000) {
                return false;
            }else {
                needsCount.set(false);
                return true;
            }
        }
        return true;
    }

}
