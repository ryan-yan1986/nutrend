package com.idowran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idowran.entity.request.OrderCommendDto;
import com.idowran.entity.response.OrderVO;
import com.idowran.service.OrderService;
import com.idowran.utils.constants.URLConstant;
import com.idowran.utils.response.JsonResponseEntity;
import com.idowran.utils.response.JsonResponseListEntity;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = URLConstant.ORDER_GET_LIST)
	public JsonResponseListEntity<OrderVO> getList() {
		List<OrderVO> list = orderService.getList();
		if(list.size() <= 0) {
			return JsonResponseListEntity.fail(0);
		}
		return JsonResponseListEntity.ok(list);
	}

	@GetMapping(value = URLConstant.ORDER_GET_ONE)
	public JsonResponseEntity<OrderVO> getOne(@RequestParam("id") Long id) {
		OrderVO one = orderService.getInfo(id);
		if(one == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(one);
	}
	
	@PostMapping(value = URLConstant.ORDER_CREATE)
	public JsonResponseEntity<Integer> create(@RequestParam("goodsIds") List<Long> goodsIds) {
		Integer res = orderService.create(goodsIds);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}
	
	/**
	 * 商家发货
	 * @param id
	 * @return
	 */
	@PostMapping(value = URLConstant.ORDER_SHIPPING)
	public JsonResponseEntity<Integer> shipping(@RequestParam("id") Long id) {
		Integer res = orderService.shipping(id);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}
	
	/**
	 * 买家签收
	 * @param id
	 * @return
	 */
	@PostMapping(value = URLConstant.ORDER_RECEIVING)
	public JsonResponseEntity<Integer> receiving(@RequestParam("id") Long id) {
		Integer res = orderService.receiving(id);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}

	/**
	 * 买家评价
	 * @param dto
	 * @return
	 */
	@PostMapping(value = URLConstant.ORDER_COMMEND)
	public JsonResponseEntity<Integer> commend(@RequestBody OrderCommendDto dto) {
		Integer res = orderService.commend(dto);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}
	
	@PostMapping(value = URLConstant.ORDER_CANCEL)
	public JsonResponseEntity<Integer> cancel(@RequestParam("id") Long id) {
		Integer res = orderService.cancel(id);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}

	@PostMapping(value = URLConstant.ORDER_REMOVE)
	public JsonResponseEntity<Integer> remove(@RequestParam("id") Long id) {
		Integer res = orderService.remove(id);
		if(res == null) {
			return JsonResponseEntity.fail(0);
		}
		return JsonResponseEntity.ok(res);
	}
	
}
