package com.easybuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class EasybuyUserAddress implements Serializable{

  
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  private long id;
  @Column(name = "userid")
  private long userId;
  private String address;
  @Column(name = "createtime")
  private java.sql.Timestamp createTime;
  @Column(name = "isdefault")
  private long isDefault;
  private String remark;


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


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(long isDefault) {
    this.isDefault = isDefault;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
