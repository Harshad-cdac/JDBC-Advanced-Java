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

@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    doPost(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac_170", "root", "cdac")) {
				PrintWriter out=response.getWriter();
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				try (PreparedStatement psAuthenticateStatement = con
						.prepareStatement("Select * from users where username=? and  password=?")) {
					psAuthenticateStatement.setString(1, username);
					psAuthenticateStatement.setString(2, password);
					try (ResultSet result = psAuthenticateStatement.executeQuery()) {
						if (result.next()) {
							response.sendRedirect("category");
						} else {
							out.println("user does not exsists");
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
