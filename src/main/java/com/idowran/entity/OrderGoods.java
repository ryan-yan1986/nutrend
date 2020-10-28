package com.idowran.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_order_goods")
public class OrderGoods {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	private Long orderId;		// 所属订单id
	
	private Long goodsId;		// 商品Id
	
	private String title;
	
	private String size;		// 规格
	
	private Double price;
	
	private String pic;
	
	private String content;		// 描述
	
	private Integer number;		// 商品数量
	
	private String commend;			// 评价描述
	
	private String commendPic;		// 评价的图片
	
	private Integer commendStar;	// 商品评分
	
	
}
