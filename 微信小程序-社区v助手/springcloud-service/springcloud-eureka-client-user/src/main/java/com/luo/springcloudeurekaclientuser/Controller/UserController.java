package com.luo.springcloudeurekaclientuser.Controller;

import com.luo.springcloudeurekaclientuser.Service.WechatService;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.AppContext;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.WechatAuthCodeResponse;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.WechatAuthenticationResponse;
import com.luo.springcloudeurekaclientuser.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientuser.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientuser.Tools.WebTool.WebService;
import com.luo.springcloudeurekaclientuser.entity.AccountDto;
import com.luo.springcloudeurekaclientuser.entity.Consumer;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {


    @Autowired
    WechatService wechatService;




    //通过request.code 生产sessionID 获取登录凭证
    @PostMapping("/auth")
    public ResponseEntity<WechatAuthenticationResponse> createAuthenticationToken(@RequestBody AccountDto accountDto)
            throws AuthenticationException {
        WechatAuthenticationResponse jwtResponse = wechatService.wechatLogin(accountDto.getCode());
        return ResponseEntity.ok(jwtResponse);
    }



    //将用户的信息 填充到利用请求code而创建的登录账号中
    @PostMapping("/updateConsumerInfo")
    public void updateConsumerInfo(HttpServletRequest httpRequest, @RequestBody Consumer consumer) throws IOException {
        httpRequest.setCharacterEncoding("utf-8");//设置post请求的编码问题.
        System.out.println(consumer);
        wechatService.updateConsumerInfo(consumer);
    }


    //在这里进行用户注销操作
    //根据openID清空redis内容
    @PostMapping("/ConsumerInfologout")
    public void ConsumerInfologout(HttpServletRequest request, HttpServletResponse response)throws Exception
    {

      ResultMent resultMent =new ResultMent();

       try{

           //获取请求头中的数据 进行删除vxSession
           wechatService.cleanConsumerInfo(request);
           resultMent.setStatus(1);
       }catch (Exception e)
       {
          resultMent.setStatus(0);
       };
        SendJson.sendJsonObject(request,response,resultMent);



    }
}
