package com.idowran.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idowran.entity.Cart;
import com.idowran.mapper.CartMapper;
import com.idowran.service.CartService;
import com.idowran.utils.UserUtils;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void getInfo() {
		// TODO Auto-generated method stub

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
