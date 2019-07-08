package com.luo.Controller;


import com.luo.Dao.UserDao;
import com.luo.Model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/1")
    public String mian() {


        List<User> list = userDao.getUser();


        for (User user : list) {

            System.out.println(user);
        }


        System.out.println("访问主页");
        return "index";
    }
}
