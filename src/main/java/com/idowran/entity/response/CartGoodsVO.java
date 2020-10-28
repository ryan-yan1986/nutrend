package com.idowran.entity.response;

import lombok.Data;

@Data
public class CartGoodsVO {

	private Long id;	// 商品ID
	
	private Integer number;
	
	private String title;
	
	private String size;		// 规格
	
	private Double price;
	
	private String pic;
	
}
