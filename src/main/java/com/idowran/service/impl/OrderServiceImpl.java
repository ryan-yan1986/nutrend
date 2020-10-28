package com.idowran.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idowran.entity.response.OrderVO;
import com.idowran.mapper.GoodsMapper;
import com.idowran.mapper.OrderGoodsMapper;
import com.idowran.mapper.OrderMapper;
import com.idowran.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<OrderVO> getList() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public OrderVO getInfo(Long id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Transactional
	@Override
	public Integer create(List<Long> goodsIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public Integer commend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Integer cancel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Integer remove(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
