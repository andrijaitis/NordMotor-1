import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        return " Peak season";
    }else if(startMonth >=13 || startMonth <= 0){
        System.out.println(" You entered a non existing month you fcking donut");//such a month does not exist
        return "Wrong Date ass";
    }else if( startMonth == 12 || startMonth == 2 || startMonth == 1 ){
        System.out.println("winter bitch");//low season winter
        return " Low season";
    }else if( (startMonth >= 3 || startMonth <= 5) || (startMonth >= 9 || startMonth <= 11)){
        System.out.println("fcking either spring or Fall");//middle seasson either spring or fall
        return " Middle season";
    }
    return  " ";
    }
}
