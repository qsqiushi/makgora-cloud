package com.arthur.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.sys.service.impl.ConstantServiceImpl;

@RestController
@RequestMapping(value = "constant")
public class ConstantController {
  @Autowired
  private ConstantServiceImpl constantService;
  
  @RequestMapping(value="test")
  public @ResponseBody Map<String,String> check(){
    Map <String,String> result = new HashMap<>();
    result.put("result", (constantService==null)+"");
    return result; 
  }

}
