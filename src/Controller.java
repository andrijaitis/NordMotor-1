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

import static java.sql.JDBCType.INTEGER;
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
    private javafx.scene.control.PasswordField pas;
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
    private javafx.scene.control.Label finalPrice;
    @FXML
    private javafx.scene.control.Label statusBarForSuccessesfullyAddingMH;
    @FXML
    private javafx.scene.control.Label statusBarForService;
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
    private javafx.scene.control.Label updateID;
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
    private javafx.scene.control.Label idForMechanic;
    @FXML
    private javafx.scene.control.Label brandForMechanic;
    @FXML
    private javafx.scene.control.Label modelForMechanic;
    @FXML
    private javafx.scene.control.Label bedForMechanic;
    @FXML
    private javafx.scene.control.Label fuelForMechanic;
    @FXML
    private javafx.scene.control.Label mileageForMechanic;
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
    private int globalBeforeTripMileage =0;


    //Class instences
    MotorhomeModification motorhomeModification = new MotorhomeModification();
    AdminLogin adminLogin = new AdminLogin();
    Repair repair = new Repair();
    UltimateComboboxRefresher ultCBref = new UltimateComboboxRefresher();
    CustomerOrder cusOrder = new CustomerOrder();
    ReserveMH reserveMH = new ReserveMH();

    //Loging in to the system
    @FXML
    public void LoginAction(ActionEvent actionEvent) {

        String LoginInput = log.getText();
        String PassInput  = pas.getText();

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
    //Adding a motorhome
    @FXML
    public void motorHomeModsAddingMH(ActionEvent actionEvent) {
        String thePrice     = addPriceTxtField.getText(); /// Needs a fix to be a double not a fcking string
        String theMileage   = addMileageTxtField.getText();

        try {

            String theBrand = addBrandTxtField.getText();
            String theModel = addModelTxtField.getText();
            Integer.parseInt(addPriceTxtField.getText());//not used####################
            Integer.parseInt(addMileageTxtField.getText());//not used####################
            String theBed   = (String) addBedComBox.getValue();

            motorhomeModification.addMotorHome(theBrand, theModel, thePrice, theBed, theMileage);
            statusBarForSuccessesfullyAddingMH.setText("Status: Congratulations! " + theBrand + " " + theModel + " has been saved!");

        } catch (NumberFormatException e) {
            statusBarForSuccessesfullyAddingMH.setText("Please use number values without decimals");

           }
    }
    //We use it to store stuff that is not database connected
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
        extraItems.addAll("Baby seat", "Bike rack", "Table", "Umbrella");
        listOfExtraItemsComBox.setItems(extraItems);
        //year
        ObservableList<String> years = FXCollections.observableArrayList();
        years.addAll("2017","2018","2019","2020");
        startDateYEARtxtField.setItems(years);
        endDateYEARtxtField.setItems(years);
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
    //Update combobox MH list to set the values in Text fields
    @FXML
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
    //Updates existing motorhomes
    @FXML
    public void motorHomeModsUpdatingMH(ActionEvent actionEvent) {

        try {
            Integer.parseInt(updatePrice.getText());//not used####################
            String price = updatePrice.getText();
            String beds = (String) updateBeds.getValue();
            String availability = (String) updateAvailability.getValue();

            String ID = updateID.getText();
            String brand = updateMark.getText();
            String model = updateModel.getText();


            motorhomeModification.updatingMotorHomne(ID, brand, model, price, beds, availability);
            statusBarForSuccessesfullyAddingMH.setText("Status: Congratulations! " + brand + " " + model + " has been updated!");


        } catch (NumberFormatException e) {
            statusBarForSuccessesfullyAddingMH.setText("Please use number values without decimals");
        }
    }
    //Delete MH
    @FXML
    public void motorHomeModsDeleteMH(ActionEvent actionEvent) {
        String deleteID = updateID.getText();
        motorhomeModification.DeleteMotorHome(deleteID);
    }
    //Add an extra item
    @FXML
    public void addExtaItemAction(ActionEvent actionEvent) {

        String item = (String) listOfExtraItemsComBox.getValue();

        if (listOfExtraItemsComBox.getValue()== null) {
            statusBarForReserver.setText("Check if you selected extra items!");
        } else {
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
    }
    //clear the list of extra items
    @FXML
    public void setItemsToNull(ActionEvent actionEvent) {
        ReserveMH.items.clear();
        extraItemsTxtArea.setText("");
        String sizes = Integer.toString(ReserveMH.items.size());
        totalItems.setText(sizes);
    }
    //Remove last extra item
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
    //Calculates the price for reserving a MH
   @FXML
    public  void calculatePriceAction(ActionEvent actionEvent){
       int startDay   = Integer.parseInt( (String)startDateDAYTxtField.getValue());
       int startMonth = Integer.parseInt( (String)startDateMONTHTxtField.getValue());
       int startYear  = Integer.parseInt( (String)startDateYEARtxtField.getValue());

       int endDay    = Integer.parseInt((String)endDateDAYTxtField.getValue());
       int endMonth  = Integer.parseInt((String)endDateMONTHTxtField.getValue());
       int endYear   = Integer.parseInt((String)endDateYEARtxtField.getValue());

       String startDate = startYear + " " + startMonth + " " + startDay ;
       String endDate   = endYear   + " " + endMonth   + " " + endDay   ;
       int howManyDays = Integer.parseInt( cusOrder.dayCounterStartEnd(startDate,endDate)) ;

       System.out.println(startDateMONTHTxtField.getValue());
       if ( startDateMONTHTxtField.getValue() == null|| startDateDAYTxtField.getValue()== null || endDateMONTHTxtField.getValue()== null || endDateDAYTxtField.getValue() == null ){
           System.out.println(" Ble cyka you need normal value");
           whichSeason.setText("Fill all the dates!");
           whichSeason.setFont(Font.font ("Verdana", 17));

       }else if ( reserverCombo.getValue() == null){
            System.out.println(" Ble cyka you need normal value");
           whichSeason.setFont(Font.font ("Verdana", 17));
            statusBarForReserver.setText("Please check if MH selected");

       }else if (howManyDays <= 0){
           System.out.println(" Ble cyka you need normal value");
           whichSeason.setFont(Font.font ("Verdana", 17));
           statusBarForReserver.setText("Choose normal date");
       }else if (Integer.parseInt( cusOrder.currentDaytoStartDate(startDate)) < 0){
           System.out.println(" Ble cyka you need normal value");
           whichSeason.setFont(Font.font ("Verdana", 17));
           statusBarForReserver.setText("You cant travel time m88888: "+startDate);

       }else{

            ReserveMH rmh = new ReserveMH();
            whichSeason.setText(rmh.season(startMonth));
            int seasonPercentage =ReserveMH.season(whichSeason.getText());
            String price = (String) reserverCombo.getValue();
            int extraItemPrice = ReserveMH.items.size() *10; //price for extra shiy
            String[] arr = price.split(" ");
            int motorhomePrice = Integer.parseInt(arr[5]);
            int motorhomePriceforDay =motorhomePrice;
            int seasonPrice= (((motorhomePriceforDay *howManyDays) )/ 100 *  seasonPercentage);
            int lastPrice = seasonPrice + (motorhomePriceforDay *howManyDays) + extraItemPrice;
            finalPrice.setText(Integer.toString(lastPrice));//
            System.out.println(seasonPercentage);
            statusBarForReserver.setText("Price was calculated  Total: "+ lastPrice);
           System.out.println(howManyDays);
           System.out.println(cusOrder.currentDaytoStartDate(startDate) + "u dipppppppp");
        }
}
    //Repair combobox MH list to set the values in Text fields
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
    //Check list checks if all the maintenance is done
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
        statusBarForService.setText("Service information for this motorhome was saved");
    }
    //saves the reserved order
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
    public void orderCancelation(ActionEvent actionEvent) {

        if (cancelReservationCustomer.getSelectionModel().isEmpty()) {
            receiptTxtArea.setText("Pick a name from combo box you dip");
        } else {
            String nameOfTheGuyWhoCancel = (String) cancelReservationCustomer.getValue();

            cusOrder.orderCancelation(nameOfTheGuyWhoCancel);

            int daysBeforeStart = Integer.parseInt(cusOrder.dateDffCounter(nameOfTheGuyWhoCancel));
            int kaina = Integer.parseInt(cusOrder.Reservation(nameOfTheGuyWhoCancel).get(0));
            System.out.println(daysBeforeStart);
            daysCounter.setText(cusOrder.dateDffCounter(nameOfTheGuyWhoCancel));
            int refund = cusOrder.penalty(daysBeforeStart, kaina);
            int sugrazinta = kaina - refund;
            System.out.println("Days beror start " + daysBeforeStart);
            System.out.println("Kaina " + kaina);
            System.out.println("penalty" + refund);
            System.out.println("Sugrazinta suma" + sugrazinta);

            receiptTxtArea.setText(

                            "Days berore start " + daysBeforeStart       + " " + "\n" +
                            "Price             " + kaina                 + " " + "\n" +
                            "Penalty           " + refund                + " " + "\n" +
                            "Guy who cancelled " + nameOfTheGuyWhoCancel + " " + "\n" +
                            "Returned amount   " + sugrazinta            + "\n"
            );
        }
    }

    @FXML
    public void customerTurnIn(ActionEvent actionEvent){
        System.out.print(" GLOBAL MILEAGE: " + globalBeforeTripMileage);

        if(turnInCustomer.getSelectionModel().isEmpty()){
            receiptTxtArea.setText("Pick a name from combo box you dip");
        }else{
            String nameOfTheGuyWhoTurnIns = (String ) turnInCustomer.getValue();
            int currentMileage = Integer.parseInt( turnInMileage.getText());
            int currentFuel = Integer.parseInt(turnInFuel.getText());

            if(currentMileage <= globalBeforeTripMileage){
                turnInMileage.setText("Mileage too low!");
            }else{
                if(currentFuel <= 0 || currentFuel >=200){
                    turnInFuel.setText("Fuel negative/too much");
                }else{
                    cusOrder.turnIn(nameOfTheGuyWhoTurnIns, currentMileage, currentFuel);
                    receiptTxtArea.setText("Customer who turned in: "+nameOfTheGuyWhoTurnIns);
                }
                           }
        }
    }
    @FXML
    public void setCurentMileageTurnIn(ActionEvent actionEvent){

        String beforeTripMileage;
        String customer = (String) turnInCustomer.getValue();
        beforeTripMileage = cusOrder.mileagaBeforeTheTrip(customer);
        globalBeforeTripMileage = Integer.parseInt(beforeTripMileage) ;
        mileageBeforeTrip.setText("Before trip mileage: "+beforeTripMileage+" [km]");
    }
    //Shuts down the system
    @FXML
    public  void turnOffProgram(ActionEvent actionEvent){
        System.exit(1);
    }


}
