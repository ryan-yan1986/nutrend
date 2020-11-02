package com.idowran.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_goods_category_relation")
public class GoodsCategoryRelation {
	
	@TableField(value = "category_id")
	private Long categoryId;

	@TableField(value = "goods_id")
	private Long goodsId;
}
