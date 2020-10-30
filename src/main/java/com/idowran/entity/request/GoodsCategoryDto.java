package com.idowran.entity.request;

import lombok.Data;

@Data
public class GoodsCategoryDto {
	
	private Long id;
	
	private String title;
	
	private Long pId;		// 父级id
	
	private String pic;
	
	private Integer sort;
	
	private Integer isShow;	// 0 隐藏, 1 显示
}
