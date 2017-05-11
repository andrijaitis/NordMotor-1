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
    private final static String URL = "jdbc:mysql://52.26.41.237:3306/";
    private final static String DB_NAME = "nordic_motorhomes";
    private final static String USER = "hanzback";
    private final static String PASS = "thomasisgay";

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

