package com.idowran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idowran.entity.OrderGoods;

@Mapper
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {
	
	@Select("SELECT g.id goods_id, g.title, g.size, g.price, g.pic, g.content, c.number FROM nd_goods g " + 
			"JOIN nd_cart c ON c.goods_id = g.id " + 
			"WHERE c.user_id = #{userId} AND g.id in ($(goodsIds))")
	List<OrderGoods> getWillCreateOrderGoodsList(Long userId, String goodsIds);
}
