package com.easybuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class EasybuyProductCategory implements Serializable{

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  private long id;
  private String name;
  @Column(name = "parentid")
  private long parentId;
  private long type;
  @Column(name = "iconclass")
  private String iconClass;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getIconClass() {
    return iconClass;
  }

  public void setIconClass(String iconClass) {
    this.iconClass = iconClass;
  }

}
