/**
 *
 * File Created at 15/1/20
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

import org.apache.commons.lang.StringUtils;

/**
 * @author kewen.yao
 */
public class VersionUtil {

	private VersionUtil() {
	}

	/**
	 * 检验version 是否合法
	 *
	 * @param version
	 * @return
	 */
	public static boolean validate(String version) {
		if (version == null || version.length() == 0) {
			return false;
		}
		String[] elements = StringUtils.split(version, ".");
		//version.split("\\.");
		for (String s : elements) {
			try {
				int i = Integer.parseInt(s);
				if (i < 0) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * return 1,versionA > versionB
	 * return 0,versionA = versionB
	 * return -1,versionA < versionB
	 *
	 * @param versionA
	 * @param versionB
	 * @return
	 */
	public static int compare(String versionA, String versionB) {
		if (validate(versionA) && validate(versionB)) {
			int[] versionASplit = strToArray(versionA);
			int[] versionBSplit = strToArray(versionB);
			if (versionASplit.length != versionBSplit.length) {
				if (versionASplit.length > versionBSplit.length) {
					for (int l = 0; l < versionBSplit.length; l++) {
						if (versionASplit[l] > versionBSplit[l]) {
							return 1;
						} else if (versionASplit[l] < versionBSplit[l]) {
							return -1;
						} else {
							continue;
						}
					}
					for (int i = versionASplit.length - 1; i >= versionBSplit.length; i--) {
						if (versionASplit[i] != 0) {
							return 1;
						}
					}
					return 0;
				} else {
					for (int h = 0; h < versionASplit.length; h++) {
						if (versionASplit[h] > versionBSplit[h]) {
							return 1;
						} else if (versionASplit[h] < versionBSplit[h]) {
							return -1;
						} else {
							continue;
						}
					}
					for (int j = versionBSplit.length - 1; j >= versionASplit.length; j--) {
						if (versionBSplit[j] != 0) {
							return -1;
						}
					}
					return 0;
				}
			} else {
				for (int k = 0; k < versionASplit.length; k++) {
					if (versionASplit[k] > versionBSplit[k]) {
						return 1;
					} else if (versionASplit[k] < versionBSplit[k]) {
						return -1;
					} else {
						continue;
					}
				}
				return 0;
			}
		} else if (!validate(versionA) && !validate(versionB)) {
			return 0;
		} else if (!validate(versionA)) {
			return -1;
		} else {
			return 1;
		}
	}

	private static int[] strToArray(String version) {
		String[] s = StringUtils.split(version, ".");
		int[] versionNum = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			int num = Integer.parseInt(s[i]);
			versionNum[i] = num;

		}
		return versionNum;
	}
}
