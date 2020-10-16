package com.idowran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idowran.entity.GoodsCategory;
import com.idowran.entity.response.GoodsCategorySubVO;

@Mapper
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {
	
	
	@Select("SELECT gc.id, gc.title, gc.pic, gc.sort, count(g.id) goods_count FROM nd_goods_category gc " + 
			"LEFT JOIN nd_goods g ON gc.id = g.category_id " + 
			"WHERE p_id = #{topId} GROUP BY gc.id ORDER BY gc.sort ASC")
	List<GoodsCategorySubVO> getSubList(Long topId);
}
