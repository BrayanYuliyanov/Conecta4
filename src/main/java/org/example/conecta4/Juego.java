package org.example.conecta4;

import java.util.Scanner;

import static org.example.conecta4.Config.*;

public class Juego {

    public static void main(String[] args) {
        int turno = 1;
        char ganador = NINGUNO;
        Tablero tablero = new Tablero();

        Scanner teclado = new Scanner(System.in);

        System.out.println("Conecta 4");

        while(ganador == NINGUNO) {
            tablero.pintar();
            System.out.println("Turno de jugador: " + turno);
            System.out.print("Indica la columna: ");
            int columna = Integer.parseInt(teclado.nextLine());
            char valorTurno = String.valueOf(turno).charAt(0);
            boolean b = tablero.meterFicha(columna, valorTurno);

            if (b) {
                turno++;
                if (turno > JUGADORES) {
                    turno = 1;
                }
            }

            ganador = tablero.getGanador();
        }

        tablero.pintar();
        if (ganador != EMPATE) {
            System.out.println("Gana el jugador: " + ganador);
        } else {
            System.out.println("Empate");
        }
    }


//    public void run() {
//        inicio();
//        gameLoop();
//        fin();
//    }
//
//    private void inicio() {
//
//    }
//
//    private void gameLoop() {
//
//    }
//
//    private void fin() {
//
//    }
}
