package Main.Ranking;

import Main.Jugador.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TableView<Jugador> tblView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn tblJugador = new TableColumn<Jugador, Object>("JUGADOR");
        TableColumn tblWinsJugador = new TableColumn("VICTORIAS");
        tblView.getColumns().addAll(tblJugador,tblWinsJugador);

        ObservableList<Jugador> data = FXCollections.observableArrayList(
                new Jugador("ESTE",99),
                new Jugador("RANKING",99),
                new Jugador("NO",99),
                new Jugador("FUNCIONA",99));

        tblView.setItems(data);
    }
}