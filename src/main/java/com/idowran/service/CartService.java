package com.idowran.service;

import com.idowran.entity.response.CartInfoVO;

public interface CartService {
	
	public CartInfoVO getInfo();
	
	public Integer goodsAdd(Long goodsId);
	
	public Integer goodsRemove(Long goodsId);
	
	public Integer goodsInc(Long goodsId);
	
	public Integer goodsDec(Long goodsId);
	
}
