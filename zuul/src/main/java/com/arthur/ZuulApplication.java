package com.arthur;

import com.arthur.filter.ErrorFilter;
import com.arthur.filter.ResultFilter;
import com.marcosbarbero.zuul.filters.pre.ratelimit.RateLimitFilter;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

//import com.arthur.filter.AccessFilter;
//import com.arthur.filter.UuidFilter;
//import com.arthur.filter.ValidateFilter;

@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }

    //	@Bean
//	public AccessFilter accessFilter() {
//		return new AccessFilter();
//	}
//	
    @Bean
    public RateLimitFilter rateLimiterFilter() {
        return new RateLimitFilter(null, null, null);
    }

    @Bean
    public ResultFilter resultFilter() {
        return new ResultFilter();
    }
//	
//	@Bean
//	public UuidFilter uuidFilter() {
//		return new UuidFilter();
//	}
//	
//	@Bean
//	public ValidateFilter validateFilter() {
//		return new ValidateFilter();
//	}

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

}
