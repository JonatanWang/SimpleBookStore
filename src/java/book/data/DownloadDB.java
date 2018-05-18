package book.data;

import book.business.Statistics;
import java.sql.*;

// Class for statistics of downloading sample chapter of book
public class DownloadDB {

    public static long insert(Statistics download) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO Download "
                + "(UserID, DownloadDate, ProductCode) "
                + "VALUES "
                + "(?, NOW(), ?)";
        try {
            ps = connection.prepareStatement(query);
            // Dynamically set values to parameters *对SQL语句的占位符参数进行动态赋值
            ps.setLong(1, download.getUser().getId());
            ps.setString(2, download.getProductCode());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}