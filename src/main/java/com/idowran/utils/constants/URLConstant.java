package com.idowran.utils.constants;

public class URLConstant {
	
	// 产品模块
	private final static String GOODS_MODULE = "/Goods";
	// 产品分类相关
	public final static String GOODS_CATEGORY_LIST 		= GOODS_MODULE + "/categoryList";
	
	public final static String GOODS_CATEGORY_SAVE 		= GOODS_MODULE + "/categorySave";
	public final static String GOODS_CATEGORY_REMOVE 	= GOODS_MODULE + "/categoryRemove";
	
	public final static String GOODS_GET_LIST 	= GOODS_MODULE + "/getList";
	public final static String GOODS_GET_ONE 	= GOODS_MODULE + "/getOne";
	
	public final static String GOODS_SAVE 		= GOODS_MODULE + "/save";
	public final static String GOODS_REMOVE 	= GOODS_MODULE + "/remove";
	
	// 购物车模块
	private final static String CART_MODULE = "/Cart";
	public final static String CART_GET_INFO 		= CART_MODULE + "/getInfo";
	
	public final static String CART_GOODS_ADD 		= CART_MODULE + "/goodsAdd";
	public final static String CART_GOODS_REMOVE 	= CART_MODULE + "/goodsRemove";
	public final static String CART_GOODS_INC 		= CART_MODULE + "/goodsInc";
	public final static String CART_GOODS_DEC 		= CART_MODULE + "/goodsDec";
	
	// 订单模块
	private final static String ORDER_MODULE = "/Order";
	public final static String ORDER_GET_LIST	= ORDER_MODULE + "/getList";
	public final static String ORDER_GET_ONE	= ORDER_MODULE + "/getOne";
	
	public final static String ORDER_CREATE		= ORDER_MODULE + "/create";
	public final static String ORDER_CANCEL		= ORDER_MODULE + "/cancel";
	public final static String ORDER_REMOVE		= ORDER_MODULE + "/remove";
	public final static String ORDER_COMMEND	= ORDER_MODULE + "/commend";	// 评价订单
	
	// 支付模块
	
	
}
