package com.luo.springcloudeurekaclientnotice.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
* RestController相当于Controller+ResponseBody的集合
*使用ResponseBody 只会返回json 不会返回jsp
* */
@RestController
public class RestNoticeContrller {


    //测试
    //根据id号获取
    @GetMapping("/notice/{id}")
    public String findOrderById(@PathVariable String id)
    {
        return "从公告组件获取内容成功";
    }





}
