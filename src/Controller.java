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


}
