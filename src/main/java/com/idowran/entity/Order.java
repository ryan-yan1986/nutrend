package com.idowran.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_order")
public class Order {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	private Long userId;	// 下订单的用户id
	
	private Date createDate;
	
	private Double priceTotal;
	
	private Integer status;		// #OrderStatusConstant
	
	private Integer starService;	// 服务评分
	
	private Integer starShipping;	// 配送评分
}
