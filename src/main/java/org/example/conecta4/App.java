package org.example.conecta4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static org.example.conecta4.Config.*;

public class App extends Application {

    private static final int TAMANO_CELDA = 60;

    private StackPane root;
    private GridPane gridPane;
    private Button resetStage, nextStage;

    private char[][] matriz;
    private int turno;
    private char ganador;
    private Tablero tablero;

    @Override
    public void start(Stage primaryStage) {

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        root = new StackPane(gridPane);

        tablero = new Tablero();
        matriz = tablero.getMatriz();
        turno = 1;
        ganador = NINGUNO;

        pintarMatriz();

        int width = COLUMNAS * 75;
        int height = FILAS * 75;
        Scene scene = new Scene(root, width, height);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                turno = 1;
                ganador = NINGUNO;
                tablero.limpiar();
                pintarMatriz();
            }
        });

        primaryStage.setTitle("Conecta 4");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void pintarMatriz() {
        gridPane.getChildren().clear();
        if(matriz!=null){
            int filas = matriz.length;
            int cols = matriz[0].length;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    char valor = matriz[i][j];
                    Rectangle fondo = new Rectangle(TAMANO_CELDA, TAMANO_CELDA);
                    fondo.setFill(Color.BLACK);

                    Label label = new Label(String.valueOf(valor));
                    label.setTextFill(Color.WHITE);
                    label.setFont(Font.font("Arial", FontWeight.BOLD, 25));

                    StackPane celda = new StackPane(fondo, label);

                    final int filaActual = i;
                    final int columnaActual = j;

                    celda.setOnMouseClicked(event ->  {
                        System.out.printf("Fila %d Columna %d Valor %c\n",
                                filaActual, columnaActual, valor);

                        if (ganador == NINGUNO) {
                            char valorTurno = String.valueOf(turno).charAt(0);
                            boolean b = tablero.meterFicha(columnaActual + 1, valorTurno);

                            if (b) {
                                pintarMatriz();

                                turno++;
                                if (turno > JUGADORES) {
                                    turno = 1;
                                }
                                ganador = tablero.getGanador();
                                if (ganador != NINGUNO) {
                                    System.out.println("Hay ganador");
                                }
                            }
                        }


                    });

                    gridPane.add(celda, j, i);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}