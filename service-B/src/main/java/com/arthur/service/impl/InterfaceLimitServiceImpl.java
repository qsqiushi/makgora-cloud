package com.arthur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthur.entity.InterfaceLimit;
import com.arthur.mapper.InterfaceLimitMapper;
import com.arthur.service.InterfaceLimitService;

@Service
public class InterfaceLimitServiceImpl implements InterfaceLimitService {
	
	@Autowired
	private InterfaceLimitMapper mapper;

	@Override
	public InterfaceLimit getEntityByPri(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}
