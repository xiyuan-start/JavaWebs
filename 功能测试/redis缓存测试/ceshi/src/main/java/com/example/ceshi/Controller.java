package com.example.ceshi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Service service;
    @RequestMapping("/")
    public void index()
    {

        User a= service.ceshi();
        System.out.println("这是主页"+a);

    }

}
