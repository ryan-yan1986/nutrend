package com.idowran.entity.response;

import lombok.Data;

@Data
public class OrderGoodsVO {
	
	private Long goodsId;		// 商品Id
	
	private String title;
	
	private String size;		// 规格
	
	private Double price;
	
	private String pic;
	
	private Integer number;		// 商品数量
}
