package com.idowran.entity.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderCommendDto {

	private Long id;	// 订单id
	
	private Integer starService;	// 服务评价
	
	private Integer starShipping;	// 配送评价

	private List<OrderGoodsCommendDto> goodsCommendList;
}
