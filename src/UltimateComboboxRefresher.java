import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Mantas is responsible for this class
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
                                rs.getString(1) + " <"+
                                        rs.getString(2) + "> "+
                                        rs.getString(3) + " "+
                                        rs.getString(4) + " "+
                                        rs.getString(5) + "[B] "+
                                        rs.getString(6) + " [€] "+
                                        rs.getString(7) + " [L] "+
                                        rs.getString(8) + " [km] ");
                }
                //update motorhome info
                if(tab == "Update") {
                    if (!rs.getString(2).equals("Out_of_service") && !rs.getString(2).equals("Unavailable") && !rs.getString(2).equals("In_use") && !rs.getString(2).equals("Reserved_MH")) {
                        members.add(
                                rs.getString(1) + " " +
                                        rs.getString(2) + " " +
                                        rs.getString(3) + " " +
                                        rs.getString(4) + " " +
                                        rs.getString(5) + "[B] " +
                                        rs.getString(6) + " [€] ");
                    }
                }
                //repair motorhomes
                if(tab == "Repair") {
                    if (!rs.getString(2).equals("Available") && !rs.getString(2).equals("In_use") && !rs.getString(2).equals("Reserved_MH")) {
                        members.add(
                                rs.getString(1) + " <" +
                                        rs.getString(2) + "> " +
                                        rs.getString(3) + " " +
                                        rs.getString(4) + " " +
                                        rs.getString(5) + " " +
                                        rs.getString(7) + "[L] " +
                                        rs.getString(8) + "[km]");
                    }
                }
                //reserve a motorhome
                if(tab == "Reserve") {
                    if (!rs.getString(2).equals("Out_of_service") && !rs.getString(2).equals("Unavailable") && !rs.getString(2).equals("In_use") && !rs.getString(2).equals("Reserved_MH")) {
                        members.add(
                                rs.getString(1) + " " +
                                        rs.getString(2) + " " +
                                        rs.getString(3) + " " +
                                        rs.getString(4) + " " +
                                        rs.getString(5) + "[B] " +
                                        rs.getString(6) + " [€] ");
                    }
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
            ResultSet rs = stmt.executeQuery("SELECT signiture, situation " +
                    "FROM reserve");

            while (rs.next()) {
                    if(tab.equals("pickUp")){
                        if(!rs.getString(2).equals("Finished") && !rs.getString(2).equals("Canceled") && !rs.getString(2).equals("Using_it")){
                            members.add(
                                            rs.getString(1));
                        }
                    }

                    if(tab.equals("turnIn")){
                        if(!rs.getString(2).equals("Reserved") && !rs.getString(2).equals("Finished") && !rs.getString(2).equals("Canceled")){
                            members.add(
                                rs.getString(1));
                        }
                    }
                    if(tab.equals("cancel")){
                        if(!rs.getString(2).equals("Canceled") && !rs.getString(2).equals("Using_it") && !rs.getString(2).equals("Finished")){
                            members.add(
                                    rs.getString(1));
                        }
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

}
