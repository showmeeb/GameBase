package com.gamebase.tradesystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Product")
@Component("PD")
public class Product {

	private int productId;
	private String productVideo;
	private String productImg;
	private String productName;
	private String productType;
	private int inventory;
	private int productPrice;
	private String productTag;
	private String productInfo;
	private int searchFreq;

	public Product() {
	};

	public Product(String productImg, String pName, String productType, int inventory, int pPrice, String pTag,
			String pInfo) {
		this.productImg = productImg;
		this.productName = pName;
		this.productType = productType;
		this.inventory = inventory;
		this.productPrice = pPrice;
		this.productTag = pTag;
		this.productInfo = pInfo;

	}

	@Id
	@Column(name = "PRODUCTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "PRODUCTVIDEO")
	public String getProductVideo() {
		return productVideo;
	}

	public void setProductVideo(String productVideo) {
		this.productVideo = productVideo;
	}

	@Column(name = "PRODUCTIMG")
	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "PRODUCTTYPE")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "INVENTORY")
	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Column(name = "PRODUCTPRICE")
	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "PRODUCTTAG")
	public String getProductTag() {
		return productTag;
	}

	public void setProductTag(String productTag) {
		this.productTag = productTag;
	}

	@Column(name = "PRODUCTINFO")
	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	@Column(name = "SEARCHFREQ")
	public int getSearchFreq() {
		return searchFreq;
	}

	public void setSearchFreq(int searchFreq) {
		this.searchFreq = searchFreq;
	}

//	@OneToOne(fetch = FetchType.LAZY,mappedBy = "Product",cascade = CascadeType.ALL)
//	public Game getGame() {
//		return game;
//	}
//	public void setGame(Game game) {
//		this.game = game;
//	}
//	@OneToOne(fetch = FetchType.LAZY,mappedBy = "Product",cascade = CascadeType.ALL)
//	public Host getHost() {
//		return host;
//	}
//	public void setHost(Host host) {
//		this.host = host;
//	}

}
