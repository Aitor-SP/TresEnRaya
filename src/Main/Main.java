package Main;

import Main.Juego.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/Juego/sample.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("Main/CSS/Styles.css");

        controller.setScene(scene);
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tres en raya - Aitor");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}