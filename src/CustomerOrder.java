import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.ResultSet;


/**
 * Created by Mantas_MSI on 5/14/2017.
 */
public class CustomerOrder {

    public void pickUp(String customerName) {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "UPDATE reserve, nordic_rv " +
                    "SET " +
                    "status    = " + "'" + "In_use" + "'" + " " +
                    "WHERE reservedID = rvID AND signiture = " + "'" + customerName + "'" + ";";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void orderCancelation() {


    }

    public String dateDffCounter(String signature) {
        String days;
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        Date date = new Date();
        String cancelationDate = (String) dateFormat.format(date);
        String OrderDate = StartDate(signature);

        try {

            Date date1 = dateFormat.parse(cancelationDate);
            Date date2 = dateFormat.parse(OrderDate);
            long difference = date2.getTime() - date1.getTime();
            days = Long.toString(TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS));
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public String StartDate(String customerName) {

        try {

            System.out.println(customerName);
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `startDay`,`startMonth`,`startYear` FROM reserve, nordic_rv  WHERE reservedID = rvID AND signiture =" + "'" + customerName + "'" + ";");
            while (rs.next()) {
                System.out.println(rs.getString(3) + " " + rs.getString(1) + " " + rs.getString(2));
                String date = rs.getString(3) + " " + rs.getString(1) + " " + rs.getString(2);
                con.close();
                return date;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "1999 11 11"; //means something is wrong u DIP

    }

    public String cost(String customerName) {

        try {

            System.out.println(customerName);
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `cost` FROM reserve, nordic_rv  WHERE reservedID = rvID AND signiture =" + "'" + customerName + "'" + ";");
            while (rs.next()) {
                String cost = rs.getString(1);
                con.close();
                return cost;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "999999"; //means something is wrong u DIP

    }
    //int seasonPrice= ((motorhomePrice) / 100 * seasonPercentage ) +extraItemPrice + motorhomePrice;

    public int penalty(int days,int cost ) {
        int refund = 0;
        if (50 < days) {
            refund = cost / 100 * 20;
            return refund;
        } else if (50 < days) {
            refund = cost / 100 * 50;
            return refund;
        } else if (49 > days && days > 15) {
            refund = cost / 100 * 80;
            return refund;
        } else if (days >= 15) {
            refund = cost / 100 * 95;
            return refund;
        } else if (days >= 1) {
            refund = cost / 100 * 95;
            return refund;
        } else {
            return refund;
        }

    }




}
