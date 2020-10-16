package com.idowran.service;

public interface CartService {
	
	public void getInfo();
	
	public Integer inc(Long id);
	
	public Integer dec(Long id);
	
	public Integer remove(Long id);
}
