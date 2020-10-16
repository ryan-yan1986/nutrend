package com.idowran.entity.response;

import lombok.Data;

@Data
public class GoodsVO {
	
	private Long id;
	
	private String title;
	
	private String size;		// 规格
	
	private Double price;
	
	private String pic;
	
	private String content;
	
	private Integer collectCount;	// 收藏数

	private Integer orderCount;		// 订单数
}
