package com.dinaping.wed.monitor.common.util;

import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-05 16:48
 */
public class StringTemplateUtil {
    /**
     * 替换掉本网站模板文本中的变量${}
     *
     * @param text 要替换的文本全文
     * @param varname 变量名 $(name) name为变量名
     * @param value 替换的值
     * @return 替换后的文本 string
     */
    public static String replaceTemplateTag(String text, String varname, String value) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("${" + varname + "}", value);
    }

    /**
     * Replace template tag.
     *
     * @param text the text
     * @param map the map
     * @return the string
     */
    public static String replaceTemplateTag(String text, Map<String, String> map) {
        for (String o : map.keySet()) {
            String key = o;
            if (key != null && map.containsKey(key)) {
                text = replaceTemplateTag(text, key, map.get(key));
            }
        }
        return text;
    }
}
