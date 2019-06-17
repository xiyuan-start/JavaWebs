package com.easybuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Entity
public class EasybuyOrder implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  private long id;
	@Column(name = "userid")
  private long userId;
  @Column(name = "loginname")
  private String loginName;
  @Column(name ="useraddress")
  private String userAddress;
  @Column(name = "createtime")
  private java.sql.Timestamp createTime;
  private double cost;
  @Column(name = "serialnumber")
  private String serialNumber;

  @Transient
  private List<EasybuyOrderDetail>orderDetailList;


  public List<EasybuyOrderDetail> getOrderDetailList() {
    return orderDetailList;
  }

  public void setOrderDetailList(List<EasybuyOrderDetail> orderDetailList) {
    this.orderDetailList = orderDetailList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }


  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }


  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

}
