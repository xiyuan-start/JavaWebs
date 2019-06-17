package com.luo.springcloudeurekaclientuser.Tools.SpringSecurity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:jst.properties"} ,encoding="UTF-8")
@Component
public class WechatAuthProperties {

    @Value("${auth.wechat.sessionHost}")
    private String sessionHost;

    @Value("${auth.wechat.appId}")
    private String appId;

    @Value("${auth.wechat.secret}")
    private String secret;

    @Value("${auth.wechat.grantType}")
    private String grantType;

    //省略getter setter


    public String getSessionHost() {
        return sessionHost;
    }

    public void setSessionHost(String sessionHost) {
        this.sessionHost = sessionHost;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}

