import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            ResultSet rs = stmt.executeQuery("SELECT `mark`,`model`,`beds`, `fuel`, `mileage` FROM `nordic_rv` WHERE `rvID`=" + ID);

            while (rs.next()) {

               // MH.add(rs.getString(1));
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

    public ObservableList<String> refreshItForTheMechanic(){


        java.util.List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    "FROM nordic_rv");

            while (rs.next()) {
                members.add(
                        rs.getString(1) + " "+
                                rs.getString(3) + " "+
                                rs.getString(4) + " "+
                                rs.getString(5) + " "+
                                rs.getString(7) + "[L] "+
                                rs.getString(8) + "[km]");

                // System.out.println(rs.getString(4));
            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();


            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            ObservableList<String> list2 = FXCollections.observableArrayList();
            list2.add("Failed to load");
            return list2;
        }
    }
}
