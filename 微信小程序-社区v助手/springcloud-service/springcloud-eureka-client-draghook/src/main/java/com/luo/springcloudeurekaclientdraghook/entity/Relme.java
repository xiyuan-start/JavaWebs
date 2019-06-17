package com.luo.springcloudeurekaclientdraghook.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
* 存放在redis中 从而判定有没提醒
* */
public class Relme implements Serializable {

    public String id; //Relme+openId

    public Set<Integer> synidlist;//任务的id

    public Integer flag=0; //标志位 1有未读消息 0无未读消息

    public Relme() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Integer> getSynidlist() {
        return synidlist;
    }

    public void setSynidlist(Set<Integer> synidlist) {
        this.synidlist = synidlist;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void addsyn(Integer id)
    {
        synidlist.add(id);
    }





}
