package com.luo.springcloudeurekaclientuser.Service;




import com.luo.springcloudeurekaclientuser.entity.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.io.Serializable;
import java.text.ParseException;

@Service
public class ActiveMQService {

    @Autowired
   ConsumerService consumerService;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "SaveConsumer")
    Queue SaveConsumer;



    //创建拉钩的监听期
    public void sendMessageforSaveConsumer(Consumer consumer) {
        this.jmsMessagingTemplate.convertAndSend(this.SaveConsumer,(Serializable)consumer);
    }

    @JmsListener(destination = "SaveConsumer")
    public void receiveQueueforSaveConsumer(Consumer consumer) throws ParseException  {


        System.out.println("在消息队列中接收的用户对象"+consumer);
        consumerService.SaveConsumer(consumer);

    }


}
