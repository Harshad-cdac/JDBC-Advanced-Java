package org.harshad.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.harshad.dao.CategoryDao;
import org.harshad.dao.impl.CategoryDaoImplementation;
import org.harshad.exceptions.CategoryException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			HttpSession session=request.getSession(false);
			if(session==null) {
				response.sendRedirect("login.html");
				return;
			}
			CategoryDao categoryDao =new CategoryDaoImplementation();
			try {
				Iterator<org.harshad.entity.Category> categoryList=categoryDao.listAllCategories();
				out.println("<html> <body> <table> <tr> ");
				out.println("<th>" + "CategoryId" + "</th>");
				out.println("<th>" + "CategoryName" + "</th>");
				out.println("<th>" + "CategoryDescription" + "</th>");
				out.println("<th>" + "CategoryImageUrl" + "</th>");
				out.println("</tr>");
				while (categoryList.hasNext()) {
						org.harshad.entity.Category category=categoryList.next();
					out.println("<tr>");
					out.println("<td>" + category.getCategoryId() + "</td>");
					out.println("<td><a href='products?categoryId=" + category.getCategoryId()+ "'/>" + category.getCategoryName()+ "</a></td>");
					out.println("<td>" + category.getCategoryName() + "</td>");
					out.println("<td>" + category.getCategoryDescription() + "</td>");
					out.println("</tr>");
				}
				out.println("</table> </body></html>");
			} catch (CategoryException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

}
