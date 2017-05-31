
//Vidas responsible for this class
import java.sql.*;

public class AdminLogin {

    public boolean LoginStatus(String peck, String pick) // login & password prom controller action
    {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement prepeare =
            con.prepareStatement("SELECT * FROM `users` WHERE `userName`=? AND `password`=MD5(?)");
            prepeare.setString(1, peck);
            prepeare.setString(2, pick);

            prepeare.execute();

            ResultSet rs = prepeare.getResultSet();

            if(!rs.first()){
                  return false;
            } else   {

               return  true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return false;
    }

}
