package org.harshad.entity;

public class Cart {
	int catregoryId;
	int productId;
	float price;
	String username;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cart(int catregoryId, int productId, float price, String username) {
		super();
		this.catregoryId = catregoryId;
		this.productId = productId;
		this.price = price;
		this.username = username;
	}
	public int getCatregoryId() {
		return catregoryId;
	}
	public void setCatregoryId(int catregoryId) {
		this.catregoryId = catregoryId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
