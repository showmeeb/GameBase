package com.gamebase.tradesystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Host")
@Component
public class Host {
	private int productId;
	private int hostId;
	private String hostImg;
	private String hostName;
	private Product product;

	public Host() {}
	public Host(String hostImg,String hostName) {
		this.hostImg=hostImg;
		this.hostName=hostName;
	}
	
	
	@GenericGenerator(name = "generator",strategy = "foreign",
			parameters = @Parameter(name="property",value = "Product"))
	@Id @GeneratedValue(generator = "generator")
	@Column(name = "PRODUCTID")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Id@Column(name="HOSTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	@Column(name="HOSTIMG")
	public String getHostImg() {
		return hostImg;
	}
	public void setHostImg(String hostImg) {
		this.hostImg = hostImg;
	}
	@Column(name="HOSTNAME")
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTID")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
