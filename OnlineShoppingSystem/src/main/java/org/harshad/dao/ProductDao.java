package org.harshad.dao;

import java.util.Iterator;

import org.harshad.entity.Product;
import org.harshad.exceptions.ProductNotFoundException;

public interface ProductDao {
	Iterator<Product> getProductsByCategoryId(String categoryId) throws ProductNotFoundException;
}
