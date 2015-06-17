package com.dianping.wed.monitor.util;

import com.dianping.dpsf.async.ServiceFuture;

/**
 * Created by guyujie on 14-6-10.
 */
public class AsynUtils {

    @SuppressWarnings("unchecked")
    public static <T> T getValue(ServiceFuture future) {
        try {
            return (T)future._get();
        } catch (Exception ex) {
            return null;
        }
    }
}
