package com.arthur.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arthur.sys.mapper.ConstantMapper;
import com.arthur.sys.properties.ConfigProperties;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceTest {
  @Autowired
  private ConstantMapper constantMapper;
  @Autowired
  private ConfigProperties configProperties;

  @Test
  public void test() {
    System.out.println(constantMapper==null);
    System.out.println(configProperties==null);
    System.out.println(configProperties.getUrl());
    
  }

}
