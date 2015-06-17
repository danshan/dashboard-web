/**
 *
 * File Created at 14-6-5
 *
 * Copyright 2014 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.wed.monitor.service.impl;

import com.dianping.combiz.entity.URLRewriteRule;
import com.dianping.combiz.service.impl.URLRewriteRuleServiceImpl;
import com.dianping.shopbusinessremote.remote.URLRewriteWeddingService;
import com.dianping.shopbusinessremote.remote.dto.WeddingURLDTO;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bo.lv
 */
public class WeddingURLRewriteRuleServiceImpl extends URLRewriteRuleServiceImpl {

	@Setter
	private URLRewriteWeddingService urlRewriteWeddingService;

	/**
	 * 根据context获取相应的url rule的list
	 *
	 * @param context
	 * @return
	 */
	@Override
	public List<URLRewriteRule> getByContext(String context) {
		List<WeddingURLDTO> weddingURLDTOList = urlRewriteWeddingService.findAllRewriteWeddingURLbyContext(context);
		List<URLRewriteRule> urlRewriteRules = new ArrayList<URLRewriteRule>();
		if (CollectionUtils.isNotEmpty(weddingURLDTOList)) {
			for (WeddingURLDTO weddingURLDTO : weddingURLDTOList) {
				URLRewriteRule urlRewriteRule = new URLRewriteRule();
				BeanUtils.copyProperties(weddingURLDTO, urlRewriteRule);
				urlRewriteRules.add(urlRewriteRule);
			}
		}
		return urlRewriteRules;
	}
}
