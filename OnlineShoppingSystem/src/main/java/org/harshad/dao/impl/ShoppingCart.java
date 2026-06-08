package org.harshad.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.harshad.dao.Cart;
import org.harshad.entity.Product;

public class ShoppingCart implements Cart {
	ArrayList<Product> allProductList = new ArrayList<>();

	@Override
	public void addToCart(Product product) {
		allProductList.add(product);

	}

	@Override
	public Iterator<Product> listCart() {

		return allProductList.iterator();
	}

}
