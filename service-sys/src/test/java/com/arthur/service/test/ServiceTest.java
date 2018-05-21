package com.arthur.service.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.arthur.sys.domain.ConstantExample;
import com.arthur.sys.mapper.ConstantMapper;
import com.arthur.sys.mapper.PrimaryKeyMapper;
import com.arthur.sys.properties.ConfigProperties;
import com.arthur.sys.properties.RedissonProperties;
import com.arthur.sys.service.impl.RedissonManagerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceTest {
  @Autowired
  private ConstantMapper constantMapper;
  @Autowired
  private ConfigProperties configProperties;
  @Autowired
  private PrimaryKeyMapper  primaryKeyMapper;

  
  @Autowired
  private RedisTemplate<String,Object> redisTemplate;
  
  @Autowired
  private RedisTemplate<String,Map<String,String>> redisMapTemplate;
  
  @Autowired
  private  RedissonManagerServiceImpl  redissonManagerService;
  
  
  @Autowired
  private RedissonProperties redissonProperties;
  
  @Test
  public void test3() {
    System.out.println(redissonProperties.getAddress());
    
    RLock rLock=  redissonManagerService.lock("123123",20);
    RLock rLock2=  redissonManagerService.lock("123123",20);
    System.out.println(rLock.isExists());
    System.out.println(rLock2.isExists());
    redissonManagerService.unlock("123123");
  }
  
  
  public void testRedis() {
    System.out.println(redisTemplate==null);
    System.out.println(redisMapTemplate==null);
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();
   ops.set("1",1);
     Map<String,String> map = new HashMap<String,String>();
     map.put("2", "value");
     ValueOperations<String,  Map<String,String>> redisMapTemplateOps = redisMapTemplate.opsForValue();
     redisMapTemplateOps.set("2",map);
    System.out.println(ops.get("1"));
    Map<String,String> map2=redisMapTemplateOps.get("2");
    System.out.println(map2.get("2"));
  }
  
  
  public void test() {
    System.out.println(constantMapper==null);
    System.out.println(configProperties==null);
    System.out.println(configProperties.getUrl());
    System.out.println(constantMapper.countByExample(new ConstantExample()));
    System.out.println(primaryKeyMapper==null);
    System.out.println(primaryKeyMapper.getPrimaryKey());
    System.out.println(primaryKeyMapper.getEvenPrimaryKey());
  }

}
