/**
 *
 * File Created at 14-9-28
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
package com.dianping.wed.monitor.util;

/**
 * @author bo.lv
 */
public class DataFormatUtil {

	public static int parseInt(String str) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public static double parseDouble(String str){
		try {
			return Double.valueOf(str);
		} catch (Exception e) {
		}
		return 0.0;
	}
}
