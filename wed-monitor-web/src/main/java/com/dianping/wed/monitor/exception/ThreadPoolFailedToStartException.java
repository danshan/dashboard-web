package com.dianping.wed.monitor.exception;

/**
 * Created with IntelliJ IDEA.
 * Author: liangjun.zhong
 * Date: 13-8-11
 * Time: AM2:20
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPoolFailedToStartException extends Exception{

    private static final long serialVersionUID = -2140062640554306689L;

    public ThreadPoolFailedToStartException() {
    }

    /**
     * @param message
     */
    public ThreadPoolFailedToStartException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ThreadPoolFailedToStartException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ThreadPoolFailedToStartException(String message, Throwable cause) {
        super(message, cause);
    }
}
