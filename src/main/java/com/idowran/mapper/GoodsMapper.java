package com.idowran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idowran.entity.Goods;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

	@Select("SELECT g.* FROM nd_goods g " + 
			"JOIN nd_goods_category_relation gcr ON gcr.goods_id = g.id " + 
			"WHERE gcr.category_id in (${categoryIds}) GROUP BY g.id")
	List<Goods> getListInCategoryIds(String categoryIds);
}
