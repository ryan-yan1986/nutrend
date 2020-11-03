package com.idowran.service;

import java.util.List;

import com.idowran.entity.request.OrderCommendDto;
import com.idowran.entity.response.OrderVO;

public interface OrderService {

	public List<OrderVO> getList();
	
	public OrderVO getInfo(Long id);
	
	public Integer create(List<Long> goodsIds);
	
	public Integer shipping(Long id);
	
	public Integer receiving(Long id);
	
	public Integer commend(OrderCommendDto dto);
	
	public Integer cancel(Long id);
	
	public Integer remove(Long id);
}
