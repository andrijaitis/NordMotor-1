/**
 * Created by Mantas_MSI on 5/11/2017.
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import java.awt.*;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

    public void updatingMotorHomne() {

    }

    public java.util.List<String> Load(String ID) {
        String P; // called P with no reason

      java.util.List<String> MH = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `status`, `mark`,`model`,`beds`,`price` FROM `nordic_rv` WHERE `rvID`=" + ID);

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


    public  ObservableList<String> refresh(){


        java.util.List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    "FROM nordic_rv");

            while (rs.next()) {
                members.add(rs.getString(1) + " "+
                        rs.getString(2) + " "+
                        rs.getString(3) + " "+
                        rs.getString(4) + " "+
                        rs.getString(5) + " "+
                        rs.getString(6));
                System.out.println(rs.getString(4));
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
