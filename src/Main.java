/**
 * Created by Kompas on 2017-05-11.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Everyone is responsible for this class
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("nordicGUI.fxml"));
        primaryStage.setTitle("Nordic Motorhomes System");
        primaryStage.setScene(new Scene(root, 900, 690));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
