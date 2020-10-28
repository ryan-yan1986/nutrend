package com.idowran.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idowran.entity.Cart;
import com.idowran.entity.response.CartGoodsVO;
import com.idowran.entity.response.CartInfoVO;
import com.idowran.mapper.CartMapper;
import com.idowran.service.CartService;
import com.idowran.utils.UserUtils;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public CartInfoVO getInfo() {
		// TODO Auto-generated method stub
		Long userId = UserUtils.getUserId();
		
		List<CartGoodsVO> cartGoodsList = cartMapper.getGoodsList(userId);
		// 总价格
		Double priceTotal = cartGoodsList.stream().mapToDouble(item -> new BigDecimal(item.getNumber()).multiply(new BigDecimal(item.getPrice())).doubleValue()).sum();
		
		CartInfoVO vo = new CartInfoVO();
		vo.setGoodsList(cartGoodsList);
		vo.setPriceTotal(priceTotal);
		return vo;
	}
	
	@Override
	public Integer goodsAdd(Long goodsId) {
		Long userId = UserUtils.getUserId();
		
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId).eq("goods_id", goodsId);
		Cart cart 	= cartMapper.selectOne(wrapper);
		Date now 	= new Date();
		int res 	= 0;
		if(cart == null) {
			cart = new Cart();
			cart.setGoodsId(goodsId);
			cart.setUserId(userId);
			cart.setNumber(1);
			cart.setInsertDate(now);
			res = cartMapper.insert(cart);
		}else {
			cart.setNumber(cart.getNumber() + 1);
			cart.setInsertDate(now);
			res = cartMapper.updateById(cart);
		}
		return res;
	}

	@Override
	public Integer goodsRemove(Long goodsId) {
		Long userId = UserUtils.getUserId();
		
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId).eq("goods_id", goodsId);
		
		return cartMapper.delete(wrapper);
	}

	@Override
	public Integer goodsInc(Long goodsId) {
		Long userId = UserUtils.getUserId();
		
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId).eq("goods_id", goodsId);
		
		Cart cart = cartMapper.selectOne(wrapper);
		cart.setNumber(cart.getNumber() + 1);
		cart.setInsertDate(new Date());
		
		return cartMapper.updateById(cart);
	}

	@Override
	public Integer goodsDec(Long goodsId) {
		Long userId = UserUtils.getUserId();
		
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId).eq("goods_id", goodsId);
		Cart cart 	= cartMapper.selectOne(wrapper);
		
		int res = 0;
		if(cart.getNumber() <=1) {
			res = cartMapper.delete(wrapper);
		}else {
			cart.setNumber(cart.getNumber() - 1);
			res = cartMapper.updateById(cart);
		}
		return res;
	}
}
