package com.luo.springcloudeurekaclientdraghook.Service;


import com.luo.springcloudeurekaclientdraghook.entity.Draghook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.io.Serializable;

@Service
public class ActiveMQService {

    @Autowired
    DraghookService draghookService;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Queue productActiveMQQueue;

    //创建拉钩的监听期
    public void sendMessage(Draghook draghook) {
        this.jmsMessagingTemplate.convertAndSend(this.productActiveMQQueue,draghook);
    }


    @JmsListener(destination = "CreateDraghook",containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(Draghook draghook) {
        draghookService.SaveNewDraghook(draghook);

    }
}
