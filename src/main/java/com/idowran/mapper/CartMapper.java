package com.idowran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idowran.entity.Cart;
import com.idowran.entity.response.CartGoodsVO;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

	@Select("SELECT g.id, c.number, g.title, g.size, g.price, g.pic FROM nd_goods g " + 
			"JOIN nd_cart c ON c.goods_id = g.id " + 
			"WHERE c.user_id = #{userId};")
	List<CartGoodsVO> getGoodsList(@Param("userId") Long userId);
}
