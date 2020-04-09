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
@Table(name="Game")
@Component
public class Game {
	
	
	private int productId;
	private int gameId;
	private String gameImg;
	private String gameName;
	private String gameType;
	private String gamePlatform;
	private String gameLevel;
	private Product product;
	
	public Game () {}
	public Game (String gameImg,String gameName,String gameType,String gamePlatform,String gameLevel) {
		this.gameImg=gameImg;
		this.gameName=gameName;
		this.gameType=gameType;
		this.gamePlatform=gamePlatform;
		this.gameLevel=gameLevel;
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
	@Id@Column(name="GAMEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	@Column(name="GAMEIMG")
	public String getGameImg() {
		return gameImg;
	}
	public void setGameImg(String gameImg) {
		this.gameImg = gameImg;
	}
	@Column(name="GAMENAME")
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	@Column(name="GAMETYPE")
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	@Column(name="GAMEPLATFORM")
	public String getGamePlatform() {
		return gamePlatform;
	}
	public void setGamePlatform(String gamePlatform) {
		this.gamePlatform = gamePlatform;
	}
	@Column(name="GAMELEVEL")
	public String getGameLevel() {
		return gameLevel;
	}
	public void setGameLevel(String gameLevel) {
		this.gameLevel = gameLevel;
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
