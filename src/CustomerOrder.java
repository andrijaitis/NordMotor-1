import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Mantas_MSI on 5/14/2017.
 */
public class CustomerOrder {

    public void pickUp(String customerName){
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "UPDATE reserve, nordic_rv " +
                    "SET "         +
                    "status    = " +"'" + "In_use" +"'" + " " +
                    "WHERE reservedID = rvID AND signiture = " + "'" +customerName +    "'"+           ";"        ;

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
