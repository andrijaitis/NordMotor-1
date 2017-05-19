/**
 * Created by Kompas on 2017-05-11.
 */
import javafx.fxml.FXML;
import java.awt.*;
import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static java.sql.JDBCType.NULL;

public class Controller {


    private boolean userLoggedIn = false;

    @FXML
    private  javafx.scene.control.ComboBox startScreenComBox;

    //AdminLogin CLASS variables >>>>>>>>>> for logingin
    @FXML
    private Label statusBarForLogin;
    @FXML
    private javafx.scene.control.TextField log;
    @FXML
    private javafx.scene.control.TextField pas;
    @FXML
    private javafx.scene.control.TitledPane startSceenPan;
    @FXML
    private javafx.scene.control.TitledPane motorhomeModPan;
    @FXML
    private javafx.scene.control.TitledPane reservePan;
    @FXML
    private javafx.scene.control.TitledPane customerOrderFunctioPan;
    @FXML
    private javafx.scene.control.TitledPane repairPan;
    //MotorhomeModification CLASS variables >>>>>>>>>> for adding a MH
    @FXML
    private javafx.scene.control.TextField addBrandTxtField;


    @FXML
    private javafx.scene.control.TextField addModelTxtField;
    @FXML
    private javafx.scene.control.Label daysCounter;

    @FXML
    private javafx.scene.control.TextField addPriceTxtField;
    @FXML
    private javafx.scene.control.ComboBox addBedComBox;
    @FXML
    private javafx.scene.control.TextField addMileageTxtField;
    @FXML
    private javafx.scene.control.TextField finalPrice;
    @FXML
    private javafx.scene.control.Label statusBarForSuccessesfullyAddingMH;
    @FXML
    private javafx.scene.control.Label statusBarForReserver;
    //ReserveMH CLASS variables >>>>>>>>>> for adding extra item
    @FXML
    private Label totalItems;
    @FXML
    private javafx.scene.control.TextArea extraItemsTxtArea;
    @FXML
    private javafx.scene.control.ComboBox listOfExtraItemsComBox;
    //ReserveMH CLASS variables >>>>>>>>>> for finding which season it is
    @FXML
    private javafx.scene.control.ComboBox reserverCombo;
    @FXML
    private javafx.scene.control.ComboBox startDateDAYTxtField;
    @FXML
    private javafx.scene.control.ComboBox startDateMONTHTxtField;
    @FXML
    private javafx.scene.control.ComboBox endDateDAYTxtField;
    @FXML
    private javafx.scene.control.ComboBox endDateMONTHTxtField;
    @FXML
    private javafx.scene.control.ComboBox startDateYEARtxtField;
    @FXML
    private javafx.scene.control.ComboBox endDateYEARtxtField;
    @FXML
    private javafx.scene.control.TextField singitureTxtField;
    @FXML
    private javafx.scene.control.TextField whichSeason;
    //MotorhomeModification CLASS variables >>>>>>>>>> for updating existing values for database
    @FXML
    private ComboBox listOfMHforUpdating;
    @FXML
    private javafx.scene.control.TextField updateID;
    @FXML
    private javafx.scene.control.TextField updateMark;
    @FXML
    private javafx.scene.control.TextField updateModel;
    @FXML
    private javafx.scene.control.TextField updatePrice;
    @FXML
    private ComboBox updateBeds;
    @FXML
    private ComboBox updateAvailability;
    //Repair CLASS variables >>>>>>>>>> for loading up the motorhome for the mechanic
    @FXML
    private javafx.scene.control.ComboBox repairListOFMHforMechanic;
    @FXML
    private javafx.scene.control.TextField idForMechanic;
    @FXML
    private javafx.scene.control.TextField brandForMechanic;
    @FXML
    private javafx.scene.control.TextField modelForMechanic;
    @FXML
    private javafx.scene.control.TextField bedForMechanic;
    @FXML
    private javafx.scene.control.TextField fuelForMechanic;
    @FXML
    private javafx.scene.control.TextField mileageForMechanic;
    //check boxes
    @FXML
    private javafx.scene.control.CheckBox oilCheck;
    @FXML
    private javafx.scene.control.CheckBox fuelCheck;
    @FXML
    private javafx.scene.control.CheckBox waterCheck;
    @FXML
    private javafx.scene.control.CheckBox cleanCheck;
    @FXML
    private javafx.scene.control.CheckBox repairNeededCheck;
    //UltimateComboboxRefresher CLASS variables >>>>>>>>>> for loading up customers
    @FXML
    private javafx.scene.control.ComboBox pickUpCustomer;
    @FXML
    private javafx.scene.control.ComboBox turnInCustomer;
    @FXML
    private javafx.scene.control.ComboBox cancelReservationCustomer;
    //CustomerOrder CLASS variables >>>>>>>>>> for seeing returned mileage and fuel
    @FXML
    private  javafx.scene.control.TextField turnInMileage;
    @FXML
    private  javafx.scene.control.TextField turnInFuel;
    @FXML
    private  javafx.scene.control.Label mileageBeforeTrip;
    @FXML
    private  javafx.scene.control.TextArea receiptTxtArea;


    //Class instences
    MotorhomeModification motorhomeModification = new MotorhomeModification();
    AdminLogin adminLogin = new AdminLogin();
    Repair repair = new Repair();
    UltimateComboboxRefresher ultCBref = new UltimateComboboxRefresher();
    CustomerOrder cusOrder = new CustomerOrder();

    @FXML
    public void LoginAction(ActionEvent actionEvent) {

        String LoginInput = log.getText();
        String PassInput = pas.getText();

        boolean status = adminLogin.LoginStatus(LoginInput, PassInput);
        System.out.println(status);
        if (status == true) {
            statusBarForLogin.setText("You are logged in");
            userLoggedIn = status;
            //Enabale all the tabs after the login is correct
            startSceenPan.setDisable(false);
            motorhomeModPan.setDisable(false);
            reservePan.setDisable(false);
            customerOrderFunctioPan.setDisable(false);
            repairPan.setDisable(false);
        } else {
            statusBarForLogin.setText("Wrong password or username");
            userLoggedIn = status;
        }
    }

    @FXML
    public void motorHomeModsAddingMH(ActionEvent actionEvent) {
        //add a motorhome to the data base
        String theBrand = addBrandTxtField.getText();
        String theModel = addModelTxtField.getText();
        String thePrice = addPriceTxtField.getText(); /// Needs a fix to be a double not a fcking string
        String theBed = (String) addBedComBox.getValue();
        String theMileage = addMileageTxtField.getText();

        motorhomeModification.addMotorHome(theBrand, theModel, thePrice, theBed, theMileage);
        statusBarForSuccessesfullyAddingMH.setText("Status: Congratulations! " + theBrand + " " + theModel + " has been saved!");
    }

    @FXML
    public void localValueComboBoxes(MouseEvent mouseEvent){
        //make the beds only available from 2 to 6 in the combo box
        ObservableList<String> beds = FXCollections.observableArrayList();
        beds.addAll("2","3","4","5","6");
        addBedComBox.setItems(beds);
        updateBeds.setItems(beds);
        //make mh unaivalable or available
        ObservableList<String> availability = FXCollections.observableArrayList();
        availability.addAll("Available","Unavailable");
        updateAvailability.setItems(availability);
        //select the extra items you want
        ObservableList<String> extraItems = FXCollections.observableArrayList();
        extraItems.addAll("Baby seat", "Bike rack", "Table");
        listOfExtraItemsComBox.setItems(extraItems);
    }

    @FXML
    public void motorHomeModsUpdatingMH(ActionEvent actionEvent) {
        //updates existing motorhomes
        String beds = (String) updateBeds.getValue();
        String availability = (String) updateAvailability.getValue();

        String ID = updateID.getText();
        String brand = updateMark.getText();
        String model = updateModel.getText();
        String price = updatePrice.getText();

        motorhomeModification.updatingMotorHomne(ID, brand, model, price, beds, availability);
        statusBarForSuccessesfullyAddingMH.setText("Status: Congratulations! " + brand + " " + model + " has been updated!");
    }

    @FXML
    public void motorHomeModsDeleteMH(ActionEvent actionEvent) {
        String deleteID = updateID.getText();
        motorhomeModification.DeleteMotorHome(deleteID);
    }
    //FROM RESERVEMH CLASS
    @FXML
    public void extraItemCatalogComBox(MouseEvent mouseEvent) {
        ObservableList<String> extraItems = FXCollections.observableArrayList();
        extraItems.addAll("Baby seat", "Bike rack", "Table");
        listOfExtraItemsComBox.setItems(extraItems);
    }


    @FXML
    public void addExtaItemAction(ActionEvent actionEvent) {

        String item = (String) listOfExtraItemsComBox.getValue();
        String listString = "";

        for (String s : ReserveMH.addExtraShit(item)) {
            listString += s + "\n";
            System.out.println();
        }
        extraItemsTxtArea.setText(listString);
        String sizes = Integer.toString(ReserveMH.items.size());
        totalItems.setText(sizes);
        System.out.println(sizes);
    }

    @FXML
    public void setItemsToNull(ActionEvent actionEvent) {
        ReserveMH.items.clear();
        extraItemsTxtArea.setText("");
        String sizes = Integer.toString(ReserveMH.items.size());
        totalItems.setText(sizes);
    }

    @FXML
    public void removeLastExtraItem(ActionEvent actionEvent) {
        ReserveMH.items.remove(ReserveMH.items.size() - 1);
        extraItemsTxtArea.setText("");
        String listString = "";

        for (String s : ReserveMH.items) {
            listString += s + "\n";
            System.out.println();

        }
        extraItemsTxtArea.setText(listString);
        String sizes = Integer.toString(ReserveMH.items.size());
        totalItems.setText(sizes);
    }

    public void setAllValuesFromComboboxToTextField(ActionEvent actionEvent) {

        String valuesFromComBox = (String) listOfMHforUpdating.getValue();
        String ID = valuesFromComBox.substring(0, 2);
        System.out.println(valuesFromComBox);

        updateAvailability.setValue(motorhomeModification.Load(ID).get(0));
        updateMark.setText(motorhomeModification.Load(ID).get(1));
        updateModel.setText(motorhomeModification.Load(ID).get(2));
        updatePrice.setText(motorhomeModification.Load(ID).get(3));
        updateBeds.setValue(motorhomeModification.Load(ID).get(4));
        updateID.setText(ID);
    }
   @FXML
    public  void calculatePriceAction(ActionEvent actionEvent){
        //Checks which season it is--------------------------------------
       System.out.println(startDateMONTHTxtField.getValue());
       if ( startDateMONTHTxtField.getValue() == null|| startDateDAYTxtField.getValue()== null || endDateMONTHTxtField.getValue()== null || endDateDAYTxtField.getValue() == null ){
           System.out.println(" Ble cyka you need normal value");
           whichSeason.setText("Fill all the dates!");
           whichSeason.setFont(Font.font ("Verdana", 17));

       }else if ( reserverCombo.getValue() == null){
            System.out.println(" Ble cyka you need normal value");
           whichSeason.setFont(Font.font ("Verdana", 17));
            statusBarForReserver.setText("Please check if MH selected");



        }else{
           //String StartDate = "2017 05 15";
           //String EndDate = "2017 05 20";



           int startDay   = Integer.parseInt( (String)startDateDAYTxtField.getValue());
           int startMonth = Integer.parseInt( (String)startDateMONTHTxtField.getValue());
           int startYear     = Integer.parseInt((String)startDateYEARtxtField.getValue());

           int endDay     = Integer.parseInt((String)endDateDAYTxtField.getValue());
           int endMonth   = Integer.parseInt((String)endDateMONTHTxtField.getValue());
           int endYear   = Integer.parseInt((String)endDateYEARtxtField.getValue());

           String StartDate = startYear+ " "+ startMonth+" "+ startDay;
           String EndDate = endYear+ " "+ endMonth+" "+ endDay;
           int howManyDays = Integer.parseInt( cusOrder.dayCounterStartEnd(StartDate,EndDate)) ;

            ReserveMH rmh = new ReserveMH();
            whichSeason.setText(rmh.season(startMonth));
            int seasonPercentage =ReserveMH.season(whichSeason.getText());
            String price = (String) reserverCombo.getValue();
            int extraItemPrice = ReserveMH.items.size() *10; //price for extra shiy
            String[] arr = price.split(" ");
            int motorhomePrice = Integer.parseInt(arr[5]);
            int motorhomePriceforDay =motorhomePrice/30;
            int seasonPrice= (((motorhomePriceforDay *howManyDays) )/ 100 *  seasonPercentage);
            int lastPrice = seasonPrice + (motorhomePriceforDay *howManyDays) + extraItemPrice;
            finalPrice.setText(Integer.toString(lastPrice));//
            System.out.println(seasonPercentage);
            statusBarForReserver.setText("Price was calculated  Total: "+ seasonPrice);
           System.out.println(howManyDays);
        }
    //----------------------------------------------------------------
}
    //FROM REPAIR CLASS ###############################
    @FXML
    public void setAllValuesForMechanic(ActionEvent actionEvent){
        //set the text into the text fields from the combo box directly
        String valuesFromComBox = (String) repairListOFMHforMechanic.getValue();
        String ID = valuesFromComBox.substring(0,2);
        System.out.println(valuesFromComBox);

        idForMechanic.setText(ID);
        brandForMechanic.setText(repair.loadItUpForTheMechanic(ID).get(0));
        modelForMechanic.setText(repair.loadItUpForTheMechanic(ID).get(1));
        bedForMechanic.setText(repair.loadItUpForTheMechanic(ID).get(2));
        fuelForMechanic.setText(repair.loadItUpForTheMechanic(ID).get(3)+ " [L]");
        mileageForMechanic.setText(repair.loadItUpForTheMechanic(ID).get(4)+ " [km]");
    }
    //FROM REPAIR CLASS ###############################
    @FXML
    public void checkUpForMechanic(ActionEvent actionEvent) {

        String  repairMHid       = idForMechanic.getText();
        boolean oilSituation     = oilCheck.isSelected();
        boolean fuelSituation    = fuelCheck.isSelected();
        boolean waterSituation   = waterCheck.isSelected();
        boolean cleanSituation   = cleanCheck.isSelected();
        boolean repairsSituation = repairNeededCheck.isSelected();

        repair.serviceComplete(oilSituation,fuelSituation,waterSituation,cleanSituation,repairsSituation, repairMHid);

        oilCheck.setSelected(false);
        fuelCheck.setSelected(false);
        waterCheck.setSelected(false);
        cleanCheck.setSelected(false);
        repairNeededCheck.setSelected(false);
    }
    @FXML
    public  void saveOrderAction(ActionEvent actionEvent){
       if (singitureTxtField.getText().isEmpty() ||finalPrice.getText().isEmpty()){
           statusBarForReserver.setText("Please Check final price or signature u DIP");
           } else {
           String startYear = (String) startDateYEARtxtField.getValue();
           String startMonth = (String) startDateMONTHTxtField.getValue();
           String startDay = (String) startDateDAYTxtField.getValue();
           String endYear = (String) endDateYEARtxtField.getValue();
           String endMonth = (String) endDateMONTHTxtField.getValue();
           String endDay = (String) endDateDAYTxtField.getValue();
           String Season = whichSeason.getText();
           String itemAmount = totalItems.getText();
           String cost = finalPrice.getText();
           String signiture = singitureTxtField.getText();
           String reservedID= (String)reserverCombo.getValue();
           String reservedID2 = reservedID.substring(0, 1);
           ReserveMH.saveOrder(startYear, startMonth, startDay, endYear, endMonth, endDay, Season, itemAmount, cost, signiture,reservedID2 );
           statusBarForReserver.setText("Your oder " + signiture+" was successful");
       }
       reserverCombo.setValue("");
    }
    @FXML
    public  void lalalalaal(ActionEvent actionEvent) {

        String compareDataStart = " ";
        int a = 0;

        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.addAll(2017,2018,2019);

        ObservableList<String> empty = FXCollections.observableArrayList();
        empty.addAll(" ");

        a =   (Integer ) startDateYEARtxtField.getValue();



        System.out.println(" this shit is: "+compareDataStart);

        if(a >= 2017){
            endDateYEARtxtField.setItems(years);
        }else{
            endDateYEARtxtField.setItems(empty);

        }

    }

    @FXML
    public  void datesForReserve(MouseEvent mouseEvent){
        //year
        String compareDataEnd;


        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.addAll(2017,2018,2019);

        startDateYEARtxtField.setItems(years);
        //startDateYEARtxtField.setSelected Index(0);



        //compareDataEnd   =   (String )endDateYEARtxtField.getValue();

        int b = 0;

       // b = Integer.parseInt(compareDataEnd);












        //month
        ObservableList<String> months = FXCollections.observableArrayList();
        for ( int i = 1; i < 13; i++) {
            months.add(Integer.toString(i));
        }

        startDateMONTHTxtField.setItems(months);
        endDateMONTHTxtField.setItems(months);
        //days
        ObservableList<String> days = FXCollections.observableArrayList();
        for ( int i = 1; i < 32; i++) {
            days.add(Integer.toString(i));
        }

        startDateDAYTxtField.setItems(days);
        endDateDAYTxtField.setItems(days);
    }
    //Ultimate refresher for comebo boxes that gets their value directly from data base for motorhome
    @FXML
    public  void ultimateMotorhomeListForComboboxReresher(MouseEvent mouseEvent){

        startScreenComBox.setItems(ultCBref.myUltimateRefresh("SS"));
        listOfMHforUpdating.setItems(ultCBref.myUltimateRefresh("Update"));
        repairListOFMHforMechanic.setItems(ultCBref.myUltimateRefresh("Repair"));
        reserverCombo.setItems(ultCBref.myUltimateRefresh("Reserve"));
    }
    //Ultimate refresher for comebo boxes that gets their value directly from data base for customer names a.k.a. signature
    @FXML
    public void ultimateCustomerListComboboxRefresher(MouseEvent mouseEvent){
    pickUpCustomer.setItems(ultCBref.customerOrder("pickUp"));
    turnInCustomer.setItems(ultCBref.customerOrder("turnIn"));
    cancelReservationCustomer.setItems(ultCBref.customerOrder("cancel"));
    }
    @FXML
    public void customerPickUp(ActionEvent actionEvent){
        String nameOfTheGuyWhoPicksUp = (String ) pickUpCustomer.getValue();
        cusOrder.pickUp(nameOfTheGuyWhoPicksUp);
        receiptTxtArea.setText("Customer who picked up: "+nameOfTheGuyWhoPicksUp);
    }
    @FXML
    public void orderCancelation(ActionEvent actionEvent){
        String Signature = (String) cancelReservationCustomer.getValue();
        int daysBeforeStart = Integer.parseInt(cusOrder.dateDffCounter(Signature));


        int kaina = Integer.parseInt(cusOrder.Reservation(Signature).get(0));
        System.out.println(daysBeforeStart);
        daysCounter.setText(cusOrder.dateDffCounter(Signature));
        int refund = cusOrder.penalty(daysBeforeStart,kaina);
        int sugrazinta = kaina - refund;
        System.out.println("Days beror start "+daysBeforeStart);
        System.out.println("Kaina " + kaina);
        System.out.println("penalty" +  refund);
        System.out.println("Sugrazinta suma" + sugrazinta);

        receiptTxtArea.setText(
               "Days berore start "+daysBeforeStart +" " + "\n" +
                "Price                 " + kaina + "\n" +
                "Penalty               " +  refund +" " + "\n" +
                "Returned ammount  " + sugrazinta + "\n"

        );

             }

    @FXML
    public void customerTurnIn(ActionEvent actionEvent){

        if(turnInCustomer.getSelectionModel().isEmpty()){
            receiptTxtArea.setText("Pick a name from combo box you dip");
        }else{
            String nameOfTheGuyWhoTurnIns = (String ) turnInCustomer.getValue();
            int currentMileage = Integer.parseInt( turnInMileage.getText());
            int currentFuel = Integer.parseInt(turnInFuel.getText());

            cusOrder.turnIn(nameOfTheGuyWhoTurnIns, currentMileage, currentFuel);
            receiptTxtArea.setText("Customer who turned in: "+nameOfTheGuyWhoTurnIns);

        }
    }
    @FXML
    public void setCurentMileageTurnIn(ActionEvent actionEvent){

        String a;
        String customer = (String) turnInCustomer.getValue();
        a = cusOrder.mileagaBeforeTheTrip(customer);
        mileageBeforeTrip.setText("Before trip mileage: "+a+" [km]");
    }


}
