/**
 * Created by Kompas on 2017-05-11.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("nordicGUI.fxml"));
        primaryStage.setTitle("Nordic Motorhomes System");
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();
        //inga
    }


    public static void main(String[] args) {
        launch(args);
    }
}
