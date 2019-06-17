package com.luo.springcloudeurekaclientuser.Tools.SpringSecurity;

public class AppContext implements AutoCloseable {

    private static final ThreadLocal<String> CURRENT_CONSUMER_WECHAT_OPENID = new ThreadLocal<>();

    public AppContext(String wechatOpenid) {
        CURRENT_CONSUMER_WECHAT_OPENID.set(wechatOpenid);
    }

    @Override
    public void close() {
        CURRENT_CONSUMER_WECHAT_OPENID.remove();
    }

    public static String getCurrentUserWechatOpenId() {
        return CURRENT_CONSUMER_WECHAT_OPENID.get();
    }

}