package com.idowran.entity;

import java.util.Date;

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
	
	private String content;		// 描述
	
	private Date createDate;
	
	private String commend;			// 评价描述
	
	private String commendPic;		// 评价的图片
	
	private Integer commendStar;	// 商品评分
	
	
}
