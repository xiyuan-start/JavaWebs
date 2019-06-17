package com.luo.springcloudeurekaclientpark.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CarPark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;//停车场名字
    private Integer price;//停车场价格
    private String location;//停车场位置
    private Integer totalpark;//总车位数
    private Integer sparepark;//剩余车位
    private String imgUrl;//图片
    private double latitude;//经度
    private double longitude;//维度
    @Transient
    private double dis;

    @Transient
    private String distance;
    public CarPark() {
    }

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void parking()
    {

        sparepark--;
    }
    public void leave()
    {
        sparepark++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTotalpark() {
        return totalpark;
    }

    public void setTotalpark(Integer totalpark) {
        this.totalpark = totalpark;
    }

    public Integer getSparepark() {
        return sparepark;
    }

    public void setSparepark(Integer sparepark) {
        this.sparepark = sparepark;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "CarParkDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", totalpark=" + totalpark +
                ", sparepark=" + sparepark +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
