
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//VIdas and Mantas are responsible for this class

public class CustomerOrder {

    public void pickUp(String customerName) {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "UPDATE reserve, nordic_rv " +
                    "SET " +
                    "status    = " + "'" + "In_use"   + "'" + ", " +
                    "situation = " + "'" + "Using_it" + "'" +
                    "  " +
                    "WHERE reservedID = rvID AND signiture = " + "'" + customerName + "'" + ";";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int turnIn(String nameOfTheGuyWhoTurnIns, int currentMileage, int currentFuel) {

        String dataBaseMileage  = " ";
        String dataBaseFuel     = " ";
        String dataBaseCost     = " ";

        int distenceOfTheJourney = 0;
        int fourHundredKMfree    = 0;
        int lessThenHalfFuel     = 0;

        try {
            Connection con = DBConnection.getConnection();
            //this one is for finding mileage and fuel and cost --------------------------------------------------------
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT mileage , fuel, cost " +
                    "FROM reserve, nordic_rv " +
                    "WHERE reservedID = rvID AND signiture =" + "'" + nameOfTheGuyWhoTurnIns + "'" + ";"
            );

            while (rs.next()) {
                dataBaseMileage = rs.getString(1);
                dataBaseFuel    = rs.getString(2);
                dataBaseCost    = rs.getString(3);

            }
            //----------------------------------------------------------------------------------------------------------


            if ((Integer.parseInt(dataBaseFuel) / 2) > currentFuel) {
                lessThenHalfFuel = 70;
            }

            distenceOfTheJourney = currentMileage - Integer.parseInt(dataBaseMileage);
            if (distenceOfTheJourney > 400) {
                fourHundredKMfree = distenceOfTheJourney;
                fourHundredKMfree += Integer.parseInt(dataBaseCost);
                fourHundredKMfree += lessThenHalfFuel;
            }


            //this one updates milege and fuel and cost
            String sql = "UPDATE nordic_rv, reserve " +
                    "SET         " +
                    "situation = " + "'" + "Finished"           + "'" + ", " +
                    "status    = " + "'" + "Unavailable"        + "'" + ", " +
                    "mileage   = " + "'" + currentMileage       + "'" + ", " +
                    "fuel      = " + "'" + currentFuel          + "'" + ", " +
                    "cost      = " + "'" + fourHundredKMfree    + "'" + "  " +
                    "WHERE rvID = reservedID AND signiture =  " + "'" + nameOfTheGuyWhoTurnIns + "'" + ";";

            System.out.println(sql);

            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fourHundredKMfree;
    }

    public void orderCancelation(String nameOfTheGuyWhoCanceled) {


        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();


            String sql =    "UPDATE reserve, nordic_rv " +
                    "SET "         +
                    "status    = " +"'" + "Available"          + "'" + ", " +
                    "situation = " +"'" + "Canceled"           + "'" + "  " +
                    "WHERE reservedID = rvID AND signiture = " + "'" +nameOfTheGuyWhoCanceled +    "'"+           ";"        ;


            System.out.println(sql);

            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int penalty(int days, int cost) {

        int refund = 0;
        if (50 < days) {
            refund = cost / 100 * 20;
            System.out.println("20%");
            return refund;
        } else if (50 < days && 15 < days) {
            refund = cost / 100 * 50;
            System.out.println("50%");
            return refund;
        } else if (days < 15 && days > 1) {
            refund = cost / 100 * 80;
            System.out.println("80%");
            return refund;
        } else if (days <= 1) {
            refund = cost / 100 * 95;
            System.out.println("95%");
            return refund;
        } else {
            return refund;
        }
    }

    //side method to get the current mileage before the trip
    public String mileagaBeforeTheTrip(String customer) {

        String mileage = " ";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT mileage " +
                    "FROM  nordic_rv, reserve " +
                    "WHERE reservedID = rvID AND signiture =" + "'" + customer + "'" + ";"
            );

            while (rs.next()) {
                mileage = rs.getString(1);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mileage;
    }


    public void updateOrderPrice(String customerName, int price) {

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "UPDATE reserve, nordic_rv " +
                    "SET " +
                    "cost    = "  +price+ "" + " " +
                    "  " +
                    "WHERE reservedID = rvID AND signiture = " + "'" + customerName + "'" + ";";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}










