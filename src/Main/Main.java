package Main;

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

        String cssDark = getClass().getResource("css/Light.css").toExternalForm();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        controller controller = loader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssDark);

        controller.setScene(scene);
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}