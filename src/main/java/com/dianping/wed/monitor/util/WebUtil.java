package com.dianping.wed.monitor.util;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.wed.monitor.enums.CookieName;
import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

public class WebUtil {

	public static void addCookie(CookieName cookieName, String value, int age){
		addCookie(ServletActionContext.getRequest(), ServletActionContext.getResponse(), cookieName, value, age);
	}
    public static void removeCookie(CookieName cookieName){
        addCookie(ServletActionContext.getRequest(),ServletActionContext.getResponse(),cookieName,"",0);
    }

    //cookie domain 配置到lion
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, CookieName cookieName, String value, int age){
        String cookieDomain = LionConfigUtils.getProperty("mobile-wap-m-web.cookie-domain");
//		String[] parts = request.getServerName().split("\\u002E", 2);
//		if(parts != null && parts.length == 2) {
			Cookie cookie = new Cookie(cookieName.getName(), cookieName.needBase64() ? base64Encode(value) : value);
			cookie.setDomain(cookieDomain);
			cookie.setMaxAge(age);
			cookie.setPath("/");
			response.addCookie(cookie);
//		}
	}
	
	public static String getCookie(CookieName cookieName){
		return getCookie(ServletActionContext.getRequest(), cookieName);
	}
	
	public static String getCookie(HttpServletRequest request, CookieName cookieName){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookieName.getName().equals(cookie.getName())){
					return cookieName.needBase64() ? base64Decode(cookie.getValue()) : cookie.getValue();
				}
			}
		}
		return null;
	}
	/**
	 * 解析URL，并自动吧params拼接在url后面，设计用来输出redir
	 *
	 * @param url		编码过的链接
	 * @param params	要拼接在后面的参数，可以为0~N个，样式类似于:x=1
	 * @return			拼接后解码过的URL
	 */
	public static String decodeUrl(String url, String...params){
		StringBuffer originalUrl = new StringBuffer(URLDecoder.decode(url));
		if(params != null && params.length > 0){
			boolean hasParam = false;
			if(originalUrl.indexOf("?") != -1){
				hasParam = true;
			}
			for(int x = 0; x < params.length; x++){
				if(x == 0 && !hasParam){
					originalUrl.append("?");
					originalUrl.append(params[x]);
				}else{
					originalUrl.append("&");
					originalUrl.append(params[x]);
				}
			}
		}
		return originalUrl.toString();
	}
	/**
	 * 简单过滤输入内容中的SQL、脚本
	 * @param before
	 */
	public static String xssFilter(String before){
		if(before != null){
			before = before.replaceAll("[<]", "＜");
			before = before.replaceAll("[>]", "＞");
			before = before.replaceAll("[(]", "（");
			before = before.replaceAll("[)]", "）");
			before = before.replaceAll("[\"]", "“");
			before = before.replaceAll("[\']", "’");
		}
		return before;
	}

	private static String base64Encode(String str) {
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	private static String base64Decode(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}


}
