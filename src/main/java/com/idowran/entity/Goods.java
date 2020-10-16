package com.idowran.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_goods")
public class Goods {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	private String title;
	
	private String size;		// 规格
	
	private Double price;

	private String pic;
	
	private String content;		// 描述
	
	private Date createDate;
	
	private Date updateDate;
	
	private Integer collectCount;	// 收藏数

	private Integer orderCount;		// 订单数
	
	private Integer status;
}
