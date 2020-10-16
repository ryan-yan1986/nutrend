package com.idowran.entity.response;

import lombok.Data;

@Data
public class GoodsCategorySubVO {
	
	private Long id;
	
	private String title;
	
	private String pic;
	
	private Integer sort;

	private Long goodsCount;
}
