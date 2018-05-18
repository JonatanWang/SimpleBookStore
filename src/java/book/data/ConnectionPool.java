package book.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

// Pool class for database connection by JDBC
public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    // Singleton monster
    public synchronized static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    // Constructor of connection pool
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/bookDB");
        } catch (NamingException e) {
            System.err.println(e);
        }
    }

    // Get connection
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqle) {
            System.err.println(sqle);
            return null;
        }
    }

    // Release connection
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}