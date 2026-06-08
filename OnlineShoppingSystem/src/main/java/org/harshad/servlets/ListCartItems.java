package org.harshad.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.harshad.dao.Cart;
import org.harshad.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ListCartItems
 */
@WebServlet("/listCartItems")
public class ListCartItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCartItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("login.html");
			return;
		}
		Cart allProductList=(Cart)session.getAttribute("cart");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<h1> welcome "+session.getAttribute("username")+"</h1>");
		out.println("<a href='logout'> logout</a>");
		if(allProductList==null) {
			out.println("<h1> Cart is empty</h1>");
		}
		else {
			out.println("<table border='1' cellPadding='2' cellSpacing='2' ><tr>");
			out.println("<td>Category id </td>");
			out.println("<td>Product Id</td>");
			out.println("<td>Product Name</td>");
			out.println("<td> Product price</td></tr>");
			double total=0.0;
			for (Product objProduct:allProductList) {
				out.println("<tr>");
				 out.println("<td>"+objProduct.getCategoryId()+"</td>");
				 out.println("<td>"+objProduct.getProductId()+"</td>");
				 out.println("<td>"+objProduct.getProductName()+"</td>");
				 out.println("<td>"+objProduct.getPrice()+"</td>");
				 out.println("</tr>");
				 total+=objProduct.getPrice();
			}
			out.println("</table>");		
			
			out.println("<h1> Total amount = "+total+"</h1>");
		}
	}

}
