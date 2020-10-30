package com.idowran.entity.request;

import lombok.Data;

@Data
public class GoodsDto {
	
	private Long id;
	
	private String title;
	
	private String size;		// 规格
	
	private Double price;

	private String pic;
	
	private String content;		// 描述
	
	private Integer status;
}
