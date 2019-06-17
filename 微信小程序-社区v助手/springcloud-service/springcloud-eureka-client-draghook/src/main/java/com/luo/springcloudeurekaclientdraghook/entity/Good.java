package com.luo.springcloudeurekaclientdraghook.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Good implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "GoodId")
    private String key;//点赞人的openID
    private String name;//点赞人的昵称
    private String icon;//点赞人的头像

    public Good(String key, String name, String icon) {
        this.key = key;
        this.name = name;
        this.icon = icon;
    }

    public Good() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
