package org.harshad.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.harshad.dao.Cart;
import org.harshad.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session == null) {
				response.sendRedirect("login.html");
				return;
			}
			Cart allProduct = (Cart) session.getAttribute("cart");
			if (allProduct != null) {
				try (PrintWriter out = response.getWriter();
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "root");
						PreparedStatement pInsertIntoCart = con.prepareStatement(
								"insert into cart (categoryId,productId,productName,price,username)values (?,?,?,?,?)")) {
					Iterator<Product> allListItems = allProduct.listCart();
					while (allListItems.hasNext()) {
						Product objProduct = allListItems.next();
						pInsertIntoCart.setInt(1, objProduct.getCategoryId());
						pInsertIntoCart.setInt(2, objProduct.getProductId());
						pInsertIntoCart.setString(3, objProduct.getProductName());
						pInsertIntoCart.setFloat(4, objProduct.getPrice());
						String username = (String) session.getAttribute("username");
						pInsertIntoCart.setString(5, username);
						int isInsertedOrNot = pInsertIntoCart.executeUpdate();
						if (isInsertedOrNot != 0) {
							out.println("Inserted");
						} else {
							out.println("Not inserted");
						}
					}
				}
			}
			session.invalidate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
