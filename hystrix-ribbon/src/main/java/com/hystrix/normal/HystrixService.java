package com.hystrix.normal;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixService.class);
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String findById(@RequestParam Integer a, @RequestParam Integer b) {
        return restTemplate.getForEntity("http://SERVICE-B/add?a=" + a + "&b=" + b, String.class).getBody();
    }

    /**
     * hystrix fallback方法
     */
    public String fallback(@RequestParam Integer a, @RequestParam Integer b) {
        HystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", "qqq");
        return "出错了 a=" + a + " b=" + b;
    }
}