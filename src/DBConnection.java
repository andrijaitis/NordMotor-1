/**
 * Created by Kompas on 2017-05-11.
 */
/**
 * Created by Vidas on 5/10/2017.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Thomas is responsible for this class

public class DBConnection {
    //we put these values into string so that we can change them easaly later on
    private final static String URL = "jdbc:mysql://52.14.214.82:3306/";//show the location of our databases
    private final static String DB_NAME = "nordic";
    private final static String USER = "vidas";
    private final static String PASS = "mantas123";

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

