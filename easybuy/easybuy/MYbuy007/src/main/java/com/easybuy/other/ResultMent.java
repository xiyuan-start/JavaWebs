package com.easybuy.other;


import java.io.Serializable;

//用这个类来管理返回结果
public class ResultMent implements Serializable {

    private int status;
    private Object data;
    private String message="操作成功";

    public ResultMent()
    {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}