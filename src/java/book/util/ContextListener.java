package book.util;

import javax.servlet.*;
import java.util.*;

/**
 * Listener class for servlet context
 * 
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        ServletContext sc = event.getServletContext();

        // get the absolute paths regular connections
        String contextPath = sc.getContextPath();
        System.out.println("ContextPath: " + contextPath);
        String absolutePath = "http://localhost:8080" + contextPath;
        sc.setAttribute("absolutePath", absolutePath);
                
        // initialize the customer service email address that's used throughout the web site
        String custServEmail = sc.getInitParameter("custServEmail");
        sc.setAttribute("custServEmail", custServEmail);

        // initialize the current year that's used in the copyright notice
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        sc.setAttribute("currentYear", currentYear);

        // initialize the array of years that's used for the credit card year
        ArrayList<String> creditCardYears = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int year = currentYear + i;
            String yearString = Integer.toString(year);
            creditCardYears.add(yearString);
        }
        sc.setAttribute("creditCardYears", creditCardYears);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // no cleanup necessary
    }
}