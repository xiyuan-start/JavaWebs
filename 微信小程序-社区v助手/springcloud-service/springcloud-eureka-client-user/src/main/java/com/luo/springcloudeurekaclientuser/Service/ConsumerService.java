package com.luo.springcloudeurekaclientuser.Service;

import com.luo.springcloudeurekaclientuser.Dao.ConsumerDao;
import com.luo.springcloudeurekaclientuser.entity.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


    @Autowired
    ConsumerDao consumerDao;

    //保存用户信息到数据库
    public void SaveConsumer(Consumer consumer)
    {
        consumerDao.save(consumer);
    }

    //根据用户的openId获取用户信息
    public Consumer getConsumerById(String openId)
    {
        return consumerDao.getByOpenId(openId);
    }
}
