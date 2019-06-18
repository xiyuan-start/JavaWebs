package com.example.ceshi;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@org.springframework.stereotype.Service
public class Service {

    @Cacheable(value = "id",key = "'id'")
    public User ceshi()
    {

        User user =new User("luo","123");
        System.out.println("这里被调用");
        return user;
    }

    @CacheEvict(value = "id",key = "'id'")
    public void ceshi1()
    {

    }
}
