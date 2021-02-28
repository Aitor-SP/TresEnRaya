package Main.Juego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML private Button btnStart;
    @FXML private VBox opcionRadio;
    @FXML private ToggleGroup grupo1;
    @FXML private RadioButton rbtnP1P2, rbtnP1Cpu, rbtnCpuCpu;
    @FXML private MenuItem menuNueva;
    @FXML private MenuItem menuTema;
    @FXML private MenuItem menuAcercaDe;

    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    // Cerramos el juego
    public void clickClose() {
        stage.close();
    }

    // Al clicar en los botones aparece la X
    public void clickBtn(ActionEvent actionEvent) {
        Button bt = (Button) actionEvent.getSource();
        bt.setText("X");
    }

    // Cambiamos el tema del juego aplicando el estilo
    public void clickMenuTema(ActionEvent actionEvent) {
        String mode;
        scene.getStylesheets().clear();
        if(menuTema.getText().equals("Tema Oscuro")) {
            scene.getStylesheets().add("Main/CSS/Dark.css");
            mode = "Tema Claro";
        } else {
            scene.getStylesheets().add("Main/CSS/Light.css");
            mode = "Tema Oscuro";
        }
        menuTema.setText(mode);
    }

    // Nos muestra la ventana de Acerca de
    public void clickMenuAcercaDe(ActionEvent actionEvent) throws IOException {
        if (menuAcercaDe.getText().equals("Acerca de")) {
            Parent root = FXMLLoader.load(getClass().getResource("/Main/AcercaDe/Acercade.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Acerca de - Tres en Raya by Aitor");
            stage.setScene(scene);
            stage.show();
        }
    }

    // Reinicia el juego, pero no se como hacerlo mejor XD
    public void clickNuevaPartida (ActionEvent actionEvent) {
        if (menuNueva.getText().equals("Nueva Partida"))
            scene.getStylesheets().clear();
            btn1.setText("");
            btn2.setText("");
            btn3.setText("");
            btn4.setText("");
            btn5.setText("");
            btn6.setText("");
            btn7.setText("");
            btn8.setText("");
            btn9.setText("");
            rbtnCpuCpu.setSelected(false);
            rbtnP1Cpu.setSelected(false);
            rbtnP1P2.setSelected(false);
    }

    public void clickStart (ActionEvent actionEvent) {
        RadioButton optradioButton = (RadioButton) grupo1.getSelectedToggle();
        String id = optradioButton.getId();
    }
}
