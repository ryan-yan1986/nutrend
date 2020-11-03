package com.idowran.entity.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderGoodsCommendDto {
	
	private Long goodsId;		// 订单商品id
	
	private String commend;		// 商品评价
	
	private List<String> commendPics; 	// 评价的商品图片
	
	private Integer commendStar;		// 评分
}
