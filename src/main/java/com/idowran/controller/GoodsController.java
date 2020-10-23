package com.idowran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idowran.entity.response.GoodsCategoryVO;
import com.idowran.entity.response.GoodsVO;
import com.idowran.service.GoodsService;
import com.idowran.utils.constants.URLConstant;
import com.idowran.utils.response.JsonResponseEntity;
import com.idowran.utils.response.JsonResponseListEntity;

@RestController
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 获取所有分类列表
	 * @return
	 */
	@GetMapping(value = URLConstant.GOODS_CATEGORY_LIST)
	public JsonResponseListEntity<GoodsCategoryVO> categoryList() {
		List<GoodsCategoryVO> list = goodsService.categoryList();
		if(list.size() <= 0) {
			return JsonResponseListEntity.fail(0);
		}
		return JsonResponseListEntity.ok(list);
	}

	/**
	 * 保存分类
	 */
	@PostMapping(value = URLConstant.GOODS_CATEGORY_SAVE)
	public void categorySave() {
		
	}

	/**
	 * 删除分类
	 */
	@PostMapping(value = URLConstant.GOODS_CATEGORY_REMOVE)
	public void categoryRemove() {
		
	}

	/**
	 * 获取指定分类下的商品列表
	 * @param categoryId
	 * @return
	 */
	@GetMapping(value = URLConstant.GOODS_GET_LIST)
	public JsonResponseListEntity<GoodsVO> getList(@RequestParam(value="categoryId", required = false, defaultValue = "0") Long categoryId) {
		List<GoodsVO> list = goodsService.getList(categoryId);
		if(list.size() <= 0) {
			return JsonResponseListEntity.fail(0);
		}
		return JsonResponseListEntity.ok(list);
	}

	/**
	 * 获取指定商品详情
	 * @param id
	 * @return
	 */
	@GetMapping(value = URLConstant.GOODS_GET_ONE)
	public JsonResponseEntity<GoodsVO> getOne(@RequestParam(value="id") Long id) {
		GoodsVO one = goodsService.getOne(id);
		if(one == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(one);
	}

	/**
	 * 保存商品
	 */
	@PostMapping(value = URLConstant.GOODS_SAVE)
	public void save() {
		
	}


	/**
	 * 删除商品
	 */
	@PostMapping(value = URLConstant.GOODS_REMOVE)
	public void remove() {
		
	}
}
