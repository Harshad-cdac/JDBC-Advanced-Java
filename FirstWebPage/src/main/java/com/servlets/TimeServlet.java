package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter writer=response.getWriter();
			writer.print("<html> <body> <h1>"+new Date()+"</h1></body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
