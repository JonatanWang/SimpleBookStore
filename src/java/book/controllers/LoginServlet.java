
package book.controllers;

import book.business.Customer;
import book.data.CustomerDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for customer to log in
 * @author zyw
 */
@WebServlet("/message")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
        
        String email = request.getParameter("email");
			
	String password = request.getParameter("password");
			
	CustomerDB customerDao = new CustomerDB();	
			
	Customer customer = customerDao.login(email, password);
        
	if(customer != null){
            
            request.getSession().setAttribute("customer", customer);
            
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }else{
            
            request.setAttribute("info", "Your email or password is wrong.");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }
}
