package org.harshad.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.harshad.dao.ProductDao;
import org.harshad.dao.impl.ProductDaoImplementation;
import org.harshad.entity.Product;
import org.harshad.exceptions.ProductNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String categoryId = request.getParameter("categoryId");
			ProductDao productDao = new ProductDaoImplementation();
			Iterator<Product> allProducts = productDao.getProductsByCategoryId(categoryId);
			try (PrintWriter out = response.getWriter()) {
				out.println("<html><body><table><tr>");
				out.println("<th>Product id</th>");
				out.println("<th>ProductName</th>");
				out.println("<th>Product Price</th>");
				while (allProducts.hasNext()) {
					Product product = allProducts.next();
					out.println("<tr> <td>" + product.getProductId() + "</td>");
					out.println("<td>" + product.getProductName() + "</td>");
					out.println("<td>" + product.getPrice() + "</td></tr>");
				}
				out.println("</tr></table></body></html>");
			}
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}
	}
}
