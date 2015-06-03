/**
 *
 * File Created at 14-4-25
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
package com.dianping.wed.monitor.web.action;

import com.dianping.avatar.log.AvatarLogger;
import com.dianping.avatar.log.AvatarLoggerFactory;
import com.dianping.avatar.util.WebUtils;
import com.dianping.combiz.service.CityService;
import com.dianping.combiz.spring.context.SpringLocator;
import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.combiz.util.CodeConstants;
import com.dianping.combiz.util.HtmlFormater;
import com.dianping.combiz.util.LoginUtils;
import com.dianping.combiz.web.Constants;
import com.dianping.combiz.web.tag.common.TagNamespace;
import com.dianping.shopremote.remote.ShopService;
import com.dianping.shopremote.remote.dto.ShopDTO;
import com.dianping.shopremote.remote.enums.ShopTypeEnum;
import com.dianping.userremote.base.dto.UserDTO;
import com.dianping.userremote.base.service.UserService;
import com.dianping.w3c.pagelet.ExecutionManager;
import com.dianping.w3c.pagelet.ExecutionManagerFactory;
import com.dianping.w3c.pagelet.ExecutionManagerType;
import com.dianping.w3c.pagelet.template.freemarker.ModelRegistrarUtils;
import com.dianping.wed.monitor.exception.PageNotFoundException;
import com.dianping.wed.monitor.util.StaticMethod;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bo.lv
 */
public abstract class BaseAction extends ActionSupport implements Preparable, ServletRequestAware, RequestAware {

    protected static final AvatarLogger logger = AvatarLoggerFactory.getLogger(BaseAction.class);

    private static final long serialVersionUID = 76979809012906220L;

    private static final String PAGE_TRACKER_PREFIX = "pageTracker._trackPageview(\"";

    private static final String PAGE_TRACKER_SUFFIX = "\");";

    /**丽人-个人写真切换成结婚粉色页头*/
    private static final int PERSONAL_PHOTO_CATEGORYID = 6700;

    private HttpServletRequest request;

    protected Map<String, Object> requestMap = new HashMap<String, Object>();

    @Resource
    protected UserService userService;
    @Resource
    protected CityService cityService;
    @Resource
    protected ShopService shopService;
    @Getter
    protected ExecutionManager executionManager;

    protected ExecutionManagerFactory executionManagerFactory;

    public void setExecutionManagerFactory(ExecutionManagerFactory executionManagerFactory) {
        this.executionManagerFactory = executionManagerFactory;
    }


    public ExecutionManager getExecutionManager() {
        return executionManager;
    }


    @Override
    public void setRequest(Map<String, Object> request) {
        this.requestMap = request;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void prepare() throws Exception {
        beforePrepare();
        doValidate();
        doPrepare();
        afterPrepare();
    }

    @Override
    public String execute() throws Exception {
        beforeExecute();
        String result = doExecute();
        afterExecute();
        return result;
    }

    /**
     * 执行操作
     *
     * @return
     * @throws Exception
     */
    protected abstract String doExecute() throws Exception;

    /**
     * 校验
     */
    protected abstract void doValidate() throws Exception;

    /**
     * 预处理
     */
    protected abstract void doPrepare() throws Exception;

    /**
     * 执行之后
     */
    private void afterExecute() {
        addStaticModels();
    }

    /**
     * 执行之前
     */
    private void beforeExecute() {
        if (request != null) request.setAttribute("Header_Gbase_Css", "/s/c/app/main/base-old.css");
    }

    /**
     * 预处理之后
     */
    private void afterPrepare() {

    }

    /**
     * 预处理之前
     */
    private void beforePrepare() {
        request.setAttribute("Header_WeddingStyle", true);
        request.setAttribute("CommonJsType", "neuron");
    }

    /**
     * @param trackString
     */
    public void appendPageTracker(String trackString) {
        if (StringUtils.isEmpty(trackString)) {
            return;
        }

        List<String> trackStrings = new ArrayList<String>();

        trackStrings.add(trackString);
        appendPageTrackers(trackStrings);
    }

    /**
     * 设置多个PageTracker
     *
     * @param trackStrings
     */
    public void appendPageTrackers(List<String> trackStrings) {
        if (trackStrings == null)
            return;
        String pageTrackers = "";
        for (String trackString : trackStrings) {
            pageTrackers += PAGE_TRACKER_PREFIX + trackString + PAGE_TRACKER_SUFFIX;
        }
        String ga = (String) request.getAttribute("MoreUrchinTracker");
        if (ga != null && ga.length() > 0) {
            pageTrackers = pageTrackers + ga;
        }

        request.setAttribute("MoreUrchinTracker", pageTrackers);
    }

    /**
     * 设置商户的类型，页面头部会使用
     *
     * @param shopTypeValue
     */
    public void setShopTypeValue(int shopTypeValue) {
        for (ShopTypeEnum shopType : ShopTypeEnum.values()) {
            if (shopType.value == shopTypeValue) {
                setShopType(shopType);
                return;
            }
        }
    }

    /**
     * 设置商户的类型，页面头部会使用
     * @param shopTypeValue
     * @param mainCategoryId
     */
    public void setShopTypeValue(int shopTypeValue, Integer mainCategoryId) {
        //丽人-个性写真分类商户设置粉色页头式样
        if(mainCategoryId != null && mainCategoryId == PERSONAL_PHOTO_CATEGORYID){
            if (request != null) request.setAttribute("Header_WeddingStyle", true);
        }
        for (ShopTypeEnum shopType : ShopTypeEnum.values()) {
            if (shopType.value == shopTypeValue) {
                setShopType(shopType);
                return;
            }
        }
    }

    /**
     * 设置当前频道
     *
     * @param channelType
     */
    public void setCurrentChannel(CodeConstants.ChannelType channelType) {
        if (request == null) return;
        request.setAttribute(TagNamespace.PAGE_CHANNEL, channelType);
        request.setAttribute("Header_ChannelType", channelType.value);
    }

    /**
     * 加载商铺信息
     * @param shopId
     * @return
     */
    protected ShopDTO loadShop(int shopId) throws PageNotFoundException {
        if (shopId == 0) {
            throw new PageNotFoundException("Error.message.product.invalid.title", "Error.message.product.invalid");
        }
        ShopDTO shopDto = shopService.loadShop(shopId);
        if (shopDto == null || shopDto.getShopId() < 0) {
            throw new PageNotFoundException("Error.message.album.nullShop.title", "Error.message.album.nullShop");
        }
        return shopDto;
    }

    /**
     * 设置商户类型
     *
     * @param shopType
     */
    public void setShopType(ShopTypeEnum shopType) {
        if (request != null) request.setAttribute(TagNamespace.SHOP_TYPE, shopType);
        if (request != null) request.setAttribute("Header_ShopType", shopType.value);
    }

    /**
     * 设置页头类型
     *
     * @param headType
     */
    public void setHeadType(CodeConstants.HeadType headType) {
        if (request != null) request.setAttribute(TagNamespace.HEAD_TYPE, headType);
        setHeadType(headType.toString());
    }

    /**
     * 设置页头类型
     *
     * @param headType
     */
    public void setHeadType(String headType) {
        if (request != null) request.setAttribute("Header_HeadType", headType);
    }

    public void setHeadSearchTrack(String trackName) {
        requestMap.put("searchBoxGA", trackName);
    }

    /**
     * 设置cityId,供头部切换城市的显示使用 显示当前商户所在的城市
     *
     * @param cityId 城市的id
     */
    public void setCityId(int cityId) {
        cityService = SpringLocator.getBean("cityService");
        int cookieCityId = getCityId();
        int relateType = cityService.getCityRelation(cityId, cookieCityId);
        if (relateType == 1) {
            cityId = cookieCityId;
        }
        requestMap.put(TagNamespace.PAGE_CITY, String.valueOf(cityId));
    }

    /**
     * 获取cookies中的城市id
     *
     * @return
     */
    protected int getCityId() {
        String cityId = getCookieValue("cy");
        if (cityId == null)
            return 1;
        try {
            return Integer.parseInt(cityId);
        } catch (IllegalArgumentException e) {
            return 1;
        }
    }

    protected int getDpID() {
        String dpID = getCookieValue("_hc.v");
        if (dpID == null)
            return 1;
        try{
            return Integer.parseInt(dpID);
        } catch (IllegalArgumentException e) {
            return 1;
        }
    }

    public String getCookieValue(String cookieName) {
        return WebUtils.getCookieValue(ServletActionContext.getRequest(), cookieName);
    }

    /**
     * 获取UserId，未登陆为0
     *
     * @return
     */
    public int getUserId() {
        String userId = null;
        if (request != null) userId = (String) request.getAttribute(Constants.BIZ_CONTEXT_USER_ID);
        return StringUtils.isEmpty(userId) ? 0 : Integer.parseInt(userId);
    }

    protected String getUserIp() {
        return LoginUtils.getUserIP(request);
    }

    /**
     * 得到客户端IP
     *
     * @return
     */
    protected String getRemoteIp() {
        String userIp = LoginUtils.getUserIP(request);
        if (StringUtils.isEmpty(userIp)) {
            return StringUtils.EMPTY;
        }
        int length = userIp.length();
        if (length > 16) {
            length = 16;
        }
        return userIp.substring(0, length);
    }

    /**
     * 设置request相关参数
     *
     * @param name
     * @param key
     */
    public void setAttribute(String name, Object key) {
        requestMap.put(name, key);
    }

    /**
     * 从cookie中获取用户信息,如果cookie中无,则返回null
     *
     * @return
     */
    public UserDTO loadUserInfo() {
        int userId = this.getUserId();
        if (userId <= 0) {
            return null;
        } else {
            UserDTO user = userService.loadUser(userId);
            return user;
        }
    }

    public UserDTO loadUser(int userId) {
        if (userId <= 0) {
            return null;
        } else {
            UserDTO user = userService.loadUser(userId);
            return user;
        }
    }

    /**
     * 是否是post请求
     *
     * @return
     */
    public boolean isPostMethod() {
        String method = request.getMethod();
        if (method != null) {
            return "POST".equalsIgnoreCase(method);
        }
        return false;
    }

    /**
     * 是否是主站的refer
     *
     * @return
     */
    public boolean isReferValidate() {
        String referer = null;
        if (request != null) {
            referer = request.getHeader("Referer");
            if (referer == null) {
                return false;
            }
        }

        String permittedRefers = LionConfigUtils.getProperty("shop-web.server.domain");
        if (permittedRefers != null && !permittedRefers.equals("")) {
            String[] permittedRefer = permittedRefers.split(",");

            for (String item : permittedRefer) {
                if (referer.contains(item)) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }

    /**
     * 设置js框架类型为neuron
     */
    public void useNeuronJsType() {
        requestMap.put("CommonJsType", "neuron");
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected String getSessionId() {
        return request == null ? null : request.getSession().getId();
    }

    protected ExecutionManager loadExecutionManager() {
        String s = LionConfigUtils.getProperty("shop-web.pagelet.concurrent.switch", "n");
        if (StringUtils.isEmpty(s)) {
            return executionManagerFactory.getExecutionManager(ExecutionManagerType.DEFAULT);
        }
        if (s.equals("y")) {
            return executionManagerFactory.getExecutionManager(ExecutionManagerType.CONCURRENT);
        }

        return executionManagerFactory.getExecutionManager(ExecutionManagerType.DEFAULT);
    }

    /**
     * 从cookie中得到cookieId
     * @return 如果cookie中无,则返回0
     */
    public int getCookieUserId(){
        String userId = (String) ServletActionContext.getRequest().getAttribute(Constants.BIZ_CONTEXT_USER_ID);
        return StringUtils.isEmpty(userId) ? 0 : Integer.parseInt(userId);
    }

    /**
     * 添加工具类
     */
    private void addStaticModels() {
        // 添加Html的工具处理类,添加商户的工具类,分页导航控件类 & 图片实体处理类
        Class<?>[] clazzs = new Class[] { HtmlFormater.class, StaticMethod.class};
        Map<String,Object> contextMap = ServletActionContext.getContext().getContextMap();
        for (Class<?> clazz : clazzs) {
            ModelRegistrarUtils.useClass(contextMap, clazz);
        }
    }
    
    protected int getHippoPageId () {
    	if (this.requestMap == null || this.requestMap.size() <= 0) {
    		return 0;
    	}
    	
    	Object hippoPageId = this.requestMap.get("hippoPageID");
    	
    	if (hippoPageId == null || !NumberUtils.isNumber(hippoPageId.toString())) {
    		return 0;
    	}
    	
    	return NumberUtils.toInt(hippoPageId.toString());
    		
    }

}

