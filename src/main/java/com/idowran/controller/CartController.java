package com.idowran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idowran.service.CartService;
import com.idowran.utils.constants.URLConstant;
import com.idowran.utils.response.JsonResponseEntity;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	public void getInfo() {
		
	}
	
	@PostMapping(value = URLConstant.CART_GOODS_ADD)
	public JsonResponseEntity<Integer> goodsAdd(@RequestParam("goodsId") Long goodsId) {
		Integer res = cartService.goodsAdd(goodsId);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}

	@PostMapping(value = URLConstant.CART_GOODS_REMOVE)
	public JsonResponseEntity<Integer> goodsRemove(@RequestParam("goodsId") Long goodsId) {
		Integer res = cartService.goodsRemove(goodsId);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}

	@PostMapping(value = URLConstant.CART_GOODS_INC)
	public JsonResponseEntity<Integer> goodsInc(@RequestParam("goodsId") Long goodsId) {
		Integer res = cartService.goodsInc(goodsId);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}

	@PostMapping(value = URLConstant.CART_GOODS_DEC)
	public JsonResponseEntity<Integer> goodsDec(@RequestParam("goodsId") Long goodsId) {
		Integer res = cartService.goodsDec(goodsId);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}
}
