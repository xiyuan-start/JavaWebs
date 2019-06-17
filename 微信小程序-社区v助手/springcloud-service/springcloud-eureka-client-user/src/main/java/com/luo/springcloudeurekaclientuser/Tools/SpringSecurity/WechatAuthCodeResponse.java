package com.luo.springcloudeurekaclientuser.Tools.SpringSecurity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WechatAuthCodeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openid;

    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 请求失败错误码
     */
    private String errcode;

    /**
     * 请求失败错误信息
     */
    private String errmsg;

    /**
     * 会话有效期（以前微信会返回，现在未知）
     */
    @JsonProperty("expires_in")
    private Long expiresIn;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "WechatAuthCodeResponse{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }

}

