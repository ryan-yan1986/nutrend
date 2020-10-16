package com.idowran.entity.response;

import java.util.List;

import lombok.Data;

@Data
public class GoodsCategoryVO {
	
	private Long id;
	
	private String title;
	
	private String pic;
	
	private Integer sort;
	
	private Long goodsCount;
	
	private List<GoodsCategorySubVO> subCategoryList;
}
