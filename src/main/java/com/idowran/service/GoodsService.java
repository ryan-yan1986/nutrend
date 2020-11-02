package com.idowran.service;

import java.util.List;

import com.idowran.entity.request.GoodsCategoryDto;
import com.idowran.entity.request.GoodsDto;
import com.idowran.entity.response.GoodsCategoryVO;
import com.idowran.entity.response.GoodsVO;

public interface GoodsService {

	public List<GoodsCategoryVO> categoryList();
	
	public Integer categorySave(GoodsCategoryDto dto);
	
	public Integer categoryRemove(Long id, Integer clearRelation);
	
	
	public List<GoodsVO> getList(Long categoryId);
	
	public GoodsVO getOne(Long id);
	
	public Integer save(GoodsDto dto);
	
	public Integer remove(Long id);
}
