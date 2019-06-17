package com.luo.springcloudeurekaclientpark.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CarOrder implements Serializable {

    @Id
    private String id;//订单号

    private String userId;//用户openId

    private String username;//用户昵称

    @Column(name = "location")
    private String location;//停车场名称

    private Integer hours;//预约的时长

    @Column(name = "start")
    private String startDate;//开始时间
    @Column( name="end")
    private String endDate;//结束时间

    @Column(name = "money")
    private Integer parkPrice; //应收价格

    private String plateNumber;//车牌

    private Integer parkId;//停车场Id

    private Integer type;// 1.表示预约中 2.成功停车 3.已取车 4.过期失效 5.提示删除订单

    @Transient
    private String loadingtime;//用来进行倒计时

    public CarOrder() {
    }


    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(Integer parkPrice) {
        this.parkPrice = parkPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getParkprice() {
        return parkPrice;
    }

    public void setParkprice(Integer parkprice) {
        this.parkPrice = parkprice;
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLoadingtime() {
        return loadingtime;
    }

    public void setLoadingtime(String loadingtime) {
        this.loadingtime = loadingtime;
    }

    @Override
    public String toString() {
        return "CarOrder{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", hours=" + hours +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", parkPrice=" + parkPrice +
                ", plateNumber='" + plateNumber + '\'' +
                ", parkId=" + parkId +
                ", type=" + type +
                '}';
    }
}
