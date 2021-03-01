package Main.Juego;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private int optJuego;
    private Controller controller;

    public Juego(int optJuego, Controller controller) {
        this.optJuego = optJuego;
        this.controller = controller;
    }

    private Button btnClick;
    List<Button> buttons = new ArrayList<>();
    List<String> btnSelected = new ArrayList<>();
    private int[] btnWin = new int[3];

    private enum Turno {
        X, O;
    }

    private Turno tablero[][] = new Turno[3][3];
    Turno turno = Turno.X;
    private String[] posWin;


    // Para cambiar el turno
    void cambioTurno() {
        if (turno.equals(Turno.X)) {
            turno = turno.O;
        } else {
            turno = Turno.X;
        }
    }

    // Comprobamos si hay tres en raya en todas las posiciones del tablero
    boolean checkWin() {
        boolean win = false;

        // Primera linea horizontal
        if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[0][1]) && tablero[0][1].equals(tablero[0][2]) )) {
            posWin = new String[]{"00", "01", "02"};
            win = true;
        }
        // Segunda linea horizontal
        else if ( (tablero[1][0] != (null)) && (tablero[1][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[1][2]) )) {
            posWin = new String[]{"10", "11", "12"};
            win = true;
        }
        // Tercera linea horizontal
        else if ( (tablero[2][0] != (null)) && (tablero[2][0].equals(tablero[2][1]) && tablero[2][1].equals(tablero[2][2]) )) {
            posWin = new String[]{"20", "21", "22"};
            win = true;
        }

        // Primera linea vertical
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][0]) && tablero[1][0].equals(tablero[2][0]) )) {
            posWin = new String[]{"00", "10", "20"};
            win = true;
        }
        // Segunda linea vertical
        else if ( (tablero[0][1] != (null)) && (tablero[0][1].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][1]) )) {
            posWin = new String[]{"01", "11", "21"};
            win = true;
        }
        // Tercera vertical
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][2]) && tablero[1][2].equals(tablero[2][2]) )) {
            posWin = new String[]{"02", "12", "22"};
            win = true;
        }
        // Diagonal de izquierda a derecha
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) )) {
            posWin = new String[]{"00", "11", "22"};
            win = true;
        }
        // Diagonal de derecha a izquierda
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0]) )) {
            posWin = new String[]{"02", "11", "20"};
            win = true;
        }
        return win;
    }

    // Modo 1
    private void P1vsP2() {
    }

    // Modo 2
    private void P1vsCPU(){
    }

    // Modo 3
    private void CPUvsCPU() {
    }
}
