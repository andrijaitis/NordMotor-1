import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Mantas_MSI on 5/14/2017.
 */
public class UltimateComboboxRefresher {

    public ObservableList<String> myUltimateRefresh(String tab){

        java.util.List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    "FROM nordic_rv");

            while (rs.next()) {
                //Start Screen
                if(tab == "SS") {
                    members.add(
                                    rs.getString(1) + " "+
                                    rs.getString(2) + " "+
                                    rs.getString(3) + " "+
                                    rs.getString(4) + " "+
                                    rs.getString(5) + "[B] "+
                                    rs.getString(6) + " [€] "+
                                    rs.getString(7) + " [L] "+
                                    rs.getString(8) + " [km] ");
                }
                //update motorhome info
                if(tab == "Update") {
                    members.add(
                                    rs.getString(1) + " "+
                                    rs.getString(2) + " "+
                                    rs.getString(3) + " "+
                                    rs.getString(4) + " "+
                                    rs.getString(5) + "[B] "+
                                    rs.getString(6) + " [€] ");
                }
                //repair motorhomes
                if(tab == "Repair") {
                    members.add(
                                    rs.getString(1) + " <"+
                                    rs.getString(2) + "> "+
                                    rs.getString(3) + " "+
                                    rs.getString(4) + " "+
                                    rs.getString(5) + " "+
                                    rs.getString(7) + "[L] "+
                                    rs.getString(8) + "[km]");
                }
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

    public ObservableList<String> customerOrder(String tab){

        java.util.List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT signiture " +
                    "FROM reserve");

            while (rs.next()) {
                //Start Screen
                    members.add(
                            rs.getString(1) );
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
