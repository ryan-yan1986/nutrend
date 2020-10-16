package com.idowran.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_cart")
public class Cart {
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	private Long userId;	// 用户id
	
	private Long goodsId;
	
	private Integer number;
	
	private Date insertDate;	// 商品插入时间
}
