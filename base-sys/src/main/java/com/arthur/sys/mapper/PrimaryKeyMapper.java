package com.arthur.sys.mapper;

public interface PrimaryKeyMapper {

  
  
  /**
   * 取得基数主键
   * @return
   */
  public Long getPrimaryKey();
  /**
   * 取得偶数主键
   * @return
   */
  public Long getEvenPrimaryKey();
}
