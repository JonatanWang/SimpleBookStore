package book.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for customer and administrator to log out
 * @author zyw
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Simply break the session
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/logout.jsp");
    }  
}
