package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static  Connection getConnection()
    {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//class present in dependency
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/generalStore", "root", "root");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
