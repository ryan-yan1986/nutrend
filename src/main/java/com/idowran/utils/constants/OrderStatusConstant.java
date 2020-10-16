package com.idowran.utils.constants;

public class OrderStatusConstant {
	
	public final static int ORDER_STATUS_WILL_PAYMENT 	= 1 << 0;	// 1 待支付
	public final static int ORDER_STATUS_WILL_SHIPPING 	= 1 << 1;	// 2 代发货
	public final static int ORDER_STATUS_WILL_COMMEND 	= 1 << 2;	// 4 待评价
	public final static int ORDER_STATUS_FINISHED	 	= 1 << 3;	// 8 已完成
	
	public final static int ORDER_STATUS_CANCEL 	= -1;	// 取消
	public final static int ORDER_STATUS_DELETED 	= -2;	// 删除
	
}
