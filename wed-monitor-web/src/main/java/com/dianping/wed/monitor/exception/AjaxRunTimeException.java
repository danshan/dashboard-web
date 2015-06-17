package com.dianping.wed.monitor.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;
import org.apache.struts2.ServletActionContext;

/**
 * @author honghao.shan
 * @since 2014-06-06 11:11 AM
 */
public class AjaxRunTimeException extends ProjectException {

    private static final long serialVersionUID = 2693511787952230465L;

    @Setter @Getter
    private int code;
    @Setter @Getter
    private String msg;

    public AjaxRunTimeException() {
        super();
    }

    /**
     * @param titleId
     * @param messageId
     */
    public AjaxRunTimeException(String titleId, String messageId) {
        super(titleId, messageId);
        if (ServletActionContext.getResponse() != null) {
            ServletActionContext.getResponse().setStatus(HttpStatus.SC_OK);
        }

    }

    /**
     * @param message
     */
    public AjaxRunTimeException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

}
