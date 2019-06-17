package com.luo.springcloudeurekaclientuser.Tools.SpringSecurity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/*
* 用这个类 返回access_token
*
* */
public class WechatAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    @JsonProperty("access_token")
    private final String accessToken;

    private  String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public WechatAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccess_token() {
        return this.accessToken;
    }

}
