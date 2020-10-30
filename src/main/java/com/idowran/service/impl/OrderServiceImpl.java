package com.idowran.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idowran.entity.Goods;
import com.idowran.entity.Order;
import com.idowran.entity.OrderGoods;
import com.idowran.entity.response.OrderGoodsVO;
import com.idowran.entity.response.OrderVO;
import com.idowran.mapper.GoodsMapper;
import com.idowran.mapper.OrderGoodsMapper;
import com.idowran.mapper.OrderMapper;
import com.idowran.service.OrderService;
import com.idowran.utils.UserUtils;
import com.idowran.utils.constants.OrderStatusConstant;

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
		Long userId = UserUtils.getUserId();
		
		QueryWrapper<Order> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		List<Order> orderList = orderMapper.selectList(wrapper);
		
		List<OrderVO> voList = new LinkedList<>();
		for (Order order : orderList) {
			
			// 订单下的商品
			QueryWrapper<OrderGoods> wrapperGoods = new QueryWrapper<>();
			wrapperGoods.eq("order_id", order.getId());
			List<OrderGoods> goodsList = orderGoodsMapper.selectList(wrapperGoods);
			
			List<OrderGoodsVO> goodsVoList = new ArrayList<>();
			for (OrderGoods goods : goodsList) {
				OrderGoodsVO goodsVo = new OrderGoodsVO();
				goodsVo.setId(goods.getId());
				goodsVo.setGoodsId(goods.getGoodsId());
				goodsVo.setTitle(goods.getTitle());
				goodsVo.setNumber(goods.getNumber());
				goodsVo.setPrice(goods.getPrice());
				goodsVo.setSize(goods.getSize());
				goodsVo.setPic(goods.getPic());
				goodsVoList.add(goodsVo);
			}
			
			OrderVO vo = new OrderVO();
			vo.setId(order.getId());
			vo.setPriceTotal(order.getPriceTotal());
			vo.setCreateDate(order.getCreateDate());
			vo.setStatus(order.getStatus());
			vo.setGoodsList(goodsVoList);
			
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public OrderVO getInfo(Long id) {
		
		Order order = orderMapper.selectById(id);
		
		// 订单下的商品
		QueryWrapper<OrderGoods> wrapperGoods = new QueryWrapper<>();
		wrapperGoods.eq("order_id", id);
		List<OrderGoods> goodsList = orderGoodsMapper.selectList(wrapperGoods);
		
		List<OrderGoodsVO> goodsVoList = new ArrayList<>();
		for (OrderGoods goods : goodsList) {
			OrderGoodsVO goodsVo = new OrderGoodsVO();
			goodsVo.setId(goods.getId());
			goodsVo.setGoodsId(goods.getGoodsId());
			goodsVo.setTitle(goods.getTitle());
			goodsVo.setNumber(goods.getNumber());
			goodsVo.setPrice(goods.getPrice());
			goodsVo.setSize(goods.getSize());
			goodsVo.setPic(goods.getPic());
			goodsVoList.add(goodsVo);
		}
		
		OrderVO vo = new OrderVO();
		vo.setId(order.getId());
		vo.setPriceTotal(order.getPriceTotal());
		vo.setCreateDate(order.getCreateDate());
		vo.setStatus(order.getStatus());
		vo.setGoodsList(goodsVoList);
			
		return vo;
	}

	@Transactional
	@Override
	public Integer create(List<Long> goodsIds) {

		Long userId = UserUtils.getUserId();
		
		Double priceTotal = 0.0;
		
		
		Order order = new Order();
		order.setUserId(userId);
		order.setCreateDate(new Date());
		order.setStatus(OrderStatusConstant.ORDER_STATUS_WILL_PAYMENT);
		order.setPriceTotal(priceTotal);
		int res = orderMapper.insert(order);
		
		return res;
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
		Order order = orderMapper.selectById(id);
		order.setStatus(OrderStatusConstant.ORDER_STATUS_CANCEL);
		
		return orderMapper.updateById(order);
	}

	@Transactional
	@Override
	public Integer remove(Long id) {
		Order order = orderMapper.selectById(id);
		order.setStatus(OrderStatusConstant.ORDER_STATUS_DELETED);
		
		return orderMapper.updateById(order);
	}
	
	
}
