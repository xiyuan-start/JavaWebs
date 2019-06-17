package com.luo.springcloudeurekaclientdraghook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com")
@EnableEurekaClient
@EnableCircuitBreaker
@EnableAutoConfiguration //如果使用@Configuration的话 需要这个注解
@ServletComponentScan("com.luo")
public class SpringcloudEurekaClientDraghookApplication extends SpringBootServletInitializer {




    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringcloudEurekaClientDraghookApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaClientDraghookApplication.class, args);
    }



}
