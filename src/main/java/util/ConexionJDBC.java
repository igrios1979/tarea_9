package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {


    private static    String url = "jdbc:mysql://localhost:3306/mi_base_de_datos?serverTimezone=UTC";
    private static    String user = "ignacio";
    private static   String pass = "123456";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pass);

    }

}
