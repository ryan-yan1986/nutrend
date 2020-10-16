package com.idowran.service;

import java.util.List;

import com.idowran.entity.response.GoodsCategoryVO;
import com.idowran.entity.response.GoodsVO;

public interface GoodsService {

	public List<GoodsCategoryVO> categoryList();
	
	public Integer categorySave();
	
	public Integer categoryRemove(Long id);
	
	public List<GoodsVO> getList(Long categoryId);
	
	public GoodsVO getOne(Long id);
	
	public Integer save();
	
	public Integer remove(Long id);
}
