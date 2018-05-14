package com.arthur;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SysApplication {
  
  public static void main(String[] args) {
    new SpringApplicationBuilder(SysApplication.class).web(true).run(args);
  }

}
