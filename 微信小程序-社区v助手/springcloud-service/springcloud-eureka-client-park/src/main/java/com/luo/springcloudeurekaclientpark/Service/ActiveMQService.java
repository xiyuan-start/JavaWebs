package com.luo.springcloudeurekaclientpark.Service;



import com.luo.springcloudeurekaclientpark.entity.CarOrder;
import org.quartz.SchedulerException;
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
    OrderService orderService;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "CreateOrder")
    Queue createOrder;


    //创建拉钩的监听期
    public void sendMessageforNewOrder(CarOrder carOrder) {
        this.jmsMessagingTemplate.convertAndSend(this.createOrder,carOrder);
    }

    @JmsListener(destination = "CreateOrder",containerFactory = "jmsListenerContainerQueue")
    public void receiveQueueforNewOrder(CarOrder carOrder) throws ParseException ,SchedulerException {

        orderService.CreateNewOrder(carOrder);

    }


}
