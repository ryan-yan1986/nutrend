package com.idowran.utils.constants;

public class OrderStatusConstant {
	
	public final static int ORDER_STATUS_WILL_PAYMENT 		= 1 << 0;	// 1 待支付(用户下单)
	public final static int ORDER_STATUS_WILL_SHIPPING 		= 1 << 1;	// 2 待发货(用户支付)
	public final static int ORDER_STATUS_WILL_DISPATCHED 	= 1 << 2;	// 4 待收货(商家发货)
	public final static int ORDER_STATUS_WILL_COMMEND 		= 1 << 3;	// 8 待评价(用户收货)
	public final static int ORDER_STATUS_FINISHED	 		= 1 << 4;	// 16 已完成(用户完成评价)
	
	public final static int ORDER_STATUS_CANCEL 	= -1;	// 已取消
	public final static int ORDER_STATUS_DELETED 	= -2;	// 已删除
	
}
