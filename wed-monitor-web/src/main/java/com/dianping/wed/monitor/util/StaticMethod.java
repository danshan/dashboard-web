package com.dianping.wed.monitor.util;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.combiz.support.staticfile.StaticFile;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author honghao.shan
 * @since 2014-06-09 6:03 PM
 */
public class StaticMethod {
	
    public static String escapeJavaScript(String str) {
        return StringEscapeUtils.escapeJavaScript(str);
    }

    public static String getLionConfig(String key) {
        return LionConfigUtils.getProperty(key, "");
    }

    public static String include(String url) {
        return StaticFile.include(url);
    }

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
