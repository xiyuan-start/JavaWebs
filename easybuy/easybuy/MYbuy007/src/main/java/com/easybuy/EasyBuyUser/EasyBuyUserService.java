package com.easybuy.EasyBuyUser;

import com.easybuy.entity.EasybuyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EasyBuyUserService {

    @Autowired
    EasyBuyUserDao easyBuyUserDao;


   //用户登录
    public EasybuyUser login(String loginname, String password)
    {

        return  easyBuyUserDao.login(loginname,password);
    }


    //用户注册
    public boolean Register(EasybuyUser user)
    {

    try {
        easyBuyUserDao.save(user);
        return true;
    }catch (Exception e)
    {

        return false;
    }

    }

}
