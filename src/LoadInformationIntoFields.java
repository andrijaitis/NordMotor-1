import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Mantas is responsible for this class
public class LoadInformationIntoFields {

    public java.util.List<String> load(String ID) {

        java.util.List<String> MH = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT      " +
                    "`status`  , " +
                    "`mark`    , " +
                    "`model`   , " +
                    "`price`   , " +
                    "`beds`      " +
                    "FROM `nordic_rv` " +
                    "WHERE `rvID`  =  " + ID);

            while (rs.next()) {

                MH.add(rs.getString(1));
                MH.add(rs.getString(2));
                MH.add(rs.getString(3));
                MH.add(rs.getString(4));
                MH.add(rs.getString(5));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MH;
    }
}
