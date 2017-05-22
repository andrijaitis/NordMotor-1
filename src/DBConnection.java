/**
 * Created by Kompas on 2017-05-11.
 */
/**
 * Created by Vidas on 5/10/2017.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private final static String URL = "jdbc:mysql://sql11.freemysqlhosting.net:3306/";
    private final static String DB_NAME = "sql11175872";
    private final static String USER = "sql11175872";
    private final static String PASS = "JYrq7ab6W9";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    URL + DB_NAME,
                    USER,
                    PASS);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

