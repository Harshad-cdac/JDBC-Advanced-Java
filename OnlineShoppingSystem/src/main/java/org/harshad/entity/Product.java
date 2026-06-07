package org.harshad.entity;

public class Product {
	int productId;
	String productName;
	int categoryId;
	float price;
	
	public Product() {
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price= price;
	}
	
	
}
