package com.arthur.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.arthur.base.mapper.BaseMapper;
import com.arthur.sys.domain.SysLog;
import com.arthur.sys.domain.SysLogExample;
import com.arthur.sys.domain.SysLogWithBLOBs;

public interface SysLogMapper extends BaseMapper<SysLog, SysLogExample> {

    int updateByExampleWithBLOBs(@Param("record") SysLogWithBLOBs record, @Param("example") SysLogExample example);

    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

}