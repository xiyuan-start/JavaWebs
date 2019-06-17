package com.luo.springcloudeurekaclientpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com")
@EnableEurekaClient
@EnableAutoConfiguration //如果使用@Configuration的话 需要这个注解
@ServletComponentScan("com.luo")
public class SpringcloudEurekaClientParkApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringcloudEurekaClientParkApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaClientParkApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return  new RestTemplate();
    }




}
