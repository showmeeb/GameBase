package com.gamebase.tradesystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="ProductType")
@Component
public class ProductType {
	
	private int productTypeId;
	private String productType;
	private Product product;
	
	public ProductType() {}
	public ProductType(String productType) {
		this.productType=productType;
	}
	
	@Id@Column(name="PRODUCTTYPEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	@Column(name="PRODUCTTYPE")
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUCTTYPEID")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
