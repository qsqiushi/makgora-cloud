package com.arthur.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.base.mapper.BaseMapper;
import com.arthur.base.service.BaseService;
import com.arthur.sys.domain.Constant;
import com.arthur.sys.domain.ConstantExample;
import com.arthur.sys.mapper.ConstantMapper;

@Service
@Transactional
public class ConstantServiceImpl implements BaseService<Constant, ConstantExample> {

  //@Autowired
  private ConstantMapper constantMapper;

  @Override
  public BaseMapper<Constant, ConstantExample> getMapper() {
    return constantMapper;
  }



}
