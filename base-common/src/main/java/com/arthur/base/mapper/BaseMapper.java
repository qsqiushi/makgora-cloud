package com.arthur.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface BaseMapper<T, Example> {

  long countByExample(Example example);

  int deleteByExample(Example example);

  int deleteByPrimaryKey(Long id);

  int insert(T record);

  int insertSelective(T record);

  List<T> selectByExample(Example example);

  T selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

  int updateByExample(@Param("record") T record, @Param("example") Example example);

  int updateByPrimaryKeySelective(T record);

  int updateByPrimaryKey(T record);


}
