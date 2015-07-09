package com.dianping.wed.monitor.web.action;

import java.util.HashMap;
import java.util.Map;

public abstract class AjaxBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	protected final int CODE_SUCCESS = 200;
	protected final int CODE_ERROR = 500;
	
	private int code = CODE_SUCCESS;
	
	private Map<String, Object> msg = new HashMap<String, Object>();
	
	protected String message;
	
	@Override
	protected String doExecute() throws Exception {
		
		try {
			code = doAjaxExecute(msg);
			msg.put("string", message);
		} catch (Exception ex) {
			code = CODE_ERROR;
			logger.error("==== ajax error ====", ex);
			if (ex instanceof IllegalArgumentException) {
				msg.put("message", ex.getMessage());
			} else {
				msg.put("message", "处理出错，请稍候再试");
			}
		}
		
		return SUCCESS;
	}
	
	protected abstract int doAjaxExecute(Map<String, Object> result) throws Exception;
	
	@Override
	protected void doValidate() throws Exception { }

	@Override
	protected void doPrepare() throws Exception { }
	
	public int getCode() {
		return code;
	}

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
