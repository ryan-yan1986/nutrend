package com.idowran.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_article_category")
public class ArticleCategory {

	@TableId(type = IdType.AUTO)
	private Long id;
	
	private String title;
	
	private String pic;
	
	private Integer sort;
}
