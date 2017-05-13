import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Mantas_MSI on 5/13/2017.
 */
public class Repair {
    public java.util.List<String> loadItUpForTheMechanic(String ID) {

        java.util.List<String> MH = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `mark`,`model`,`beds`,`fuel`, `mileage` FROM `nordic_rv` WHERE `rvID`=" + ID);

            while (rs.next()) {

               // MH.add(rs.getString(1));
                MH.add(rs.getString(3));
                MH.add(rs.getString(4));
                MH.add(rs.getString(5));
                MH.add(rs.getString(7));
                MH.add(rs.getString(8));

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MH;
    }
}
