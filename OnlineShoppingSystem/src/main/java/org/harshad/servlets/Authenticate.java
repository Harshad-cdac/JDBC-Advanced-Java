package org.harshad.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.harshad.dao.UserDao;
import org.harshad.dao.impl.UserDaoImplementation;
import org.harshad.entity.User;
import org.harshad.exceptions.UserNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {

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
			User user=new User();
			UserDao userdao=new UserDaoImplementation();
			PrintWriter out=response.getWriter();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			user.setUsername(username);
			user.setPassword(password);
			if(userdao.isAuthenticated(user)) {
				HttpSession session=request.getSession();
				session.setAttribute("username", user.getUsername());
				response.sendRedirect("category");
			}else {
				out.println("Invalid userName or password");
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
	}
}
