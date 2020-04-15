package com.gamebase.tradesystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;
import org.springframework.stereotype.Component;

@Entity
@Table(name ="shoppingCart")
@Component("SC")
public class ShoppingCart {
	
	private int shoppingCartId;
	private int userId;
	private int productId;
	private String productImg;
	private String productName;
	private int productPrice;
	private int amount;
	
	public ShoppingCart() {}
	public ShoppingCart(int shoppingCartId,int userId,int productId,String productImg,String productName,int productPrice,int amount){
		this.shoppingCartId=shoppingCartId;
		this.userId=userId;
		this.productId=productId;
		this.productImg=productImg;
		this.productName=productName;
		this.productPrice=productPrice;
		this.amount=amount;
	}
	
	@Id@Column(name="SHOPPINGCARTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getShoppingCartId() {
		return shoppingCartId;
	}
	@Column(name="USERID")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setShoppingCartId(int shoppingCart) {
		this.shoppingCartId = shoppingCart;
	}
	@Column(name="PRODUCTID")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Column(name="PRODUCTIMG")
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
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
