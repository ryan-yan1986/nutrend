package com.idowran.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.idowran.entity.Order;
import com.idowran.entity.OrderGoods;
import com.idowran.entity.request.OrderCommendDto;
import com.idowran.entity.request.OrderGoodsCommendDto;
import com.idowran.entity.response.OrderGoodsVO;
import com.idowran.entity.response.OrderVO;
import com.idowran.mapper.OrderGoodsMapper;
import com.idowran.mapper.OrderMapper;
import com.idowran.service.CartService;
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
	private CartService cartService;
	
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
				BeanUtils.copyProperties(goods, goodsVo);
				goodsVoList.add(goodsVo);
			}
			
			OrderVO vo = new OrderVO();
			BeanUtils.copyProperties(order, vo);
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
			BeanUtils.copyProperties(goods, goodsVo);
			goodsVoList.add(goodsVo);
		}
		
		OrderVO vo = new OrderVO();
		BeanUtils.copyProperties(order, vo);
		vo.setGoodsList(goodsVoList);
			
		return vo;
	}

	@Transactional
	@Override
	public Integer create(List<Long> goodsIds) {

		Long userId = UserUtils.getUserId();
		
		// 订单中的商品列表
		List<OrderGoods> goodsList = orderGoodsMapper.getWillCreateOrderGoodsList(userId, StringUtils.join(goodsIds, ","));
		
		// 1 创建订单
		Order order = new Order();
		order.setUserId(userId);
		order.setCreateDate(new Date());
		order.setStatus(OrderStatusConstant.ORDER_STATUS_WILL_PAYMENT);
		
		// 计算订单总价格
		Double priceTotal = goodsList.stream().mapToDouble(item -> new BigDecimal(item.getNumber()).multiply(new BigDecimal(item.getPrice())).doubleValue()).sum();
		order.setPriceTotal(priceTotal);
		int res = orderMapper.insert(order);
		
		goodsList.forEach(item -> {
			item.setOrderId(order.getId());
			// 2 创建订单的商品数据
			orderGoodsMapper.insert(item);
			
			// 3 清除购物车的关联商品
			cartService.goodsRemove(item.getGoodsId());
		});
		
		return res;
	}
	
	@Override
	public Integer shipping(Long id) {
		Order order = orderMapper.selectById(id);
		if(order.getStatus() != OrderStatusConstant.ORDER_STATUS_WILL_PAYMENT) return 0;	// 这里可以抛异常
		
		// 支付完成后才能发货
		order.setStatus(OrderStatusConstant.ORDER_STATUS_WILL_DISPATCHED);
		return orderMapper.updateById(order);
	}

	@Override
	public Integer receiving(Long id) {
		Order order = orderMapper.selectById(id);
		if(order.getStatus() != OrderStatusConstant.ORDER_STATUS_WILL_DISPATCHED) return 0;	// 可以抛异常
			
		// 配送中的才能收货
		order.setStatus(OrderStatusConstant.ORDER_STATUS_WILL_COMMEND);
		return orderMapper.updateById(order);
	}
	
	@Transactional
	@Override
	public Integer commend(OrderCommendDto dto) {
		
		Order order = orderMapper.selectById(dto.getId());
		if (order.getStatus() != OrderStatusConstant.ORDER_STATUS_WILL_COMMEND) return 0;	// 可以抛异常
		
		order.setStarService(dto.getStarService());
		order.setStarShipping(dto.getStarShipping());
		order.setStatus(OrderStatusConstant.ORDER_STATUS_FINISHED);
		int res = orderMapper.updateById(order);

		Date now = new Date();
		QueryWrapper<OrderGoods> wrapper = new QueryWrapper<>();
		wrapper.eq("order_id", dto.getId());
		for (OrderGoodsCommendDto goodsDto : dto.getGoodsCommendList()) {
			wrapper.eq("goods_id", goodsDto.getGoodsId());
			OrderGoods goods = orderGoodsMapper.selectOne(wrapper);
			goods.setCommend(goodsDto.getCommend());
			goods.setCommendDate(now);
			goods.setCommendStar(goodsDto.getCommendStar());
			goods.setCommendPic(StringUtils.join(goodsDto.getCommendPics().toArray(), ","));
			orderGoodsMapper.updateById(goods);
		}
		return res;
	}

	@Transactional
	@Override
	public Integer cancel(Long id) {
		Order order = orderMapper.selectById(id);
		if(order.getStatus() == OrderStatusConstant.ORDER_STATUS_WILL_PAYMENT || 
				order.getStatus() == OrderStatusConstant.ORDER_STATUS_WILL_SHIPPING) {
			// (待支付, 待发货状态才可以取消)
			order.setStatus(OrderStatusConstant.ORDER_STATUS_CANCEL);
			return orderMapper.updateById(order);
		}
		// 这里可以抛异常
		return 0;
	}

	@Transactional
	@Override
	public Integer remove(Long id) {
		Order order = orderMapper.selectById(id);
		if(order.getStatus() == OrderStatusConstant.ORDER_STATUS_WILL_COMMEND || 
				order.getStatus() == OrderStatusConstant.ORDER_STATUS_FINISHED || 
				order.getStatus() == OrderStatusConstant.ORDER_STATUS_CANCEL) {
			// (待评价, 已完成, 已取消状态才可以删除)
			order.setStatus(OrderStatusConstant.ORDER_STATUS_DELETED);
			return orderMapper.updateById(order);
		}
		// 这里可以抛异常
		return 0;
	}
}
