package com.idowran.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_goods_category")
public class GoodsCategory {

	@TableId(type = IdType.AUTO)
	private Long id;
	
	private String title;
	
	private Integer level;	// 分类级别, 0 顶级
	
	private Long pId;		// 父级id
	
	private String pic;
	
	private Integer sort;
	
	private Integer isShow;	// 0 隐藏, 1 显示
}
