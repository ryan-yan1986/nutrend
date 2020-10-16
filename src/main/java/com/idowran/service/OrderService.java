package com.idowran.service;

public interface OrderService {

	public void getList();
	
	public void getInfo();
	
	public Integer commend();
	
	public Integer cancel(Long id);
	
	public Integer remove(Long id);
}
