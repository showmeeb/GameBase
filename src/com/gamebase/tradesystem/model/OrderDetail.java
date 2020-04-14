package com.gamebase.tradesystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "OrderDetail")
@Component
public class OrderDetail {
	private int orderId;
	private int orderDetailId;
	private int productId;
	private String productName;
	private int productPrice;
	private int amount;
	
	
	public OrderDetail() {}
	public OrderDetail(int orderId,int productId,String productName,int productPrice,int amount) {
		this.orderId=orderId;
		this.productId=productId;
		this.productName=productName;
		this.productPrice=productPrice;
		this.amount=amount;
	}
	@Column(name="ORDERID")
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Id @Column(name="ORDERDETAILID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	@Column(name="PRODUCTID")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Column(name="PRODUCTNAME")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name="PRODUCTPRICE")
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	@Column(name="AMOUNT")
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
