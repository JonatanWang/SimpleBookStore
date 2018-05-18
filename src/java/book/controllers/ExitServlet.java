package book.controllers;

import book.business.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for customer to exit, not in use
 * 
 */
@WebServlet("/logout_customer")
public class ExitServlet extends HttpServlet {

    //private static final long serialVersionUID = 1599366365079846238L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get session
		HttpSession session = request.getSession();
		// get customer object
		Customer customer = (Customer)session.getAttribute("user");
		// check if customer is valid
		if(customer != null){
			// 将用户对象逐出session
			session.removeAttribute("customer");
			// 设置提示信息
			request.setAttribute("info", customer.getFirstName() + " " +  
                                customer.getLastName() + " exits successfully！");
		}
		// jump to message.jsp webpage
		request.getRequestDispatcher("/logout_customer.jsp").forward(request, response);
	}
}
