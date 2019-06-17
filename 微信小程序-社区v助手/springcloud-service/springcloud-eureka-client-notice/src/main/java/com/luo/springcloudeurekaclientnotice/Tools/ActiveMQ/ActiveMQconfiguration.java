package com.luo.springcloudeurekaclientnotice.Tools.ActiveMQ;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Queue;

@Configuration
//ActiveMQ的工具类
public class ActiveMQconfiguration {

    //当检测到故障时 会自动切换IP
    private static final String BROKER_URL="failover:(tcp://47.94.101.75:61616,tcp://139.196.137.129:61617)";
    //如果ActiveMQ直接部署在服务器中 可以使用默认的模板
    //这是消息队列对象
    @Bean
    public Queue SaveNotice()
    {
        return new ActiveMQQueue("SaveNotice");
    }

    //创建JMS工厂
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue() {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setTrustAllPackages(true);//解决类对象不能被传输的问题
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
    //创建JMS模板
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(){
        return new JmsMessagingTemplate(new ActiveMQConnectionFactory(BROKER_URL));
    }

}