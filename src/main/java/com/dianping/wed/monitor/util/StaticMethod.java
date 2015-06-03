package com.dianping.wed.monitor.util;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.combiz.support.staticfile.StaticFile;
import com.dianping.piccentercloud.display.api.ImageFile;
import com.dianping.piccentercloud.display.api.PictureVisitAdapter;
import com.dianping.piccentercloud.display.api.PictureVisitMode;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author honghao.shan
 * @since 2014-06-09 6:03 PM
 */
public class StaticMethod {
	
	private static final Map<String,String> filterMap = new HashMap<String,String>();
	
	static {
		filterMap.put("00000", "00000");
		filterMap.put("11111", "11111");
		filterMap.put("22222", "22222");
		filterMap.put("33333", "33333");
		filterMap.put("44444", "44444");
		filterMap.put("55555", "55555");
		filterMap.put("66666", "66666");
		filterMap.put("77777", "77777");
		filterMap.put("88888", "88888");
		filterMap.put("99999", "99999");
	};

    public static String getProductPicUrl(String url, int width, int height, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(url)) {
            int index = url.indexOf("pc");
            if (index == -1) {
                //老图片处理
                return getWedPicUrl(url, 630, 380, request);
            }
            url = url.substring(0, index + 2) + "/wed" + url.substring(index + 2);
            return ImageFile.getInstance().include(PictureVisitAdapter.formatUrl(url, width, height, PictureVisitMode.CUT), request);
        }
        return "";
    }

    /** 竖版产品图 */
    public static String getPcProductPicUrl(String url, int width,int height,HttpServletRequest request) {
        return ImageFile.getInstance().include(PictureVisitAdapter.formatUrl(url, width, height, PictureVisitMode.CUT), request);
    }

    public static String escapeJavaScript(String str) {
        return StringEscapeUtils.escapeJavaScript(str);
    }

    public static String getLionConfig(String key) {
        return LionConfigUtils.getProperty(key, "");
    }

    public static String include(String url) {
        return StaticFile.include(url);
    }

    /**
     * 使用cut方式裁剪图片
     *
     * @return
     */
    public static String getWedPicUrl(String url, int width, int height, HttpServletRequest request) {
        return ImageFile.getInstance().include(PictureVisitAdapter.formatUrl(url, width, height, PictureVisitMode.CUT), request);
    }

    public static String getWedOfficialPicUrl(String url, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(url)) {
            int index = url.indexOf("pc");
            if (index == -1) {
                //老图片处理
                return getWedPicUrl(url, 740, 1024, request);
            }
            url = url.substring(0, index + 2) + "/wed" + url.substring(index + 2);
            PictureVisitAdapter adapter = new PictureVisitAdapter(url, 740, 2048, PictureVisitMode.SCALE);
            adapter.putOption("logo", "0");
            String formattedUrl = adapter.getFormatUrlWithOptions("721c8f7aa2d74f4d783ea3f7acdefb20");
            return ImageFile.getInstance().include(formattedUrl, request);
        }
        return "";
    }

    public static String getWedPicUrl(String url,int width, int height) {
        return PictureVisitAdapter.formatUrl(url, width, height);
    }

    public static String getWedOfficialPicUrl(String url) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(url)) {
            int index = url.indexOf("pc");
            if (index == -1) {
                //老图片处理
                return getWedPicUrl(url, 740, 1024);
            }
            url = url.substring(0, index + 2) + "/wed" + url.substring(index + 2);
            PictureVisitAdapter adapter = new PictureVisitAdapter(url, 740, 2048, PictureVisitMode.SCALE);
            adapter.putOption("logo", "0");
            String formattedUrl = adapter.getFormatUrlWithOptions("721c8f7aa2d74f4d783ea3f7acdefb20");
            return formattedUrl;
        }
        return "";
    }

    /**
     * 获取等比缩放后图片的真实高度
     *
     * @param sourceWidth
     *            原图宽
     * @param sourceHeight
     *            原图高
     * @param targetW
     *            等比缩放尺寸的宽
     * @param targetH
     *            等比缩放尺寸的高
     * @return
     */
    public static int getResizeHeight(int sourceWidth, int sourceHeight, int targetW, int targetH) {
        double sx = (double) targetW / sourceWidth;
        double sy = (double) targetH / sourceHeight;
        if (sx < sy) {
            sy = sx;
        } else {
            sx = sy;
        }
        return (int) (sy * sourceHeight);
    }

    /**
     * 获取等比缩放后图片的真实高度
     *
     * @param sourceWidth
     *            原图宽
     * @param sourceHeight
     *            原图高
     * @param targetW
     *            等比缩放尺寸的宽
     * @param targetH
     *            等比缩放尺寸的高
     * @return
     */
    public static int getResizeWidth(int sourceWidth, int sourceHeight, int targetW, int targetH) {
        double sx = (double) targetW / sourceWidth;
        double sy = (double) targetH / sourceHeight;
        if (sx < sy) {
            sy = sx;
        } else {
            sx = sy;
        }
        return (int) (sy * sourceWidth);
    }

    public static boolean isNowYear(Date date) {
        Date now = new Date();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        cal2.setTime(now);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }
    
    /**
     * 用于判断手机号段是否合法
     * @param num
     * @return
     */
    public static boolean isMobileNum(String num){
    	if(StringUtils.isBlank(num)){
    		return false;
    	}
        Pattern p = Pattern.compile("^(1[3,4,5,7,8][0-9])\\d{8}$");
        Matcher m = p.matcher(num);
        if(!m.matches()){
        	return false;
        }
        for(Entry<String, String> entry:filterMap.entrySet()){
        	if(num.indexOf(entry.getKey()) != -1){
        		return false;
        	}
        }
        return true;
    }
    
    /**
     * 获取商户星级描述
     *
     * @param shopPower
     * @return
     */
    public static String getShopPowerTitle(int shopPower) {
        String title = "";
        switch (shopPower) {
            case 0:
                title = "该商户暂无星级";
                break;
            case 10:
                title = "一星商户";
                break;
            case 20:
                title = "二星商户";
                break;
            case 30:
                title = "三星商户";
                break;
            case 35:
                title = "准四星商户";
                break;
            case 40:
                title = "四星商户";
                break;
            case 45:
                title = "准五星商户";
                break;
            case 50:
                title = "五星商户";
                break;
        }
        return title;
    }
    
}
