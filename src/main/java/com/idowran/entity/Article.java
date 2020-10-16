package com.idowran.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Article {

	@TableId(type = IdType.AUTO)
	private Long id;
	
	private Long categoryId;
	
	private String title;
	
	private String content;
	
	private Date createDate;
	
	private Long hits;	// 点击量
	
	private Integer status;
}
