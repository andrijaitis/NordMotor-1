import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


//Vidas is responsible for this class
public class ReserveMH {
    //checks which season it is
    public String season(int startMonth) {
    if(startMonth >= 6 && startMonth <= 8){
        return "Season: Peak season";
    }else if(startMonth >=13 || startMonth <= 0){
        return "Wrong Date";
    }else if( startMonth == 12 || startMonth == 2 || startMonth == 1 ){
        return "Season: Low season";
    }else if( (startMonth >= 3 || startMonth <= 5) || (startMonth >= 9 || startMonth <= 11)){
        return "Season: Middle season";
    }
    return  " ";
    }
    //method to add extra items
   static java.util.ArrayList<String> items = new ArrayList<String>();
    public static java.util.ArrayList<String> addExtraShit(String item) {
        items.add(item);
        return items;
    }

     public static int season(String mySeason) {
        if (mySeason.equals("Season: Middle season")){
          return 30;

        }else if (mySeason.equals("Season: Peak season")){
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
                    + "'" + startYear    + "'  ,"
                    + "'" + startMonth   + "'  ,"
                    + "'" + startDay     + "'  ,"
                    + "'" + endYear      + "'  ,"
                    + "'" + endMotnh     + "'  ,"
                    + "'" + endDay       + "'  ,"
                    + "'" + season       + "'  ,"
                    + "'" + itemAmount   + "'  ,"
                    + "'" + cost         + "'  ,"
                    + "'" + signiture    + "'  ,"
                    + "'" + mhReservedID + "'  ,"
                    + "'" + "Reserved"   + "'   "
                    + ");                       ";

            String sqlChangeStatus = "UPDATE nordic_rv " +
                    "SET " +
                    "status     = " + "'" + "Reserved_MH" + "'" + " " +
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

    public String dateDffCounter(String signature) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        Date date = new Date();
        String cancelationDate = (String) dateFormat.format(date);
        String orderDate =  (reservation(signature).get(4) + " " + reservation(signature).get(3) + " " + reservation(signature).get(2));
        System.out.println(orderDate);

        return allTheDateCounter(dateFormat, cancelationDate, orderDate);
    }
    public String currentDaytoStartDate(String startDate) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        Date date = new Date();
        String todayDate = (String) dateFormat.format(date);

        return   allTheDateCounter(dateFormat, todayDate, startDate);
    }

    public String dayCounterStartEnd (String startDate, String endDate) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        return allTheDateCounter(dateFormat, startDate, endDate);
    }

    public String allTheDateCounter(DateFormat dateFormat, String myDateOne, String myDateTwo){
        try {
            String days;
            Date date1 = dateFormat.parse(myDateOne);
            Date date2 = dateFormat.parse(myDateTwo);
            long difference = date2.getTime() - date1.getTime();
            days = Long.toString(TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS));
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "0";
    }


    public java.util.List<String> reservation(String customerName) {
        java.util.List<String> list = new ArrayList<String>();
        try {

            System.out.println(customerName);
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `cost` ,`signiture` , `startDay`,`startMonth`,`startYear` , `endDay`,`endMotnh`,`endYear` FROM reserve, nordic_rv  WHERE reservedID = rvID AND signiture =" + "'" + customerName + "'" + ";");
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(rs.getString(4));
                list.add(rs.getString(5));
                list.add(rs.getString(6));
                list.add(rs.getString(7));
                list.add(rs.getString(8));

                con.close();
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list; //means something is wrong

    }
}
