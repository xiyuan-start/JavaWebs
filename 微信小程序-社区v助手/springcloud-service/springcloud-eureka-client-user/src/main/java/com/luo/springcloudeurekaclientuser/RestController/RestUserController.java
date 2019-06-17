package com.luo.springcloudeurekaclientuser.RestController;

import com.luo.springcloudeurekaclientuser.Service.ConsumerService;
import com.luo.springcloudeurekaclientuser.entity.Consumer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class RestUserController {


    @Autowired
    RestTemplate restTemplate;

    @Resource(name = "ObjectredisTemplate")
    ValueOperations<String,Object> valueOperations;

    @Autowired
    ConsumerService consumerService;



    //减去用户信用值
    @GetMapping("/Reduce/{id}")
    public void Reduce(@PathVariable String id)
    {

     Consumer consumer= consumerService.getConsumerById(id);
     consumer.setPoints(consumer.getPoints()-5);
     consumerService.SaveConsumer(consumer);

    }

    //获取用户信用值
    @GetMapping("/GetUser/{id}")
    @HystrixCommand(fallbackMethod = "fallbackInfo")
    public String GetUser(@PathVariable String id)
    {
        return  consumerService.getConsumerById(id).getPoints().toString();
    }



    public String fallbackInfo(@PathVariable String id)
    {
        return "101";

    }


}
