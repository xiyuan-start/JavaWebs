package com.easybuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class EasybuyProduct implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  private long id;
  private String name;
  private String description;
  private double price;
  private long stock;
  @Column (name="categorylevel1id")
  private long categoryLevel1Id;
  @Column (name="categorylevel2id")
  private long categoryLevel2Id;
  @Column (name="categorylevel3id")
  private long categoryLevel3Id;
  @Column( name = "filename")
  private String fileName;
  private  String catalog_name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EasybuyProduct)) return false;
    EasybuyProduct that = (EasybuyProduct) o;
    return getId() == that.getId() &&
            Double.compare(that.getPrice(), getPrice()) == 0 &&
            getStock() == that.getStock() &&
            getCategoryLevel1Id() == that.getCategoryLevel1Id() &&
            getCategoryLevel2Id() == that.getCategoryLevel2Id() &&
            getCategoryLevel3Id() == that.getCategoryLevel3Id() &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getFileName(), that.getFileName()) &&
            Objects.equals(getCatalog_name(), that.getCatalog_name());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getName(), getDescription(), getPrice(), getStock(), getCategoryLevel1Id(), getCategoryLevel2Id(), getCategoryLevel3Id(), getFileName(), getCatalog_name());
  }

  public String getCatalog_name() {
    return catalog_name;
  }

  public void setCatalog_name(String catalog_name) {
    this.catalog_name = catalog_name;
  }

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


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }


  public long getCategoryLevel1Id() {
    return categoryLevel1Id;
  }

  public void setCategoryLevel1Id(long categoryLevel1Id) {
    this.categoryLevel1Id = categoryLevel1Id;
  }


  public long getCategoryLevel2Id() {
    return categoryLevel2Id;
  }

  public void setCategoryLevel2Id(long categoryLevel2Id) {
    this.categoryLevel2Id = categoryLevel2Id;
  }


  public long getCategoryLevel3Id() {
    return categoryLevel3Id;
  }

  public void setCategoryLevel3Id(long categoryLevel3Id) {
    this.categoryLevel3Id = categoryLevel3Id;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


}
