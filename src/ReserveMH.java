import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mantas_MSI on 5/12/2017.
 */
public class ReserveMH {

    public ArrayList<String> addExtra(String item) {

        java.util.ArrayList<String> members = new ArrayList<String>();

        int i =-1;
        members.add(i+1,item);

        return members;
    }

    public String season(int startMonth) {
    System.out.println(" starting month: " +startMonth);
    if(startMonth >= 6 && startMonth <= 8){
        System.out.println("summer bitch");//peak season summer
        return "Season: Peak season";
    }else if(startMonth >=13 || startMonth <= 0){
        System.out.println(" You entered a non existing month you fcking donut");//such a month does not exist
        return "Wrong Date ass";
    }else if( startMonth == 12 || startMonth == 2 || startMonth == 1 ){
        System.out.println("winter bitch");//low season winter
        return "Season: Low season";
    }else if( (startMonth >= 3 || startMonth <= 5) || (startMonth >= 9 || startMonth <= 11)){
        System.out.println("fcking either spring or Fall");//middle seasson either spring or fall
        return "Season: Middle season";
    }
    return  " ";
    }

   static java.util.ArrayList<String> items = new ArrayList<String>();
    public static java.util.ArrayList<String> addExtraShit(String item) {

        items.add(item);

        return items;
    }

     public static int season(String Season) {
         System.out.println(Season);
        if (Season.equals("Season: Middle season")){
          return 30;

        }else if (Season.equals("Season: Peak season")){
            return 60;
        } else {
            return 0;
        }


    }

    public static void saveOrder(String startYear, String startMonth, String startDay, String endYear, String endMotnh,
                                 String 	endDay, String season, String 	itemAmount, String cost, String signiture,
                                 String mhReservedID    ) {

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO `reserve` (`ID`, `startYear`, `startMonth`, `startDay`, `endYear`, `endMotnh`, `endDay`, `season`, `itemAmount`, `cost`, `signiture` , `reservedID`, `situation`)" +
                    "VALUES " +
                    "(NULL, "
                    +"'"+ startYear  +"'  ,"
                    +"'"+ startMonth +"'  ,"
                    +"'"+ startDay   +"'  ,"
                    +"'"+ endYear    +"'  ,"
                    +"'"+ endMotnh   +"'  ,"
                    +"'"+ endDay     +"'  ,"
                    +"'"+ season     +"'  ,"
                    +"'"+ itemAmount +"'  ,"
                    +"'"+ cost       +"'  ,"
                    +"'"+ signiture  +"'  ,"
                    +"'"+ mhReservedID +"'  ,"
                    +"'"+ "Reserved" +"'   "
                    +");                   ";

            String sqlChangeStatus =    "UPDATE nordic_rv " +
                                        "SET "         +
                                        "status           = " + "'" + "Reserved_MH" + "'" + " " +
                                        "WHERE rvID = " + "'" + mhReservedID  + "'" + ";" ;

            System.out.println(sql);
            System.out.println(sqlChangeStatus);

            stmt.executeUpdate(sql);
            stmt.executeUpdate(sqlChangeStatus);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
