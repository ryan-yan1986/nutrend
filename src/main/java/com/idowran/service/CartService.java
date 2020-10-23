package com.idowran.service;

public interface CartService {
	
	public void getInfo();
	
	public Integer goodsAdd(Long goodsId);
	
	public Integer goodsRemove(Long goodsId);
	
	public Integer goodsInc(Long goodsId);
	
	public Integer goodsDec(Long goodsId);
	
}
