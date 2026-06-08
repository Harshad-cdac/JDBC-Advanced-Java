package org.harshad.servlets;

import java.io.IOException;

import org.harshad.dao.Cart;
import org.harshad.dao.impl.ShoppingCart;
import org.harshad.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addcart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String temp = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(temp);
		temp = request.getParameter("productId");
		int productId = Integer.parseInt(temp);
		String productName = request.getParameter("productName");
		temp = request.getParameter("price");
		float productPrice = Float.parseFloat(temp);
		Product product = new Product(categoryId, productName, productId, productPrice);

		HttpSession session = request.getSession(false);
		Cart productCart = null;
		productCart = (Cart) session.getAttribute("cart");

		if (productCart == null) {
			productCart = new ShoppingCart();
			session.setAttribute("cart", productCart);
		}
		productCart.addToCart(product);
		response.sendRedirect("listCartItems");
	}

}
