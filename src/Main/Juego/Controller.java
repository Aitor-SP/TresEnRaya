package Main.Juego;

import Main.Jugador.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private GridPane gridBotones;
    @FXML private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML private Button btnStart;
    @FXML private ToggleGroup grupo1;
    @FXML private RadioButton rbtnP1P2, rbtnP1Cpu, rbtnCpuCpu;
    @FXML private MenuItem menuNueva;
    @FXML private MenuItem menuTema;
    @FXML private MenuItem menuAcercaDe;
    @FXML private Label labelInfo;
    @FXML private TextField txtP1;
    @FXML private TextField txtP2;
    private boolean turno = false;
    private Scene scene;
    private Stage stage;
    private int nturno;
    private String P1;
    private String P2;
    Jugador jugador;
    List<Button> buttonList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnStart.setDisable(true);
        txtP1.setVisible(false);
        txtP2.setVisible(false);
        labelInfo.setVisible(false);
        gridBotones.setDisable(true);
        buttonList = Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        nturno = 0;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    //Cerramos el juego
    public void clickClose() {
        stage.close();
    }

    //Cambiamos el tema del juego entre Tema Claro y Oscuro
    public void clickMenuTema() {
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

    //Nos muestra la ventana de Acerca de
    public void clickMenuAcercaDe() throws IOException {
        if (menuAcercaDe.getText().equals("Acerca de")) {
            Parent root = FXMLLoader.load(getClass().getResource("/Main/AcercaDe/Acercade.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Acerca de");
            stage.setScene(scene);
            stage.show();
        }
    }

    //Accedemos al Hall de la Fama donde aparecen los ganadores
    public void clickHallOfFame (ActionEvent actionEvent) throws IOException {
        Button btnHall = (Button) actionEvent.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("/Main/Ranking/ranking.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Hall de la Fama");
        stage.setScene(scene);
        stage.show();
    }

    //Metodo para hacer funcionar los botones del tablero
    public void clickBtn(ActionEvent actionEvent) {
        Button bt = (Button) actionEvent.getSource();
        int random = (int) (Math.random() * 9);
        Boolean cpuWin = false;

        //P1 VS P2
        if (rbtnP1P2.isSelected()) {
            if (!turno){
                bt.setText("X");
                labelInfo.setText("TURNO DE O > "+txtP2.getText());
                turno = true;
                nturno++;
            }else{
                bt.setText("O");
                labelInfo.setText("TURNO DE X > "+txtP1.getText());
                turno = false;
                nturno++;
            }
        }

        //P1VSCPU
        if (rbtnP1Cpu.isSelected()) {
            if (!turno){
                bt.setText("X");
                labelInfo.setText("TURNO DE O > "+txtP2.getText());
                turno = true;
                nturno++;
            }else{
                bt = buttonList.get(random);
                if ("".equals(bt.getText())){
                    while (!cpuWin) {
                        if (!turno){
                            bt.setText("X");
                            labelInfo.setText("TURNO DE O > "+txtP2.getText());
                            turno = true;
                            nturno++;
                        }else{
                            bt.setText("O");
                            labelInfo.setText("TURNO DE X > "+txtP1.getText());
                            turno = false;
                            nturno++;
                        }
                        cpuWin = true;
                    }
                }
            }
        }

        //CPU VS CPU (Hay que clicar en la pantalla)
        if (rbtnCpuCpu.isSelected()){
            bt = buttonList.get(random);
                if ("".equals(bt.getText())){
                    while (!cpuWin) {
                    if (!turno){
                        bt.setText("X");
                        labelInfo.setText("TURNO DE O > "+txtP2.getText());
                        turno = true;
                        nturno++;
                    }else{
                        bt.setText("O");
                        labelInfo.setText("TURNO DE X > "+txtP1.getText());
                        turno = false;
                        nturno++;
                    }
                    cpuWin = true;
                }
            }
        }
        winCondition();
        dialogEmpate();
    }

    // Iniciar el juego teniendo en cuenta la opción elegida
    public void clickStart (ActionEvent actionEvent) {
        RadioButton optradioButton = (RadioButton) grupo1.getSelectedToggle();
        if (optradioButton!=null) {
            if (rbtnP1P2.isSelected()){
                P1vsP2(actionEvent);
            }
            if (rbtnP1Cpu.isSelected()){
                P1vsCPU(actionEvent);
            }
            if (rbtnCpuCpu.isSelected()){
                CPUvsCPU(actionEvent);
            }
        }
        gridBotones.setDisable(false);
        pruebaBug();
    }

    //Funcionamiento P1vsP2
    public void P1vsP2(ActionEvent actionEvent) {
        btnStart.setDisable(false);
        txtP1.setVisible(true);
        txtP2.setVisible(true);
        txtP1.setDisable(false);
        txtP2.setDisable(false);
        labelInfo.setVisible(true);
        P1 = txtP1.getText();
        P2 = txtP2.getText();
        winCondition();
        dialogEmpate();
    }

    //Funcionamiento de un P1vsCPU
    public void P1vsCPU(ActionEvent actionEvent) {
        btnStart.setDisable(false);
        txtP1.setVisible(true);
        txtP1.setDisable(false);
        txtP2.setVisible(false);
        txtP2.setText("R2D2");
        labelInfo.setVisible(true);
        P1 = txtP1.getText();
        P2 = txtP2.getText();
        winCondition();
        dialogEmpate();
    }

    //Funcionamiento de CPUvsCPU
    public void CPUvsCPU(ActionEvent actionEvent) {
        btnStart.setDisable(false);
        txtP1.setVisible(false);
        txtP1.setText("SKYNET");
        txtP2.setText("HAL9000");
        txtP2.setVisible(false);
        labelInfo.setVisible(true);
        P1 = txtP1.getText();
        P2 = txtP2.getText();
        winCondition();
        dialogEmpate();
    }

    //Comprobamos las combinaciones
    public void winCondition(){
        //Horizontales
        if(!btn1.getText().equals("") && !btn2.getText().equals("") && btn1.getText().equals(btn2.getText()) && btn2.getText().equals(btn3.getText())) {
        dialogWin();
        }
        if(!btn4.getText().equals("") && !btn5.getText().equals("") && btn4.getText().equals(btn5.getText()) && btn5.getText().equals(btn6.getText())) {
            dialogWin();
        }
        if(!btn7.getText().equals("") && !btn8.getText().equals("") && btn7.getText().equals(btn8.getText()) && btn8.getText().equals(btn9.getText())) {
            dialogWin();
        }
        //Verticales
        if(!btn1.getText().equals("") && !btn4.getText().equals("") && btn1.getText().equals(btn4.getText()) && btn4.getText().equals(btn7.getText())) {
            dialogWin();
        }
        if(!btn2.getText().equals("") && !btn5.getText().equals("") && btn2.getText().equals(btn5.getText()) && btn5.getText().equals(btn8.getText())) {
            dialogWin();
        }
        if(!btn3.getText().equals("") && !btn6.getText().equals("") && btn3.getText().equals(btn6.getText()) && btn6.getText().equals(btn9.getText())) {
            dialogWin();
        }
        //Diagonales
        if(!btn1.getText().equals("") && !btn5.getText().equals("") && btn1.getText().equals(btn5.getText()) && btn5.getText().equals(btn9.getText())) {
            dialogWin();
        }
        if(!btn3.getText().equals("") && !btn5.getText().equals("") && btn3.getText().equals(btn5.getText()) && btn5.getText().equals(btn7.getText())) {
            dialogWin();
        }
    }

    //Salta un dialogo al ganar la partida
    public void dialogWin (){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);


        if (turno){
            VBox vbox = new VBox(new Text("¡¡VICTORIA "+P1+"!!"));
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(50));
            dialogStage.setScene(new Scene(vbox));
            dialogStage.showAndWait();
        } else {
            VBox vbox = new VBox(new Text("¡¡VICTORIA "+P2+"!!"));
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(50));
            dialogStage.setScene(new Scene(vbox));
            dialogStage.showAndWait();
        }
        resetJuego();
        pruebaUnbug();
    }

    //Salta un dialogo al empatar la partida
    public void dialogEmpate() {
        if (nturno > 8){
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);

            VBox vbox = new VBox(new Text("EMPATE"));
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(50));

            dialogStage.setScene(new Scene(vbox));
            dialogStage.showAndWait();
            resetJuego();
            pruebaUnbug();
        }
    }
    //Metodo para que no se puedan pulsar de nuevo el Start y los RB al empezar la partida
    public void pruebaBug(){
        rbtnP1P2.setDisable(true);
        rbtnP1Cpu.setDisable(true);
        rbtnCpuCpu.setDisable(true);
        txtP1.setDisable(true);
        txtP2.setDisable(true);
        btnStart.setDisable(true);
    }

    //Metodo para volver a pulsar los botones
    public void pruebaUnbug(){
        rbtnP1P2.setDisable(false);
        rbtnP1Cpu.setDisable(false);
        rbtnCpuCpu.setDisable(false);
        btnStart.setDisable(true);
        txtP1.setVisible(false);
        txtP2.setVisible(false);
        labelInfo.setText("");
        nturno = 0;
    }

    //Reinicia el juego
    public void clickNuevaPartida () {
        if (menuNueva.getText().equals("Nueva Partida"))
            btn1.setText("");
            btn2.setText("");
            btn3.setText("");
            btn4.setText("");
            btn5.setText("");
            btn6.setText("");
            btn7.setText("");
            btn8.setText("");
            btn9.setText("");
            labelInfo.setText("");
            rbtnCpuCpu.setSelected(false);
            rbtnP1Cpu.setSelected(false);
            rbtnP1P2.setSelected(false);
            rbtnP1P2.setDisable(false);
            rbtnP1Cpu.setDisable(false);
            rbtnCpuCpu.setDisable(false);
            btnStart.setDisable(true);
            txtP1.setVisible(false);
            txtP2.setVisible(false);
            nturno = 0;
    }

    //Otro uso del reseteo del juego
    public void resetJuego(){
        nturno = 0;
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        labelInfo.setText("");
        rbtnCpuCpu.setSelected(false);
        rbtnP1Cpu.setSelected(false);
        rbtnP1P2.setSelected(false);
        rbtnP1P2.setDisable(false);
        rbtnP1Cpu.setDisable(false);
        rbtnCpuCpu.setDisable(false);
        btnStart.setDisable(true);
        txtP1.setVisible(false);
        txtP2.setVisible(false);
    }
}

