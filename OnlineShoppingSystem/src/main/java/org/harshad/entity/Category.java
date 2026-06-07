package org.harshad.entity;

public class Category {
	int categoryId;
	String CategoryName;
	String CategoryDescription;
	String CategoryImageUrl;
	
	public Category() {
	
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getCategoryDescription() {
		return CategoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}
	public String getCategoryImageUrl() {
		return CategoryImageUrl;
	}
	public void setCategoryImageUrl(String categoryImageUrl) {
		CategoryImageUrl = categoryImageUrl;
	}
}
