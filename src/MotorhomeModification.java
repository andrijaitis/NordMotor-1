/**
 * Created by Mantas_MSI on 5/11/2017.
 */
import javafx.fxml.FXML;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorhomeModification {

    public static void addMotorHome(String theBrand, String theModel, String thePrice, String theBed) {//fix this to be a double and int

        System.out.println("Name ->" + theBrand + "<-");

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO nordic_rv " +
                    "VALUES " +
                    "(NULL, "
                    +"'"+ "Available"  +"'  ,"
                    +"'"+ theBrand     +"'  ,"
                    +"'"+ theModel     +"'  ,"
                    +"'"+ thePrice     +"'  ,"
                    +"'"+ theBed       +"'  ,"
                    +100               +"   ,"
                    +11224             +")  ;";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
