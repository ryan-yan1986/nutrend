package com.idowran.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idowran.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	
}
