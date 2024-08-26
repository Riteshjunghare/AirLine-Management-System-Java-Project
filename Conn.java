package airlinemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    
    Connection c;
    Statement s;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airlinemanagementsystem";
    private static final String USER = "root";
    private static final String PASS = "pass123";
    
    public Conn() {
        try {
            Class.forName(JDBC_DRIVER);
            c = DriverManager.getConnection(DB_URL, USER, PASS);
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Method to close the connection
    public void closeConnection() {
        try {
            if (s != null) {
                s.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
