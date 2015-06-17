package com.dianping.wed.monitor.enums;

/**
 * 移动M站使用到的cookie名称枚举
 * @author xiaozhe.li
 *
 */
public enum CookieName {
    CITY_ID("cityid", false),
    CITY_PINYIN("citypinyin", false),
    CITY_NAME("cityname", true),
    LOGIN_USER_NAME("ua", false),
    LOCAL_LNG("locallng", false),
    LOCAL_LAT("locallat", false),
    VISIT_HISTORY_SHOP_IDS("wapvisithistory", false),
    WIN_WIDTH("winwidth",false),
    WIN_HEIGHT("winheight",false),
    SOURCE("source", false),
    HC_V("_hc.v", false),
    J_SESSION_ID("JSESSIONID", false),
    SAMSUNG_PHONE("samsung_phone", false),
    SAMSUNG_TOKEN("samsung_token", false),
    SAMSUNG_CHANCE("samsung_chance", false),
    SAMSUNG_STATUS("samsung_status", false),
    SAMSUNG_GAIN("samsung_gain", false),
    /**
     * 会记录中文，要base64编码
     */
    PAGE_VISIT_HISTORY("pvhistory", true),
    VISIT_FLAG("visitflag", false),
    NO_CITY_SWITCH("noswitchcity", false),
    CSRF_TOKEN("csrftoken", true),
    DOWNLOAD_BANNER("download_banner", false),

    /**
     * 微信相关
     * */
    WEIXIN_ACCESS_TOKEN("wx_atoken",false),
    WEIXIN_REFRESH_TOKEN("wx_rtoken",false),
    /**
     * shopshare相关
     */
    PLACE("m_place",false),
    /**
     * source
     */
    UTM_SOURCE("m_source",false),
    /**
     * homeFlash
     */
    FLASH("m_flash",false),
    FLASH_IN_SESSION("m_flash_s",false);


    private String name;

    private boolean needBase64;

    private CookieName(String cookieName, boolean needBase64){
        this.name = cookieName;
        this.needBase64 = needBase64;
    }

    public String getName(){
        return name;
    }

    public boolean needBase64(){
        return needBase64;
    }
}