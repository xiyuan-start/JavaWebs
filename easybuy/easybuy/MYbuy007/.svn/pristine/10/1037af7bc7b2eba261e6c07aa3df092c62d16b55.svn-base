package com.easybuy.other;

import com.easybuy.entity.EasybuyUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//防止直接输入 jsp 或者 访问Controller
//这句不起作用 将导致css、js等视图资源被拦截 使页面无法正常显示
//使用bean 的方式来设置拦截器

public class MyFilter implements Filter {



    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;

        //通过查看是否 在session中有没有admin 信息来判断是否是违法登录

        EasybuyUser admin =(EasybuyUser) req.getSession().getAttribute("loginUser");

        if(admin == null) {


          //  res.setContentType("text/html;charset=utf-8");
            PrintWriter out = res.getWriter();
            out.print("<script>top.location.href='" + req.getContextPath() + "/Login?action=toLogin';</script>");
            out.flush();
            out.close();

        }

        //重新发送 经过处理后的 request 和response
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }





}
