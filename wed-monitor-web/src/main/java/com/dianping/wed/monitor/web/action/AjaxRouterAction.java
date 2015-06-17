package com.dianping.wed.monitor.web.action;

import java.util.HashMap;
import java.util.Map;

public class AjaxRouterAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String pageName;
	
	private static Map<String, String> actionMap = new HashMap<String, String>();
	static {
		//非合作返佣商户 婚宴预约公共ajax
		actionMap.put("common_wedhotelbooking", "wedHotelBookingAjaxAction");
		//合作返佣商户 婚宴预约公共ajax 二级页面
		actionMap.put("common_wedhotelbookingnext", "wedHotelBookingNextAjaxAction");
		//合作返佣商户 婚宴预约公共jsonp 二级页面
		actionMap.put("common_wedhotelbookingnextjsonp", "wedHotelBookingNextJsonpAction");
		//推荐商户列表页，批量预约ajax
		actionMap.put("common_wedhotelbookingdoublenext", "wedHotelBookingDoubleNextAjaxAction");
		//推荐商户列表页，批量预约jsonp
		actionMap.put("common_wedhotelbookingdoublenextjsonp", "wedHotelBookingDoubleNextJsonpAction");
		
		
		//婚宴下载优惠一级页面
		actionMap.put("common_wedhotelshowpromotion", "wedHotelShowPromotionAjaxAction");
		//婚宴附近推荐ajax
		actionMap.put("common_wedhotelrecommend", "wedHotelRecommendAjaxAction");

		//结婚预约公共ajax
		actionMap.put("common_bookingshop","bookingShop");
		actionMap.put("common_bookinguser","bookingUser");

		//产品相关ajax
		actionMap.put("product_products","shopProductList");
		actionMap.put("product_moreproducts","moreProducts");
		actionMap.put("product_bookingproduct","bookingProduct");

        //婚宴模糊推荐相关ajax
        actionMap.put("fuzzy_region", "wedHotelFuzzyRecommendRegionAjaxAction");
        actionMap.put("fuzzy_weddinghotels", "wedHotelFuzzyRecommendAjaxAction");

		//mobile首页相关ajax
		actionMap.put("index_searchshop","searchIndexShop");
		
		//婚宴用户主动确认ajax
		actionMap.put("confirm", "wedHotelUserConfirmAjaxAction");
		//婚宴用户主动确认ajax pc端
		actionMap.put("userConfirm", "wedHotelUserConfirmJsonAction");

        //11月大促jsonp ajax
        actionMap.put("sendTicket","sendTicketSMSAction");
        actionMap.put("index_promote","promoteIndexAction");
        actionMap.put("guessPrice","guessPriceAction");

        //亲子大促
        actionMap.put("index_baby_promote","babyPromoteIndexAction");
        actionMap.put("RedBag_baby","babyRedBagAction");

        //验证码
        actionMap.put("validateCode","captcha");
        
        //商户推荐
        actionMap.put("shopRecommend", "shopRecommendAjaxAction");

        //内容抓取
        actionMap.put("crawlProduct_moreProducts","moreProducts");
		//用户召回计划红包
		actionMap.put("userCallback_redBag","redBag");
	}
	public String getActionName() {
		return actionMap.get(pageName);
	}
	
	private static Map<String, String> namespaceMap = new HashMap<String, String>();
	static {
		//非合作返佣商户 婚宴预约公共ajax
		namespaceMap.put("common_wedhotelbooking", "/wed/ajax");
		//合作返佣商户 婚宴预约公共ajax 二级页面
		namespaceMap.put("common_wedhotelbookingnext", "/wed/ajax");
		//合作返佣商户 婚宴预约公共jsonp 二级页面
		namespaceMap.put("common_wedhotelbookingnextjsonp", "/wed/ajax");
		//推荐商户列表页，批量预约ajax
		namespaceMap.put("common_wedhotelbookingdoublenext", "/wed/ajax");
		//推荐商户列表页，批量预约jsonp
		namespaceMap.put("common_wedhotelbookingdoublenextjsonp", "/wed/ajax");
		//婚宴下载优惠页面
		namespaceMap.put("common_wedhotelshowpromotion", "/wed/ajax");
		//婚宴附近推荐ajax
		namespaceMap.put("common_wedhotelrecommend", "/wed/ajax");

		//结婚预约公共ajax
		namespaceMap.put("common_bookingshop","/wed/ajax");
		namespaceMap.put("common_bookinguser","/wed/ajax");

		//产品相关ajax
		namespaceMap.put("product_products","/wed/ajax/product");
		namespaceMap.put("product_moreproducts","/wed/ajax/product");
		namespaceMap.put("product_bookingproduct","/wed/ajax/product");

        //婚宴模糊推荐相关ajax
        namespaceMap.put("fuzzy_region", "/wed/ajax/fuzzy");
        namespaceMap.put("fuzzy_weddinghotels", "/wed/ajax/fuzzy");

		//mobile首页相关ajax
		namespaceMap.put("index_searchshop","/wed/ajax/index");
		
		//婚宴用户主动确认ajax
		namespaceMap.put("confirm", "/wed/ajax");
		//婚宴用户主动确认ajax
		namespaceMap.put("userConfirm", "/wed/ajax");

        //11月大促 jsonp ajax
        namespaceMap.put("sendTicket","/wed/ajax/promote");
        namespaceMap.put("index_promote","/wed/ajax/promote");
        namespaceMap.put("guessPrice","/wed/ajax/promote");

        //亲子大促
        namespaceMap.put("index_baby_promote","/wed/ajax/promote");
        namespaceMap.put("RedBag_baby","/wed/ajax/promote");

        //验证码
        namespaceMap.put("validateCode","/wed/ajax/validateCode");
        
        //商户推荐
        namespaceMap.put("shopRecommend", "/wed/ajax");

        //内容抓取
        namespaceMap.put("crawlProduct_moreProducts","/wed/ajax/crawlProduct");
		//用户召回计划红包
		namespaceMap.put("userCallback_redBag","/wed/ajax/userCallback");
	}
	public String getNameSpace() {
		return namespaceMap.get(pageName);
	}
	
	private int code;
	private Map<String, Object> msg;
	
	@Override
	protected String doExecute() throws Exception {
		
		if (actionMap.containsKey(pageName))
			return SUCCESS;
		else {
			
			code = 500;
			msg = new HashMap<String, Object>();
			msg.put("string", "参数错误！");
			
			return ERROR;
		}
	}
	@Override
	protected void doValidate() throws Exception { }
	@Override
	protected void doPrepare() throws Exception { }
	
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public int getCode() {
		return code;
	}
	public Map<String, Object> getMsg() {
		return msg;
	}

}
