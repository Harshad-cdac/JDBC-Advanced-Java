package org.harshad.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement psInsertUser;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "root");
			psInsertUser = con
					.prepareStatement("Insert into users (username,password,name,email,city) values(?,?,?,?,?)");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String city = request.getParameter("city");

			psInsertUser.setString(1, username);
			psInsertUser.setString(2, password);
			psInsertUser.setString(3, name);
			psInsertUser.setString(4, email);
			psInsertUser.setString(5, city);
			int isInserted =psInsertUser.executeUpdate();
			if(isInserted!=0) {
//				response.getWriter().println("Registration successful");
				response.sendRedirect("login.html");
			}else {
				response.getWriter().println("Something went wrong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override 
	public void destroy() {
		try {
			if(con!=null) {
				con.close();
			}
			if(psInsertUser!=null) {
				psInsertUser.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
