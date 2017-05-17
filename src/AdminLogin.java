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
            //ResultSet rs = stmt.executeQuery("SELECT `userName`,`password` FROM `users` WHERE `ID` = 1");
            ResultSet rs = stmt.executeQuery("SELECT `userName`,`status` FROM users WHERE STRCMP( `password` ,MD5('"+pick+"')) = 0");

            while (rs.next()) {
                System.out.println(rs.getString(2));
                Login =   rs.getString(1);
                Pass =   rs.getString(2);
                System.out.println(Pass);

                if(peck.equals(Login) && Pass.equals("b326b5062b2f0e69046810717534cb09")){

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
