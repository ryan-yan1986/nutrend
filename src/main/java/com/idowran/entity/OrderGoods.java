package com.idowran.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_order_goods")
public class OrderGoods {
	
	@TableField(value = "order_id")
	private Long orderId;		// 所属订单id
	
	@TableField(value = "goods_id")
	private Long goodsId;		// 商品Id
	
	private String title;		// 商品标题[冗余]
	
	private String size;		// 商品规格[冗余]
	
	private Double price;		// 商品价格[冗余]
	
	private String pic;			// 商品图片[冗余]
	
	private String content;		// 商品描述[冗余]
	
	private Integer number;		// 购买商品数量
	
	private String commend;			// 评价描述
	
	private Date commendDate;		// 评价时间
	
	private String commendPic;		// 评价的图片
	
	private Integer commendStar;	// 商品评分
	
	
}
