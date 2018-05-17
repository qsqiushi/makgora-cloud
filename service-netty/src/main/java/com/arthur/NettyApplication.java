package com.arthur;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication

public class NettyApplication {
  public static void main(String[] args) {
    
    
    new SpringApplicationBuilder(NettyApplication.class).web(true).run(args);
  }
  

}
