/*
 * MIT License
 *
 * Copyright (c) 2022 Git-an-A
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package team.project;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Game Options class is dealing with Save, Load, Starting, and UI
 *
 * @author Baylor McElroy
 * @author Victoria Weir
 */
public class GameOptions extends Application {
    private boolean hiddenAsset;
    private String filename;
    private int numPlayers;
    private boolean hide;
    private Stage disp;

    /**
     * Empty
     */
    public GameOptions(){}

    /**
     * This creates, adds, and determines the game options
     * @author Baylor McElroy
     */
    @Override
    public void start(Stage stage) {
        System.out.println("GameOptions.java start() top");
        disp = new Stage();
        disp.setTitle("Courses");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 250);
        scene.setFill(Color.LIGHTGRAY);

        Label labelTop = new Label();
        labelTop.setLayoutX(175);
        labelTop.setLayoutY(25);
        labelTop.setText("Enter number of Players");

        Label labelBot = new Label();
        labelBot.setLayoutX(175);
        labelBot.setLayoutY(50);
        labelBot.setText("Enter <yes> for hide assests mode");

        Button button = new Button();
        button.setLayoutX(25);
        button.setLayoutY(100);
        button.setPrefSize(100, 25);
        button.setText("Enter");
        button.setDefaultButton(true);

        TextField playerTF = new TextField();
        playerTF.setLayoutX(25);
        playerTF.setLayoutY(25);

        TextField hideShow = new TextField();
        hideShow.setLayoutX(25);
        hideShow.setLayoutY(50);
        //System.out.println("GameOptions.java pre enter handler");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numPlayers = Integer.valueOf(playerTF.getText());
                String tempText = hideShow.getText();
                if(tempText=="yes"){
                    hide = true;
                }
               // System.out.println("GameOptions.java start() post enter handler");

                System.out.println("GameOptions.java start() pre startGame");
                Game.getInstance().startGame(); //error is in here
                System.out.println("GameOptions.java start() post startGame");


            }
        });

        root.getChildren().add(labelTop);
        root.getChildren().add(labelBot);
        root.getChildren().add(button);
        root.getChildren().add(hideShow);
        root.getChildren().add(playerTF);


        disp.setResizable(false);
        disp.setScene(scene);
        disp.show();
    }

    /**
     * Method that will save the current game
     *
     * @param file desired file location made by the player
     * @author Victoria Weir
     */
    private void saveData(String file) throws IOException {
        System.out.println("savvvveeeee");
        Gson gson = new Gson();
        try{
            File filing = new File(file);
            if(filing.exists()){
                filing.delete();
            }
            FileWriter fileWrite = new FileWriter(file, true);
            String json = gson.toJson(Game.getInstance().getGameBoard());
            System.out.println(json + " : is json");
            fileWrite.write(json);
            fileWrite.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Default save
     *
     * @throws IOException
     */
    public void saveDefault() throws IOException {
        String file = "save.txt";
        saveData(file);
    }

    public boolean Display(){
        return hiddenAsset;
    }
    public int getNumPlayers(){
        return numPlayers;
    }
    public void hide(){
        disp.hide();
    }
    public boolean getHide(){
        return hide;
    }
}

