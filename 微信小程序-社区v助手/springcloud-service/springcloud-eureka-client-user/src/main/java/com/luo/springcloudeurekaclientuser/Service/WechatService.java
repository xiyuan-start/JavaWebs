package com.luo.springcloudeurekaclientuser.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.AppContext;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.WechatAuthCodeResponse;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.WechatAuthProperties;
import com.luo.springcloudeurekaclientuser.Tools.SpringSecurity.WechatAuthenticationResponse;
import com.luo.springcloudeurekaclientuser.entity.Consumer;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class    WechatService {

    @Resource(name = "ObjectredisTemplate")
    ValueOperations<String,Object> valueOperations;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ActiveMQService activeMQService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    private static final Logger LOGGER = LoggerFactory.getLogger(WechatService.class);



    /**
     * 服务器第三方session有效时间，单位秒, 默认3天
     */
    private static final Long EXPIRES = 259200L;

    private RestTemplate wxAuthRestTemplate = new RestTemplate();

    @Autowired
    private WechatAuthProperties wechatAuthProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //借助code 进行用户登录
    public WechatAuthenticationResponse wechatLogin(String code) {
        WechatAuthCodeResponse response = getWxSession(code);

        System.out.println("获取到的openId88"+response.getOpenid());
        String wxOpenId = response.getOpenid();
        String wxSessionKey = response.getSessionKey();
        Consumer consumer = new Consumer();
        consumer.setOpenId(wxOpenId);
        //检测用户是否为新登录用户
        loginOrRegisterConsumer(consumer);

        Long expires = response.getExpiresIn();
        String thirdSession = create3rdSession(wxOpenId, wxSessionKey, expires);
        WechatAuthenticationResponse wechatAuthenticationResponse=new WechatAuthenticationResponse(thirdSession);
        wechatAuthenticationResponse.setOpenId(wxOpenId);
        return wechatAuthenticationResponse;
    }

    public WechatAuthCodeResponse getWxSession(String code) {
        String urlString = "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}";
        String response = wxAuthRestTemplate.getForObject(
                wechatAuthProperties.getSessionHost() + urlString, String.class,
                wechatAuthProperties.getAppId(),
                wechatAuthProperties.getSecret(),
                code,
                wechatAuthProperties.getGrantType());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(WechatAuthCodeResponse.class);
        WechatAuthCodeResponse res;
        try {
            res = reader.readValue(response);
        } catch (IOException e) {
            res = null;
            LOGGER.error("反序列化失败", e);
        }
        LOGGER.info(response);
        if (null == res) {
            throw new RuntimeException("调用微信接口失败");
        }
        if (res.getErrcode() != null) {
            throw new RuntimeException(res.getErrmsg());
        }
        res.setExpiresIn(res.getExpiresIn() != null ? res.getExpiresIn() : EXPIRES);
        return res;
    }

    //生成vxSession 并且设置失效时间
    public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires) {
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);
        stringRedisTemplate.opsForValue().set(thirdSessionKey, sb.toString(), expires, TimeUnit.SECONDS);
        return thirdSessionKey;
    }


    //查询mysql 获取用户信息
    //如果不存在 上传到mysql
    private void loginOrRegisterConsumer(Consumer consumer) {

        Consumer consumer1 = consumerService.getConsumerById(consumer.getOpenId());
        if (null == consumer1) {
         System.out.println("该用户为新用户，上传到mysql");
        activeMQService.sendMessageforSaveConsumer(consumer);

         //
        }


    }


    //更新mysql 中保存的mysql信息
    //对微信获取的基础信息进行更新
    public void updateConsumerInfo(Consumer consumer) {

        String openId=AppContext.getCurrentUserWechatOpenId();
        Consumer consumerExist=consumerService.getConsumerById(openId);
        consumerExist.setOpenId(openId);
        consumerExist.setUpdatedBy(1L);
        consumerExist.setUpdatedAt(System.currentTimeMillis());
        consumerExist.setGender(consumer.getGender());
        consumerExist.setAvatarUrl(consumer.getAvatarUrl());
        consumerExist.setNickName(consumer.getNickName());
        consumerExist.setCity(consumer.getCity());
       activeMQService.sendMessageforSaveConsumer(consumerExist);
    }


    // 清空清空redis中的wxSession
    public void cleanConsumerInfo(HttpServletRequest request)throws IOException
    {

        request.setCharacterEncoding("utf-8");//设置post请求的编码问题.
        //获取请求头部分的Authorization

        String authHeader = request.getHeader("auth");
        final String thirdSessionId = authHeader.substring(tokenHead.length());
        stringRedisTemplate.delete(thirdSessionId);


    }



}
