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



public class Controller {

   private boolean userLoggedIn = false ;
    //AdminLogin CLASS variables >>>>>>>>>> for logingin
    @FXML
    private Label statusBar;
    @FXML
    public javafx.scene.control.TextField log;
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
    private javafx.scene.control.TextField brandTxtField;
    @FXML
    private javafx.scene.control.TextField modelTxtField;
    @FXML
    private javafx.scene.control.TextField priceTxtField;
    @FXML
    private javafx.scene.control.TextField bedTxtField;
    @FXML
    private javafx.scene.control.Label statusBarForSuccessesfullyAddingMH;
    //MotorhomeModification CLASS variables >>>>>>>>>> for updating
    @FXML
    private ComboBox ModifyMHcombo;
    //ReserveMH CLASS variables >>>>>>>>>> for adding extra item
    @FXML
    private javafx.scene.control.TextField extraItemsTxtField;
    @FXML
    private javafx.scene.control.TextArea extraItemsTxtArea;
    //ReserveMH CLASS variables >>>>>>>>>> for date season
    @FXML
    private javafx.scene.control.TextField startDateDAYTxtField;
    @FXML
    private javafx.scene.control.TextField startDateMONTHTxtField;
    @FXML
    private javafx.scene.control.TextField endDateDAYTxtField;
    @FXML
    private javafx.scene.control.TextField endDateMONTHTxtField;
    @FXML
    private javafx.scene.control.TextField whichSeason;

    public void LoginAction(ActionEvent actionEvent) {

        String LoginInput = log.getText();
        String PassInput = pas.getText();
        AdminLogin adminLogin = new AdminLogin();
        boolean status = adminLogin.LoginStatus(LoginInput, PassInput);
        System.out.println(status+ " aa");
        if (status == true) {
            statusBar.setText("You are logged in");
            userLoggedIn = status;
            //Enabale all the tabs after the login is correct
            startSceenPan.setDisable(false);
            motorhomeModPan.setDisable(false);
            reservePan.setDisable(false);
            customerOrderFunctioPan.setDisable(false);
            repairPan.setDisable(false);
        } else {
            statusBar.setText("Wrong password or username");
            userLoggedIn = status;
        }
    }
    @FXML
    public void motorHomeModsAddingMH(ActionEvent actionEvent){

        String theBrand = brandTxtField.getText();
        String theModel = modelTxtField.getText();
        String thePrice = priceTxtField.getText(); /// Needs a fix to be a double not a fcking string
        String theBed   = bedTxtField.getText();   /// Needs a fcking fix so its a combobox with ints NOT a string txt field

        MotorhomeModification mm = new MotorhomeModification();
        MotorhomeModification.addMotorHome(theBrand, theModel, thePrice, theBed);
        statusBarForSuccessesfullyAddingMH.setText("Status: Congratulations! "+theBrand+ " " +theModel+ "has been saved!");
    }
    @FXML
    public void motorHomeModsUpdatingMH(ActionEvent actionEvent){



    }
    @FXML
    public void RefreshMH(ActionEvent actionEvent){
        System.out.println("rehreshing");

        MotorhomeModification motorhomeModification = new MotorhomeModification();
        ObservableList<String> list = FXCollections.observableArrayList();
        String listString = "";
        ModifyMHcombo.setItems(motorhomeModification.refresh());

    }

    //FROM RESERVEMH CLASS
    @FXML
    public void addExtaItemAction(ActionEvent actionEvent){



        //String item = extraItemsTxtField.getText();

        //rmh.addExtra(item);

        //extraItemsTxtArea.setText(String.valueOf(day));
        //extraItemsTxtArea.setText(String.valueOf(month));

    }
    @FXML
    public  void calculatePriceAction(ActionEvent actionEvent){
        //Checks which season it is--------------------------------------
        if (startDateMONTHTxtField.getText().isEmpty() || startDateDAYTxtField.getText().isEmpty() || endDateMONTHTxtField.getText().isEmpty() || endDateDAYTxtField.getText().isEmpty()){
            System.out.println(" Ble cyka you need normal value");
            whichSeason.setText("Fill all the dates!");
            whichSeason.setFont(Font.font ("Verdana", 17));

        }else{
            int startDay   = Integer.parseInt(startDateDAYTxtField.getText());
            int startMonth = Integer.parseInt(startDateMONTHTxtField.getText());
            int endDay     = Integer.parseInt(endDateDAYTxtField.getText());
            int endMonth   = Integer.parseInt(endDateMONTHTxtField.getText());
            ReserveMH rmh = new ReserveMH();
            whichSeason.setText(rmh.season(startMonth));
        }
        //----------------------------------------------------------------
    }


}
