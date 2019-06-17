package com.luo.springcloudeurekaclientdraghook.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "Draghook")
@Table(indexes = {@Index(name = "PERSON_INDX_0", columnList = "createTime") })
public class Draghook implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "biaoti")
    private String name;//拉钩标题
    @Column(name="miaoshu")
    private String desc;//拉钩描述
    private String excRule;//执行频率
    @ElementCollection
    private List<String> excRuleValue;
    private String startDate;
    private String endDate;
    private String passRate;  //单次完成率
    private String approvalPassRate;  //目标完成率
    private String open;//是否公开
    private String userKey;//openId
    private String userName;
    private String userIcon;
    private Date createTime;
    private String vartime;
    private double latitude;//经度
    private double longitude;//维度

    @Transient
    private String distance;

    @Transient
    private String limitTime;
    //如何利用JPA存储List 对象
    //利用中间表来进行表之间的对象的关联
    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "tb_draghook_comment" ,joinColumns = {@JoinColumn(name = "draghook_id")} ,inverseJoinColumns = {@JoinColumn(name = "comment_id")}
    )
    private List<Comment> Comments;//评论


    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "tb_draghook_good" ,joinColumns = {@JoinColumn(name = "draghook_id")} ,inverseJoinColumns = {@JoinColumn(name = "good_id")}
    )
    private List<Good>goods;//点赞


    public Draghook() {
    }


    //给拉钩task添加评论
    public void addComment(Comment comment)
    {
        Comments.add(comment);
    }

    //给拉钩task添加点赞
    public void addGood(Good good)
    {

        goods.add(good);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExcRule() {
        return excRule;
    }

    public void setExcRule(String excRule) {
        this.excRule = excRule;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getApprovalPassRate() {
        return approvalPassRate;
    }

    public void setApprovalPassRate(String approvalPassRate) {
        this.approvalPassRate = approvalPassRate;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public List<String> getExcRuleValue() {
        return excRuleValue;
    }

    public void setExcRuleValue(List<String> excRuleValue) {
        this.excRuleValue = excRuleValue;
    }

    public List<Comment> getComments() {
        return Comments;
    }

    public void setComments(List<Comment> Comments) {
        this.Comments = Comments;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public String getVartime() {
        return vartime;
    }

    public void setVartime(String vartime) {
        this.vartime = vartime;
    }

    @Override
    public String toString() {
        return "Draghook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", excRule='" + excRule + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", passRate='" + passRate + '\'' +
                ", approvalPassRate='" + approvalPassRate + '\'' +
                ", open='" + open + '\'' +
                ", userKey='" + userKey + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", createTime=" + createTime +
                ", stringCreate='" + vartime + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance='" + distance + '\'' +
                ", limitTime='" + limitTime + '\'' +
                ", Comments=" + Comments +
                ", goods=" + goods +
                '}';
    }
}
