/**
 * Created by Kompas on 2017-05-11.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kompas on 2017-05-11.
 */
public class AdminLogin {

    public boolean LoginStatus(String peck, String pick) // login & password prom controller action
    {

        String Login ;
        String Pass ;

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `userName`,`password` FROM `users` WHERE `userID` = 1");

            while (rs.next()) {

                Login =   rs.getString(1);
                Pass =   rs.getString(2);
                System.out.println( " UserInput " + peck + " " + pick);
                System.out.println(" SQL " + Login + " " + Pass);

                if(peck.equals(Login) && pick.equals(Pass)){
                    return true;
                } else {
                    return false;
                }
            }
            con.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


return false;
    }

}
