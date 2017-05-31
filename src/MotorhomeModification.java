//Mantas and Vidas are responsible for this class

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorhomeModification {

    public static void addMotorHome(String theBrand, String theModel, int thePrice, String theBed, int theMileage) {//fix this to be a double and int

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO nordic_rv " +
                    "VALUES " +
                    "(NULL, "
                    +"'"+ "Unavailable" +"'  ,"
                    +"'"+ theBrand      +"'  ,"
                    +"'"+ theModel      +"'  ,"
                    +"'"+ theBed        +"'  ,"
                    +"'"+ thePrice      +"'  ,"
                    +100                +"   ,"
                    +"'"+ theMileage    +"'   "
                    +");                      ";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatingMotorHomne(String ID,String brand,String model,String price, String beds, String availability) {

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "UPDATE nordic_rv " +
                         "SET "         +
                         "status    = " +"'" + availability +"'" + ", " +
                         "mark      = " +"'" + brand        +"'" + ", " +
                         "model     = " +"'" + model        +"'" + ", " +
                         "beds      = " +"'" + beds         +"'" + ", " +
                         "price     = " +"'" + price        +"'" + "  " +
                         "WHERE rvID= " + ID +               ";"        ;

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteMotorHome(String ID) {
        try {
            String sql = "DELETE FROM `nordic_rv`   " +
                         "WHERE `nordic_rv`.`rvID` =" + ID;

            System.out.println(sql);

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
