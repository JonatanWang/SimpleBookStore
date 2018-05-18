package book.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import book.business.Customer;
import book.data.CustomerDB;

/**
 * Controller class for customer
 * 
 */
public class CustomerController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/deleteCookies")) {
            url = deleteCookies(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // Post if user chooses to subscribe
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/subscribeToEmail")) {
            url = subscribeToEmail(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // Delete cookies for testing purpose
    private String deleteCookies(HttpServletRequest request,
            HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);  //delete the cookie
            cookie.setPath("/");  //entire application
            response.addCookie(cookie);
        }
        return "/delete_cookies.jsp";
    }

    // Subscribe by email
    private String subscribeToEmail(HttpServletRequest request,
            HttpServletResponse response) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);

        request.setAttribute("customer", customer);

        String url;
        String message;
        //check that email address doesn't already exist
        if (CustomerDB.emailExists(email)) {
            message = "This email address already exists. <br>"
                    + "Please enter another email address.";
            request.setAttribute("message", message);
            url = "/email/index.jsp";
        } else {
            CustomerDB.insert(customer);
            message = "";
            request.setAttribute("message", message);
            url = "/email/thanks.jsp";
        }
        return url;
    }
}