
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//Mantas is responsible for this class
public class Repair {

    //we look at all the check boxes of they are checked
    public String serviceComplete(boolean oilSituation, boolean fuelSituation, boolean waterSituation, boolean cleanSituation, boolean repairsSituation, String repairMHid) {

        if(repairsSituation == true){
            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();

                String sql = "UPDATE nordic_rv " +
                        "SET "         +
                        "status    = " +"'" + "Out_of_service" +"'" + " " +
                        "WHERE rvID= "      + repairMHid  +";"        ;

                System.out.println(sql);
                stmt.executeUpdate(sql);
                con.close();

                return " The motorhome is sent of to repairs";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(oilSituation == true && fuelSituation == true && waterSituation == true && cleanSituation == true){

            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();

                String sql = "UPDATE nordic_rv " +
                        "SET "         +
                        "status    = " +"'" + "Available" +"'" + ", " +
                        "fuel      = "      + 100         +      "  " +
                        "WHERE rvID= "      + repairMHid  +";"        ;

                System.out.println(sql);
                stmt.executeUpdate(sql);
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "All service is done.";
        }

}
