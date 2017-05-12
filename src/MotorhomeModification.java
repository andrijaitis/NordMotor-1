/**
 * Created by Mantas_MSI on 5/11/2017.
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public void updatingMotorHomne(){

    }

    public  void refresh(){

        List<String> members = new ArrayList<String>();

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    "FROM Teams");

            while (rs.next()) {
                members.add(
                        rs.getString(4));
            }
            con.close();

            ObservableList<String> list = FXCollections.observableArrayList();

            String listString = "";

            for (String s : members) {
                listString += list.add(s);
            }
            Team1R1.setItems(list);
            Team2R1.setItems(list);
            Team3R1.setItems(list);
            Team4R1.setItems(list);

            Team1R2.setItems(list);
            Team2R2.setItems(list);

            winnerTeam.setItems(list);
            // pickTournamentDate.setItems(list);

            //inputScore3.setItems(list);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
