package com.luo.Controller;



import com.luo.Dao.UserDao;
import com.luo.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    UserDao userDao;

    @RequestMapping("/1")
    public String mian() {


        System.out.println("获取所有用户");

        List<UserEntity>list=userDao.getUser();

        for(UserEntity userEntity:list)
        {

            System.out.println(userEntity);
        }

        System.out.println("获取id为1的用户");
        System.out.println(userDao.getUserbyId(1));

        System.out.println("访问主页");
        return "index";
    }
}
