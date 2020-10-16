package com.idowran.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "nd_banner")
public class Banner {

	@TableId(type = IdType.AUTO)
	private Long id;
	
	private String title;
	
	private String pic;
	
	private Integer sort;
	
	private Integer status;
	
	private Integer type;		// #BannerTypeConstant
	
	private String url;			// type=1时 外链url
	
	private Long targetId;		// type=2时 产品id, type=3时 文章id
}
