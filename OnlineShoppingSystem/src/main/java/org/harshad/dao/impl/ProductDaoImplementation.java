package org.harshad.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.harshad.dao.ProductDao;
import org.harshad.entity.Product;
import org.harshad.exceptions.ProductNotFoundException;

public class ProductDaoImplementation implements ProductDao {
	Connection con;
	public ProductDaoImplementation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Iterator<Product> getProductsByCategoryId(String categoryId) throws ProductNotFoundException {
		try (PreparedStatement pGetProductByCategoryId = con.prepareStatement("select * from products where categoryId=?")) {
			pGetProductByCategoryId.setString(1, categoryId);
			try (ResultSet res = pGetProductByCategoryId.executeQuery()) {
				List<Product> allProductsList=new ArrayList<>();
				while(res.next()) {
					Product product=new Product();
					product.setProductId(res.getInt(1));
					product.setProductName(res.getString(2));
					product.setCategoryId(res.getInt(3));
					product.setPrice(res.getFloat(4));
					allProductsList.add(product);
				}
				return allProductsList.iterator();
			}
		} catch (SQLException e) {
			throw new ProductNotFoundException("Products not found for the given category "+e.getMessage());
		}
	}

}
