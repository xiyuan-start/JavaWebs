package com.luo.springcloudeurekaclientnotice.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminController {



    @RequestMapping("/NoticeAdmin")
    public  void  NoticeAdmin(HttpServletRequest request, HttpServletResponse response)throws IOException
    {

        response.sendRedirect("https://www.chengzong.top/weicms/index.php?s=/addon/Cms/Cms/lists.html");


    }




    @RequestMapping("/OpinionAdmin")
    public void OpinionAdmin(HttpServletRequest request, HttpServletResponse response)throws IOException
    {



        response.sendRedirect("http://www.chengzong.top:8080/NoticeAdmin/index.html");

    }

}
