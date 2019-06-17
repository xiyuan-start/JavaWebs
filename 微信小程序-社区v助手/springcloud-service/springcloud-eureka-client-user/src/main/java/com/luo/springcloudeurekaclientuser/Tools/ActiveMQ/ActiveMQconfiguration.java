package com.luo.springcloudeurekaclientuser.Tools.ActiveMQ;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
//ActiveMQ的工具类
public class ActiveMQconfiguration {


    //如果ActiveMQ直接部署在服务器中 可以使用默认的模板
    //这是消息队列对象
    @Bean
    public Queue SaveConsumer()
    {
        return new ActiveMQQueue("SaveConsumer");
    }



}