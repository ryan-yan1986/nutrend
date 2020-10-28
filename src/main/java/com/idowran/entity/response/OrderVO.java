package com.idowran.entity.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {
	
	private Long id;
	
	private Date createDate;
	
	private Double priceTotal;
	
	private Integer status;		// #OrderStatusConstant
	
	private List<OrderGoodsVO> goodsList;
}
