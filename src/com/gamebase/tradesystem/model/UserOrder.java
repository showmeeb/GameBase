package com.gamebase.tradesystem.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "UserOrder")
@Component
public class UserOrder {
	private int userId;
	private String uuId;
	private int orderId;
	private String orderName;
	private String orderPhone;
	private String orderAddress;
	private int orderPrice;
	private String orderDate;
	private int payStatus;

	public UserOrder() {
	}

	public UserOrder(int userId,String uuId,String orderName,String orderPhone, String orderAddress, int orderPrice, String orderDate,int payStatus) {
		this.userId = userId;
		this.uuId=uuId;
		this.orderName=orderName;
		this.orderPhone=orderPhone;
		this.orderAddress = orderAddress;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.payStatus=payStatus;
	}

	@Column(name = "USERID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name = "uuId")
	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	@Id
	@Column(name = "ORDERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "ORDERNAME")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	@Column(name = "ORDERPHONE")
	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	@Column(name = "ORDERADDRESS")
	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	@Column(name = "ORDERPRICE")
	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Column(name = "ORDERDATE")
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	@Column(name = "PAYSTATUS")
	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	


	

}
