package book.data;

import book.business.Customer;
import java.sql.*;

/**
 * Data Access Object(DAO) of customer class
 * 
 */
public class CustomerDB {

    // Insert a customer
    public static void insert(Customer user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO User (FirstName, LastName, Email, Password, CompanyName, "
                + "Address1, Address2, City, State, Zip, Country, "
                + "CreditCardType, CreditCardNumber, CreditCardExpirationDate) "
                + "VALUES (?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, "
                + "?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getCompanyName());
            ps.setString(6, user.getAddress1());
            ps.setString(7, user.getAddress2());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getState());
            ps.setString(10, user.getZip());
            ps.setString(11, user.getCountry());
            ps.setString(12, user.getCreditCardType());
            ps.setString(13, user.getCreditCardNumber());
            ps.setString(14, user.getCreditCardExpirationDate());
            
            ps.executeUpdate();
            
            //Get the user ID from the last INSERT statement.
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            long userID;
            try (Statement identityStatement = connection.createStatement(); 
                    ResultSet identityResultSet = 
                            identityStatement.executeQuery(identityQuery)) {
                identityResultSet.next();
                userID = identityResultSet.getLong("IDENTITY");
            }

            // Set the user ID in the Customer object
            user.setId(userID);
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    // Update a customer information, e.g. address
    public static void update(Customer user) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE User SET "
                + "FirstName = ?, "
                + "LastName = ?, "
                + "Password = ?, "
                + "CompanyName = ?, "
                + "Address1 = ?, "
                + "Address2 = ?, "
                + "City = ?, "
                + "State = ?, "
                + "Zip = ?, "
                + "Country = ?, "
                + "CreditCardType = ?, "
                + "CreditCardNumber = ?, "
                + "CreditCardExpirationDate = ? "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getCompanyName());
            ps.setString(5, user.getAddress1());
            ps.setString(6, user.getAddress2());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getZip());
            ps.setString(10, user.getCountry());
            ps.setString(11, user.getCreditCardType());
            ps.setString(12, user.getCreditCardNumber());
            ps.setString(13, user.getCreditCardExpirationDate());
            ps.setString(14, user.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    // Choose  a customer from database by his unique email address
    public static Customer selectUser(String email) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Customer user = null;
            if (rs.next()) {
                user = new Customer();
                user.setId(rs.getLong("UserID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setAddress1(rs.getString("Address1"));
                user.setAddress2(rs.getString("Address2"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setZip(rs.getString("Zip"));
                user.setCountry(rs.getString("Country"));
                user.setCreditCardType(rs.getString("CreditCardType"));
                user.setCreditCardNumber(rs.getString("CreditCardNumber"));
                user.setCreditCardExpirationDate(
                        rs.getString("CreditCardExpirationDate"));
            }
            return user;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    // Boolean method to check if an email has been used to register in database
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    // Log in a customer by checking if email and password are correct
    public Customer login(String email, String password) {
        
        Customer user = null;
        
        // Obtain the database connection object
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        // Search customer information based on email and password
        String query = "SELECT * FROM User WHERE Email = ? AND Password = ?";
        
        try {
            // Get PreparedStatement Object
            PreparedStatement ps = conn.prepareStatement(query);
            // Dynamically set values to parameters *对SQL语句的占位符参数进行动态赋值
            ps.setString(1, email);
            ps.setString(2, password);
            // Execute query result set
            ResultSet rs = ps.executeQuery();
            // Check if the result set is valid
            if(rs.next()){
                // Instantiate a customer object
                user = new Customer();
                // Set values to customer object
                user.setId(rs.getLong("userId"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCompanyName(rs.getString("companyName"));
                user.setAddress1(rs.getString("address1"));
                user.setAddress2(rs.getString("address2"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("st"));
                user.setZip(rs.getString("zip"));
                user.setCountry(rs.getString("country"));
                user.setCreditCardType(rs.getString("creditCardType"));
                user.setCreditCardNumber(rs.getString("creditCardNumber"));
                user.setCreditCardExpirationDate(rs.getString("creditCardExpirationDate"));
                return user;
            }
            // Release database and JDBC resource for the result set object
            rs.close();
            // Release database and JDBC resource for the prepared statement object
            ps.close();
            
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            // Close database connection
            pool.freeConnection(conn);
        }
        return user;
    }
    
    // Check if a customer yet exists in database by register email
    public boolean customerExists(String email) {
        
        // Obtain the database connection object
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        // Search customer information based on email and password
        String query = "SELECT * FROM User WHERE Email = ? ";
        try {
            // Get PreparedStatement Object
            PreparedStatement ps = conn.prepareStatement(query);
            // Dynamically set values to parameters *对SQL语句的占位符参数进行动态赋值
            ps.setString(1, email);
            
            // Execute query result set
            ResultSet rs = ps.executeQuery();
            // Check if the result set is valid
            if(!rs.next()){
                // Proof that the email can be used 
                return true;     
            }
            // Release database and JDBC resource for the result set object
            rs.close();
            // Release database and JDBC resource for the prepared statement object
            ps.close();
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            // Close database connection
            pool.freeConnection(conn);
        }
        return false;
    }
}