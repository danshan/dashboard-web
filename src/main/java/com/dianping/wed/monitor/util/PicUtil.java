/**
 *
 * File Created at 14/10/31
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

import com.dianping.piccentercloud.display.api.ImageFile;
import com.dianping.piccentercloud.display.api.PictureVisitAdapter;
import com.dianping.piccentercloud.display.api.PictureVisitMode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bo.lv
 */
public class PicUtil {

	public static String getPicUrl(String url, int width, int height, PictureVisitMode mode, HttpServletRequest request) {
		return ImageFile.getInstance().include(PictureVisitAdapter.formatUrl(url, width, height, mode), request);
	}

	public static String getPicUrl(String url, int width, int height, PictureVisitMode mode) {
		return ImageFile.getInstance().include(PictureVisitAdapter.formatUrl(url, width, height, mode));
	}

	public static String getPicUrlByCut(String url, int width, int height, HttpServletRequest request) {
		return getPicUrl(url, width, height, PictureVisitMode.CUT, request);
	}

	public static String getPicUrlByCut(String url, int width, int height) {
		return getPicUrl(url, width, height, PictureVisitMode.CUT);
	}

	public static String getPicUrlByScale(String url, int width, int height, HttpServletRequest request) {
		return getPicUrl(url, width, height, PictureVisitMode.SCALE, request);
	}

	public static String getPicUrlByScale(String url, int width, int height) {
		return getPicUrl(url, width, height, PictureVisitMode.SCALE);
	}

}
