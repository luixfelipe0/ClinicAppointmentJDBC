package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConfig {

    private static Connection conn = null;

    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("resources/db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConn() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                conn = DriverManager.getConnection(props.getProperty("dburl"), props);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeStatement(PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
