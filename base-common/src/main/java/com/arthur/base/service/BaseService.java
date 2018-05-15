package com.arthur.base.service;

import com.arthur.base.mapper.BaseMapper;

public interface BaseService<T,Example> {
  
  
  public BaseMapper<T,Example>  getMapper();

}
