/**
 * Created by Mantas_MSI on 5/11/2017.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorhomeModification {

    public static void addMotorHome(String theBrand) {

        String mhBrand = theBrand;

        System.out.println("Name ->" + mhBrand + "<-");

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO nordic_rv " +
                    "VALUES " +
                    "(NULL, "
                    +"'"+ mhBrand  +"'    ,"
                    +"'"+ theBrand +"'    ,"
                    +"'"+ theBrand +"'    ,"
                    +0+                  ","
                    +"'"+ theBrand +"'    ,"
                    +0+                  ","
                    +0+ ");";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
