/**
 * Project: shop-web
 * 
 * File Created at 2011-9-21
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

import com.dianping.avatar.exception.ApplicationException;
import com.dianping.combiz.config.DictionaryCategories;
import com.dianping.combiz.util.DictConfigUtil;

/**
 * TODO Comment of ProjectException
 * @author xu.mao
 *
 */
public class ProjectException extends ApplicationException {

	private static final long serialVersionUID = 594071814789816510L;

	public ProjectException() {
		super();
	}

	private String message;	
	private String title;

	public ProjectException(String message) {
		this.message = message;
	}
	public ProjectException(String titleId,String messageId) {
		this.title = DictConfigUtil.getStringValue(DictionaryCategories.Exception_Message, titleId);
		this.message = DictConfigUtil.getStringValue(DictionaryCategories.Exception_Message, messageId);
	}

	
	public void setMessage(String message) {
		this.message = message;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public String getTitle() {
		return title;
	}
	
}
