package com.easybuy.EasyBuyUser;

import com.easybuy.EasybuyProduct.EasybuyCart.ShoppingCart;
import com.easybuy.entity.EasybuyUser;
import com.easybuy.other.Isillegal;
import com.easybuy.other.ResultMent;
import com.easybuy.transform.SecurityUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class mainController {

    @Autowired
    EasyBuyUserService dao;

    @Resource(name = "ObjectredisTemplate")
    ValueOperations<String,Object> valueOperations;

    //进入主页
    @RequestMapping("/")
    public String main(HttpServletRequest request) {

        String ip =request.getRemoteAddr();
        //根据ip地址进行免密登录
        //可以经一歩在redis 用status避免2地登录
         EasybuyUser user=(EasybuyUser) valueOperations.get(ip);

         if(user!=null)
         {
             request.getSession().setAttribute("loginUser",user);
             request.getSession().setAttribute("Userid",user.getId());
         }

        return "pre/index";
    }


    //跳转到登录界面
    //
    @RequestMapping("/Login")
    public String tologin(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(required = false) String loginName,
                          @RequestParam (required = false)String password,
                          @RequestParam (required = false)String captcha,
                          @RequestParam (required = true)String action
    ) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        ResultMent resultMent = new ResultMent();
        //进行登录
        if ("toLogin".equals(action)) {
            request.getRequestDispatcher("pre/login.jsp").forward(request, response);

        }else if ("login".equals(action))
        {

            if(Isillegal.getIsillegal().doIsillegal(loginName))//判断输入是否违法
            {
                //判断验证码是否正确
                String SessionCaptcha =request.getSession().getAttribute("captcha").toString();
                if(captcha.equals(SessionCaptcha))
                {
                    System.out.println("name"+loginName);
                    System.out.println("passeprd"+password);
                    //验证码正确
                    EasybuyUser user =dao.easyBuyUserDao.login(loginName,SecurityUtils.md5Hex3(password));
                    if (user!=null)
                    {
                        //登录成功
                        resultMent.setStatus(1);
                        //将用户信息放入session
                        request.getSession().setAttribute("loginUser",user);
                        request.getSession().setAttribute("Userid",user.getId());

                        //用户信息与ip进行绑定
                        valueOperations.set(request.getRemoteAddr(),user);


                        //利用redis 读取购物车信息
                        ShoppingCart cart=(ShoppingCart) valueOperations.get(user.getLoginName());

                        if (cart!=null)
                        {
                            request.getSession().setAttribute("cart",cart);
                        }

                    }
                    else
                    {
                        //用户不存在
                         resultMent.setStatus(2);
                    }

                }
                else
                {
                    //验证码错误
                    resultMent.setStatus(3);
                }



            }else
            {
                //输入违法报错
                  resultMent.setStatus(4);
            }



        }else if("loginOut".equals(action))
        {
            //注销用户
            EasybuyUser easybuyUser =(EasybuyUser) request.getSession().getAttribute("loginUser");
            if(easybuyUser!=null)
            {
                request.getSession().removeAttribute("loginUser");
            }
            //清空ip绑定
            valueOperations.set(request.getRemoteAddr(),null);
            return "pre/index";

        }

        PrintWriter out =response.getWriter();
        System.out.println(resultMent.getStatus());
        JSONObject jsonObject =JSONObject.fromObject(resultMent);
        out.print(jsonObject);

        out.flush();
        out.close();
        return "pre/index";
    }





    //用户注册
    @RequestMapping("/Register")
    public String Register(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");

        if ("toRegister".equals(action)) {
            //跳转到注册页面
            return "pre/register";

        } else if ("saveUserToDatabase".equals(action)) {
            //进行登录操作
            //status==1 跳转到登录界面
            //必填项
            String loginName = request.getParameter("loginName");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String sex = request.getParameter("sex");

            //非必填
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String identityCode = request.getParameter("identityCode");



            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            EasybuyUser user = new EasybuyUser();
            user.setLoginName(loginName);
            user.setUserName(userName);
            user.setPassword(SecurityUtils.md5Hex3(password));
            user.setSex(Long.valueOf(sex));

            user.setType(1);
            if (email == null) {
                user.setEmail("");
            } else {
                user.setEmail(email);
            }
            if (mobile == null) {
                user.setMobile("");
            } else {
                user.setMobile(mobile);
            }

            if (identityCode == null) {
                user.setIdentityCode("");
            } else {
                user.setIdentityCode(identityCode);
            }

            System.out.println(user);


            if (dao.Register(user)) {
                //注册成功

                jsonObject.put("status",1);
                out.print(jsonObject);
                out.flush();
                out.close();


            } else {
                //注册失败重新注册
                jsonObject.put("status", 2);


                out.print(jsonObject);
                out.flush();
                out.close();
                return "pre/register";
            }
        }


        //返回错误页面
        return "1";
    }

}
