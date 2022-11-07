package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private  static Connection connection;
    private  static final String USERNAME = "root";
    private  static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/mydatabase";
    private  static final String PASSWORD = "root";
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        try {
            return connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
        } catch (SQLException e){
            throw new SQLException();
        }
    }
}
