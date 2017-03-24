/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week8;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Joshua Tennies
 */
public class Life extends Application {

    int[][] cells = new int[100][100];
    Pane pane = new Pane();
    BorderPane borderPane = new BorderPane();

    @Override
    public void start(Stage primaryStage) {
        initCells();
        borderPane.setCenter(pane);
        
        Button btn = new Button("Next Generation");
        btn.setOnAction(e -> {
            System.out.println("btn pressed");
            nextGen();
            drawCells();
        });
        borderPane.setBottom(btn);
        
        Scene scene = new Scene(borderPane, 300, 250);

        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        drawCells();
    }
    
    public void drawCells() {
        pane.getChildren().clear();
        for(int y = 0; y < 100; y++) {
            for(int x = 0; x < 100; x++) {
                Rectangle rect = new Rectangle(x*10, y*10, 9, 9);
                rect.setFill(cells[x][y] == 0 ? Color.WHITE : Color.BLACK);
                pane.getChildren().add(rect);
            }
        }
    }
    
    public void nextGen() {
        int[][] cellsng = new int[100][100];
        for(int y = 0; y < 100; y++) {
            for(int x = 0; x < 100; x++) {
                int neighbors = 0;
                if(x>1 && x<99 && y>1 && y<99) {
                    if(cells[x-1][y-1] == 1) {
                        neighbors++;
                    }
                    if(cells[x][y-1] == 1) {
                        neighbors++;
                    }
                    if(cells[x+1][y-1] == 1) {
                        neighbors++;
                    }
                    
                    if(cells[x-1][y] == 1) {
                        neighbors++;
                    }
//                    if(cells[x][y] == 1) {
//                        neighbors++;
//                    }
                    if(cells[x+1][y+1] == 1) {
                        neighbors++;
                    }
                    
                    if(cells[x-1][y+1] == 1) {
                        neighbors++;
                    }
                    if(cells[x][y+1] == 1) {
                        neighbors++;
                    }
                    if(cells[x+1][y+1] == 1) {
                        neighbors++;
                    }
                    if(cells[x][y] == 1) {
                        if(neighbors < 2 || neighbors > 3) {
                            cellsng[x][y] = 0;
                        } else {
                            cellsng[x][y] = 1;
                        }
                    } else if(neighbors == 3) {
                        cellsng[x][y] = 1;
                    } else {
                        cellsng[x][y] = 0;
                    }
                }
            }
        }
        cells = cellsng;
    }
    
    public void initCells() {
        for(int y = 0; y < 100; y++) {
            for(int x = 0; x < 100; x++) {
                cells[x][y] = Math.random() > 0.5 ? 0 : 1;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
