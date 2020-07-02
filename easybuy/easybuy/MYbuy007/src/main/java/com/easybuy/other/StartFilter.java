package com.easybuy.other;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class StartFilter {


    //利用Bean的方式 在程序刚加载时 对拦截器进行了设置
    //利用拦截器可以对 request 和 response进行输入 输出预处理（过滤）
    @Bean
    public FilterRegistrationBean FilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());

        //过滤掉  jsp 和对Controller 的直接访问
        registration.addUrlPatterns("/Admin/*");
        //registration.addUrlPatterns("/Favorite/*");

        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
}