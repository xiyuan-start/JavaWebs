package com.luo.springcloudeurekaclientnotice.Service;


import com.luo.springcloudeurekaclientnotice.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.io.Serializable;

@Service
public class ActiveMQService {

    @Autowired
    NoticeService noticeService;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "SaveNotice")
    Queue saveNotice;

    //创建拉钩的监听期
    public void sendMessageforSaveNotice(Notice notice) {
        this.jmsMessagingTemplate.convertAndSend(this.saveNotice,notice);
    }


    @JmsListener(destination = "SaveNotice" ,containerFactory = "jmsListenerContainerQueue")
    public void receiveQueueforSaveNotice(Notice notice) {
       noticeService.NewNotice(notice);



    }
}
