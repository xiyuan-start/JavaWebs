package com.easybuy.other;


import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/*
 *该类的功能以及特点描述：电影订票系统的验证码获取的Controller
 *该类是否被编译测试：否
 *@see(与之相关联的类)：   来源：
 *                     中间：
 *                     去处：
 *开发公司或单位：成007个人
 *版权：成007

 *@author(作者)：成007
 *@since（该文件使用的jdk）：JDK1.8
 *@version（版本）：1.0
 *@数据库查询方式：mysql+hibernate
 *@date(开发日期)：2018/6/20
 *改进：
 *最后更改日期：
 */
@Controller
public class CaptchaController implements Serializable {



    @RequestMapping("/generateCaptcha")
    public void getCaptchaController(HttpServletRequest req, HttpServletResponse res) throws IOException
    {

        // 设置两个响应头
        //返回值类型时 png格式的图片
        res.setContentType("image/png");
        //验证码不要进行缓存
        res.setHeader("cache", "no-cache");

        OutputStream os = res.getOutputStream();

        // 值随机,宽度110px,高度40px
        //可以根据需要修改 参数设置定制化的化的验证码
        CaptchaService captchaService = new CaptchaService(110, 40);

        //生产验证码图片  通过outputStream的方式进行发送
        String captcha = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", os);
        os.flush();

        if(os != null) {
            os.close();
            os = null;
        }
        //将图像的验证码存贮在session 在账号验证时对其进行验证
        req.getSession().setAttribute("captcha", captcha);
    }
    }

