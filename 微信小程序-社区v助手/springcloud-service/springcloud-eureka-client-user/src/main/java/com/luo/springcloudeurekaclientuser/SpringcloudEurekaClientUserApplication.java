package com.luo.springcloudeurekaclientuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*@EnableCircuitBreaker 开启熔断机制
* */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableAutoConfiguration //如果使用@Configuration的话 需要这个注解
@ServletComponentScan("com.luo")
public class SpringcloudEurekaClientUserApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringcloudEurekaClientUserApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaClientUserApplication.class, args);
    }
    /**
     * RestTemplate提供用于访问Rest服务的客户端实例
     *
     * @LoadBalanced 开启客户端组件Ribbon的负载均衡能力
     * 修改端口号或者在其他服务器中运行多个 该组件 可实现客户端负载均衡
     * 使用RestTemplate不需要指定IP  直接使用应用名
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return  new RestTemplate();
    }




}
