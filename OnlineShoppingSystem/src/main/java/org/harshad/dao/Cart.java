package org.harshad.dao;

import java.util.Iterator;

import org.harshad.entity.Product;

public interface Cart {
	void addToCart(Product product);
	public Iterator<Product> listCart();
}
