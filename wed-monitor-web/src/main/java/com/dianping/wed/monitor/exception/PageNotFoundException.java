/**
 * Project: shop-web
 * 
 * File Created at 2011-11-28
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.wed.monitor.exception;

import org.apache.http.HttpStatus;
import org.apache.struts2.ServletActionContext;

/**
 * PageNotFoundException
 * @author mingxing.ma
 *
 */
public class PageNotFoundException extends ProjectException {

	private static final long serialVersionUID = -8154888345183193581L;

	public PageNotFoundException() {
		super();
	}

	/**
	 * @param titleId
	 * @param messageId
	 */
	public PageNotFoundException(String titleId, String messageId) {
		super(titleId, messageId);
		if (ServletActionContext.getResponse() != null) {
			ServletActionContext.getResponse().setStatus(HttpStatus.SC_NOT_FOUND);
		}
		
	}

	/**
	 * @param message
	 */
	public PageNotFoundException(String message) {
		super(message);
	}

	
}
