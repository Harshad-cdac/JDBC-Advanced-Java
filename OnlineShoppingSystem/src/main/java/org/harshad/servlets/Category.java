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

@WebServlet("/category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PrintWriter out=response.getWriter();
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "cdac");
					PreparedStatement psGetAllCategories = con.prepareStatement("Select * from category");
					ResultSet rs = psGetAllCategories.executeQuery()) {
				out.println("<html> <body> <table> <tr> ");
				out.println("<th>"+"CategoryId"+"</th>");
				out.println("<th>"+"CategoryName"+"</th>");
				out.println("<th>"+"CategoryDescription"+"</th>");
				out.println("<th>"+"CategoryImageUrl"+"</th>");
				out.println("</tr>");
				while(rs.next()) {
					out.println("<tr>");
					out.println("<td>"+rs.getInt(1)+"</td>");
					out.println("<td><a href='products?categoryId="+rs.getInt(1)+"'/>"+rs.getString(2)+"</a></td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("</tr>");
				}
				out.println("</table> </body></html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
