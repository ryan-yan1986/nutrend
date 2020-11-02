package com.idowran.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idowran.entity.Goods;
import com.idowran.entity.GoodsCategory;
import com.idowran.entity.GoodsCategoryRelation;
import com.idowran.entity.request.GoodsCategoryDto;
import com.idowran.entity.request.GoodsDto;
import com.idowran.entity.response.GoodsCategorySubVO;
import com.idowran.entity.response.GoodsCategoryVO;
import com.idowran.entity.response.GoodsVO;
import com.idowran.mapper.GoodsCategoryMapper;
import com.idowran.mapper.GoodsCategoryRelationMapper;
import com.idowran.mapper.GoodsMapper;
import com.idowran.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsCategoryMapper categoryMapper;
	
	private GoodsCategoryRelationMapper relationMapper;
	
	@Override
	public List<GoodsCategoryVO> categoryList() {
		// TODO Auto-generated method stub
		
		QueryWrapper<GoodsCategory> wrapper = new QueryWrapper<>();
		wrapper.orderByAsc("sort").eq("is_show", 1).eq("level", 0);
		List<GoodsCategory> list = categoryMapper.selectList(wrapper);
		
		List<GoodsCategoryVO> voList = new LinkedList<>();
		for (GoodsCategory topCategory : list) {
			GoodsCategoryVO vo = new GoodsCategoryVO();
			BeanUtils.copyProperties(topCategory, vo);
			
			// 子类
			List<GoodsCategorySubVO> subList = categoryMapper.getSubList(topCategory.getId());
			vo.setSubCategoryList(subList);
			
			// 商品数
			long goodsCount = subList.stream().mapToLong(item -> item.getGoodsCount()).sum();
			vo.setGoodsCount(goodsCount);
			
			voList.add(vo);
		}
		return voList;
	}

	@Transactional
	@Override
	public Integer categorySave(GoodsCategoryDto dto) {
		
		GoodsCategory category = new GoodsCategory();
		BeanUtils.copyProperties(dto, category);
		category.setLevel(dto.getPId() == 0 ? 0 : 1);
		
		if(dto.getId() == null) {
			// 新增
			return categoryMapper.insert(category);
		}else {
			// 更新
			return categoryMapper.updateById(category);
		}
	}

	@Transactional
	@Override
	public Integer categoryRemove(Long categoryId, Integer clearRelation) {
		// TODO Auto-generated method stub
		
		QueryWrapper<GoodsCategoryRelation> wrapper = new QueryWrapper<>();
		wrapper.eq("category_id", categoryId);
		int count = relationMapper.selectCount(wrapper);
		if(count > 0) {
			if(clearRelation != 1) {
				// 这地方可以抛异常
				return 0;
			}else {
				// 清空商品与分类的关联
				relationMapper.delete(wrapper);
			}
		}
		
		return categoryMapper.deleteById(categoryId);
	}

	@Override
	public List<GoodsVO> getList(Long categoryId) {
		// TODO Auto-generated method stub
		
		String categoryIds = null;
		if(categoryId != null && categoryId > 0) {
			GoodsCategory category = categoryMapper.selectById(categoryId);
			if (category.getLevel() == 0) {
				List<GoodsCategorySubVO> subList = categoryMapper.getSubList(categoryId);
				List<String> subIds = subList.stream().map(item -> item.getId().toString()).collect(Collectors.toList());
				
				subIds.add(categoryId.toString());	// 把自己也加进去
				
				categoryIds = String.join(",", subIds);	// list转字符串
			}else {
				categoryIds = categoryId.toString();
			}
		}
		List<Goods> goodsList = null;
		if(categoryIds == null) {
			goodsList = goodsMapper.selectList(null);
		}else {
			goodsList = goodsMapper.getListInCategoryIds(categoryIds);
		}
		
		List<GoodsVO> voList = new LinkedList<>();
		for (Goods goods : goodsList) {
			GoodsVO vo = new GoodsVO();
			BeanUtils.copyProperties(goods, vo, "content");
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public GoodsVO getOne(Long id) {
		Goods goods = goodsMapper.selectById(id);
		GoodsVO vo 	= new GoodsVO();
		BeanUtils.copyProperties(goods, vo);
		
		return vo;
	}

	@Transactional
	@Override
	public Integer save(GoodsDto dto) {

		Goods goods = new Goods();
		BeanUtils.copyProperties(dto, goods);
		
		Date now = new Date();
		goods.setCreateDate(now);
		goods.setUpdateDate(now);
		
		if(dto.getId() == null) {
			// 新增
			return goodsMapper.insert(goods);
		}else {
			// 更新
			return goodsMapper.updateById(goods);
		}
	}

	@Transactional
	@Override
	public Integer remove(Long id) {

		// 1 清空商品与分类的关联
		QueryWrapper<GoodsCategoryRelation> wrapper = new QueryWrapper<>();
		wrapper.eq("goods_id", id);
		relationMapper.delete(wrapper);
		
		return goodsMapper.deleteById(id);
	}

}
