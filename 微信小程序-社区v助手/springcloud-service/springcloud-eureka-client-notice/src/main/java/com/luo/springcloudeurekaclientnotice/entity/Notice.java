package com.luo.springcloudeurekaclientnotice.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Notice implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String date;//创建建议的时间
    private String content;

    private String name;//存储用户nickname
    private String userIcon;//存储用户头像
    @Transient
    private String limitTime;
    @ElementCollection
    private List<String> images;

    private Integer status=0;// 1表示已解决 0表示为解决  2表示官方拒绝

    private String solution;

    //如何利用JPA存储List 对象
    //利用中间表来进行表之间的对象的关联
    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "tbc_notice_comment" ,joinColumns = {@JoinColumn(name = "notice_id")} ,inverseJoinColumns = {@JoinColumn(name = "comment_id")}
    )
    private List<Comment> Comments =new ArrayList<Comment>();//评论


    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "tba_notice_good" ,joinColumns = {@JoinColumn(name = "notice_id")} ,inverseJoinColumns = {@JoinColumn(name = "good_id")}
    )
    private List<Good>goods=new ArrayList<Good>();//点赞


    public Notice() {
    }



    //给意见task添加评论
    public void addComment(Comment comment)
    {
        if(Comments!=null)
        {
            Comments.add(comment);
        }


    }

    //给意见task添加点赞
    public void addGood(Good good)
    {

        if(goods!=null)
        {
            goods.add(good);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Comment> getComments() {
        return Comments;
    }

    public void setComments(List<Comment> comments) {
        Comments = comments;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", Comments=" + Comments +
                ", goods=" + goods +
                '}';
    }
}
