package com.idowran.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idowran.entity.response.OrderVO;
import com.idowran.utils.constants.URLConstant;
import com.idowran.utils.response.JsonResponseEntity;
import com.idowran.utils.response.JsonResponseListEntity;

@RestController
public class OrderController {
	
	@GetMapping(value = URLConstant.ORDER_GET_LIST)
	public JsonResponseListEntity<OrderVO> getList() {
		
		return null;
	}

	@GetMapping(value = URLConstant.ORDER_GET_ONE)
	public JsonResponseEntity<OrderVO> getOne(@RequestParam("id") Long id) {

		return null;
	}
	
	@PostMapping(value = URLConstant.ORDER_CREATE)
	public JsonResponseEntity<Integer> create(@RequestParam("goodsIds") List<Long> goodsIds) {

		return null;
	}

	@PostMapping(value = URLConstant.ORDER_CANCEL)
	public JsonResponseEntity<Integer> cancel(@RequestParam("id") Long id) {

		return null;
	}

	@PostMapping(value = URLConstant.ORDER_REMOVE)
	public JsonResponseEntity<Integer> remove(@RequestParam("id") Long id) {

		return null;
	}

	@PostMapping(value = URLConstant.ORDER_COMMEND)
	public JsonResponseEntity<Integer> commend() {

		return null;
	}
	
}
