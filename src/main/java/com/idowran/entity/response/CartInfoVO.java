package com.idowran.entity.response;

import java.util.List;

import lombok.Data;

@Data
public class CartInfoVO {

	private Double priceTotal;	// 总价格
	
	private List<CartGoodsVO> goodsList;
	
}
