package com.easybuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class EasybuyUser implements Serializable{

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  private long id;
@Column(name = "loginname")
  private String loginName;
@Column(name = "username")
  private String userName;
  private String password;
  private long sex;
  @Column(name = "identitycode")
  private String identityCode;
  private String email;
  private String mobile;

  private long type;

  public EasybuyUser()
  {

  }

  public EasybuyUser( String loginName, String userName, String password, long sex, String identityCode, String email, String mobile) {

    this.loginName = loginName;
    this.userName = userName;
    this.password = password;
    this.sex = sex;
    this.identityCode = identityCode;
    this.email = email;
    this.mobile = mobile;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getSex() {
    return sex;
  }

  public void setSex(long sex) {
    this.sex = sex;
  }


  public String getIdentityCode() {
    return identityCode;
  }

  public void setIdentityCode(String identityCode) {
    this.identityCode = identityCode;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

@Override
public String toString() {
	return "EasybuyUser [id=" + id + ", loginName=" + loginName + ", userName=" + userName + ", password=" + password
			+ ", sex=" + sex + ", identityCode=" + identityCode + ", email=" + email + ", mobile=" + mobile + ", type="
			+ type + "]";
}

  
}
