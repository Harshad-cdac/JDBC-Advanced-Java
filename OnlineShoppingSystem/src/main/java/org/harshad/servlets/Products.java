package org.harshad.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			Class.forName("com.mysql.cj.jdbc.Driver");
			PrintWriter out = response.getWriter();
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "cdac")) {
				String categoryId = request.getParameter("categoryId");
				try (PreparedStatement pstmt = con.prepareStatement("select * from products where categoryId=?")) {
					pstmt.setString(1, categoryId);
					try (ResultSet rs = pstmt.executeQuery()) {
						out.println("<html><body><table><tr>");
						out.println("<th>Product id</th>");
						out.println("<th>ProductName</th>");
						out.println("<th>Product Price</th>");
						while (rs.next()) {
							out.println("<tr> <td>" + rs.getInt(1) + "</td>");
							out.println("<td>" + rs.getString(3) + "</td>");
							out.println("<td>" + rs.getInt(4) + "</td></tr>");
						}
						out.println("</tr></table></body></html>");
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
