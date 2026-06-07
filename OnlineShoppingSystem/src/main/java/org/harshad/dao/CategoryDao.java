package org.harshad.dao;

import java.util.Iterator;

import org.harshad.entity.Category;
import org.harshad.exceptions.CategoryException;

public interface CategoryDao {
	Iterator<Category> listAllCategories() throws CategoryException;
}
