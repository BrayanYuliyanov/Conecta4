package org.example.conecta4;

import static org.example.conecta4.Config.*;

public class Tablero {

    private char[][] matriz;
    private int turno;

    public Tablero() {
        matriz = new char[FILAS][COLUMNAS];
        turno = 1;
        limpiar();
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public int getTurno() {
        return turno;
    }

    public void limpiar() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = VACIO;
            }
        }
    }

    public void pintar() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.printf("%c ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    public void avanzarTurno() {
        turno++;
        if (turno > JUGADORES) {
            turno = 1;
        }
    }

    public boolean meterFicha(int columna, char valor) {
        if (columna <= 0 || columna > COLUMNAS) {
            return false;
        }

        if (matriz[0][columna - 1] != VACIO) {
            return false;
        }
        for (int i = FILAS - 1; i >= 0; i--) {
            if (matriz[i][columna - 1] == VACIO) {
                matriz[i][columna - 1] = valor;
                return true;
            }
        }
        return false;
    }

    public char getGanador() {
        for (int i = FILAS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNAS; j++) {
                char valor = matriz[i][j];
                if (valor == VACIO) {
                    continue;
                }
                if (hayCuatroEnRaya(valor, i, j)) {
                    return valor;
                }
            }
        }
        return NINGUNO;
    }

    private boolean hayCuatroEnRaya(char valor, int fila, int col) {
        // izq
        if (col - 3 >= 0 &&
        matriz[fila][col] == valor &&
        matriz[fila][col - 1] == valor &&
        matriz[fila][col - 2] == valor &&
        matriz[fila][col - 3] == valor) {
            return true;
        }
//        // drch
//        if (col + 3 <= COLUMNAS - 1 &&
//        matriz[fila][col] == valor &&
//        matriz[fila][col + 1] == valor &&
//        matriz[fila][col + 2] == valor &&
//        matriz[fila][col + 3] == valor) {
//            return true;
//        }
        // arriba
        if (fila - 3 >= 0 &&
        matriz[fila][col] == valor &&
        matriz[fila - 1][col] == valor &&
        matriz[fila - 2][col] == valor &&
        matriz[fila - 3][col] == valor) {
            return true;
        }
//        // abajo
//        if (fila + 3 <= FILAS - 1 &&
//        matriz[fila + 3][col] == valor &&
//        matriz[fila + 2][col] == valor &&
//        matriz[fila + 1][col] == valor &&
//        matriz[fila][col] == valor) {
//            return true;
//        }

          // diagonal hacia arriba izquierda
        if (col - 3 >= 0 && fila - 3 >= 0 &&
        matriz[fila][col] == valor &&
        matriz[fila - 1][col - 1] == valor &&
        matriz[fila - 2][col - 2] == valor &&
        matriz[fila - 3][col - 3] == valor) {
            return true;
        }
          // diagonal hacia arriba derecha
        if (col + 3 <= COLUMNAS - 1 && fila - 3 >= 0 &&
        matriz[fila][col] == valor &&
        matriz[fila - 1][col + 1] == valor &&
        matriz[fila - 2][col + 2] == valor &&
        matriz[fila - 3][col + 3] == valor) {
            return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.pintar();

    }
}
