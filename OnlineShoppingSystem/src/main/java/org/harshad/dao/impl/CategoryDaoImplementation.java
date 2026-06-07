package org.harshad.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.harshad.dao.CategoryDao;
import org.harshad.entity.Category;
import org.harshad.exceptions.CategoryException;

public class CategoryDaoImplementation implements CategoryDao {
	Connection con;
	
	public CategoryDaoImplementation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "root");
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public Iterator<Category> listAllCategories() throws CategoryException{
		ArrayList<Category> objCategoryList;
		try (PreparedStatement psGetAllCategories = con.prepareStatement("Select * from category");
				ResultSet res = psGetAllCategories.executeQuery()) {
			objCategoryList = new ArrayList<>();
			while(res.next()) {
				Category objCategory=new Category();
				objCategory.setCategoryId(res.getInt(1));
				objCategory.setCategoryName(res.getString(2));
				objCategory.setCategoryDescription(res.getString(3));
				objCategory.setCategoryImageUrl(res.getString(4));
				objCategoryList.add(objCategory);
			}
			return objCategoryList.iterator();
		} catch (SQLException e) {
			throw new CategoryException("PRoblkem in fetching categoryList "+ e.getMessage());
		}
	}

}
