package Main.Jugador;

import Main.Main;

public class Jugador {
    String jugador;
    int pGanadas;

    public Jugador(String jugador, int pGanadas) {
        this.jugador = jugador;
        this.pGanadas = pGanadas;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getpGanadas() {
        return pGanadas;
    }

    public void setpGanadas(int pGanadas) {
        this.pGanadas = pGanadas;
    }
}
