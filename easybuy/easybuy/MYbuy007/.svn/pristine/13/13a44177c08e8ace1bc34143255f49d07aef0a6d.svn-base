package com.easybuy.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EasybuyOrderDetail implements Serializable{

	
  /**
	 * 
	 */
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue
private long id;
@Column(name = "orderid")
  private long orderId;
@Column(name = "productid")
  private long productId;
  private long quantity;
  private double cost;

  @Transient
  private EasybuyProduct product;

  public EasybuyProduct getProduct() {
    return product;
  }

  public void setProduct(EasybuyProduct product) {
    this.product = product;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }


  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

}
