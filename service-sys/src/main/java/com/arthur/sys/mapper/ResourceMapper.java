package com.arthur.sys.mapper;

import java.util.List;
import java.util.Map;

import com.arthur.base.mapper.BaseMapper;
import com.arthur.sys.domain.Resource;
import com.arthur.sys.domain.ResourceExample;

public interface ResourceMapper extends BaseMapper<Resource, ResourceExample> {

    List<Map<String, String>> getResourceNeedPermission();
}