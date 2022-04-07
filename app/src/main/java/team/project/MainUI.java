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


import com.google.common.collect.Table;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.management.ClassLoadingMXBean;
import java.util.Arrays;
import java.util.List;

public class MainUI extends Application {

    private Game game;
    private Group root;
    private Label turnLabel;
    private GridPane buttonGrid;
    Button[][] butAr;
    private Button menuButton;
    private GridPane infoTable;
    private Label yourTileLabel;
    private RadioButton tileRB1;
    private RadioButton tileRB2;
    private RadioButton tileRB3;
    private RadioButton tileRB4;
    private RadioButton tileRB5;
    private RadioButton tileRB6;
    private Button playTileButton;
    private Label yourSharesLabel;
    private GridPane sharesTable;
    private Label moneyAvailableLabel;
    private Label moneyLabel;
    private Button next;
    final ToggleGroup toggleGroup = new ToggleGroup();

    public MainUI() throws Exception {
        System.out.println("MainUI.java MainUI() top");
        game = Game.getInstance();

        root = new Group();

        int gridLength = 12;
        int gridHeight = 9;

        butAr = new Button[gridLength][gridHeight];

        buttonGrid  = makeGridpane();
        turnLabel = makeTurnLabel();
        menuButton = makeMenuButton();
        infoTable = makeInfoTable();
        yourTileLabel = makeYourTileLabel();
        int yDistRB = 25;

        tileRB1 = makeTileRadioButtons(toggleGroup, yDistRB, 0);
        tileRB2 = makeTileRadioButtons(toggleGroup, yDistRB, 1);
        tileRB3 = makeTileRadioButtons(toggleGroup, yDistRB, 2);
        tileRB4 = makeTileRadioButtons(toggleGroup, yDistRB, 3);
        tileRB5 = makeTileRadioButtons(toggleGroup, yDistRB, 4);
        tileRB6 = makeTileRadioButtons(toggleGroup, yDistRB, 5);
        playTileButton = makePlayTileButton();
        yourSharesLabel = makeYourSharesLabel();
        sharesTable = makeSharesTable();
        moneyAvailableLabel = makeMoneyAvailableLabel();
        moneyLabel = makeMoneyLabel();
        next = makeNextButton();
        game.setUpGame(new GameOptions(), this);
        System.out.println("MainUI.java MainUI() bottom");

    }
    public void playTile(Tile tile){
        int x = tile.getXpos();
        int y = tile.getYpos();
        butAr[x][y].setStyle("-fx-background-color: #ffffff; ");
        //add different colors
    }

    private void nextPhase(){
        System.out.println("MainUI.java nextPhase() top");

        switch (game.getGameState()){
            case "PLAY" : {

            }
            case "EXCHANGE" : {
                game.nextState();
            }
            case "DRAW" : {
                game.nextState();
                nextTurn();}
        }
    }

    public void nextTurn(){
        System.out.println("MainUI.java nextTurn() top");

        Player currentPlayer = game.getCurrentPlayer();
        tileRB1.setText(currentPlayer.getTile(0).toString());
        tileRB2.setText(currentPlayer.getTile(1).toString());
        tileRB3.setText(currentPlayer.getTile(2).toString());
        tileRB4.setText(currentPlayer.getTile(3).toString());
        tileRB5.setText(currentPlayer.getTile(4).toString());
        tileRB6.setText(currentPlayer.getTile(5).toString());
    }



    @Override
    public void start(Stage stage) throws Exception {

        System.out.println("MainUI.java start() top");
        stage.setTitle("Acquire");

        Scene scene = new Scene(root, 1260, 630);
        scene.setFill(Color.DARKGRAY);

        Label title = new Label();

        root.getChildren().add(buttonGrid);
        //tabs bottom left

        root.getChildren().add(turnLabel);
        root.getChildren().add(menuButton);
        root.getChildren().add(infoTable);
        root.getChildren().add(tileRB1);
        root.getChildren().add(tileRB2);
        root.getChildren().add(tileRB3);
        root.getChildren().add(tileRB4);
        root.getChildren().add(tileRB5);
        root.getChildren().add(tileRB6);
        root.getChildren().add(playTileButton);
        root.getChildren().add(yourTileLabel);
        root.getChildren().add(sharesTable);
        root.getChildren().add(moneyAvailableLabel);
        root.getChildren().add(moneyLabel);
        root.getChildren().add(next);
        //game action buttons (1-4)

        root.getChildren().add(title);


        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
        System.out.println("MainUI.java start() bottom");

    }
    private void dispMenu(){
        Stage disp = new Stage();
        disp.setTitle("Courses");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        scene.setFill(Color.LIGHTGRAY);

        disp.setResizable(false);
        disp.setScene(scene);
        disp.show();

        System.out.println("MainUI.java dispMenu()");

    }
    private Button createButton(String text, int x, int y){
        Button button = new Button();
        button.setText(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
    private RadioButton createRadioButton(String text, int x, int y){
        RadioButton Rbutton = new RadioButton();
        Rbutton.setText(text);
        Rbutton.setLayoutX(x);
        Rbutton.setLayoutY(y);
        return Rbutton;
    }
    private Label createLabel(String text, int x, int y, int fontSize){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("Arial", 20));
        return label;
    }
    private GridPane createGridpane(int x, int y){
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(x);
        gridPane.setLayoutY(y);
        gridPane.setVgap(3);
        gridPane.setHgap(3);
        for(int i=0; i< butAr.length;i++){
            for(int j=0;j<butAr[i].length;j++){
                gridPane.add(butAr[i][j],i,j);
            }
        }
        return gridPane;
    }
    private GridPane createLabGridpane(Label[][] labAr, int x, int y){
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(x);
        gridPane.setLayoutY(y);
        gridPane.setVgap(3);
        gridPane.setHgap(3);
        for(int i=0; i< labAr.length;i++){
            for(int j=0;j<labAr[i].length;j++){
                gridPane.add(labAr[i][j],i,j);
            }
        }
        return gridPane;
    }
    private GridPane makeGridpane(){
        int x = 30;
        int y = 30;

        for(int i=0; i<12;i++){
            for(int j=0;j<9;j++){
                char c = (char)(j+65);
                final Button button = new Button();
                button.setText(String.valueOf(i)+c);
                button.setDisable(true);
                button.setStyle("-fx-background-color: #ffffff; ");
                button.setStyle("-fx-border-color: #000000; ");
                button.setPrefSize(45,30);
                butAr[i][j] = button;
            }
        }
        GridPane gridPane = createGridpane(x, y);
        return gridPane;
    }
    private Label makeTurnLabel(){
        int x = 800;
        int y = 60;
        int fontSize = 30;
        String text = "Player 1's turn";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    private Button makeMenuButton(){
        int x = 1160;
        int y = 60;
        String text = "Menu";
        Button button = createButton(text, x, y);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dispMenu();
            }
        });
        return button;
    }
    private Label makeYourTileLabel(){
        int x = 800;
        int y = 400;
        int fontSize = 30;
        String text = "Your tiles";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    private RadioButton makeTileRadioButtons(ToggleGroup toggleGroup, int yDist, int numRB){
        int x = 815;
        int y = 445 + yDist * numRB;
        String text = "TILE";
        RadioButton radioButton = createRadioButton(text, x, y);
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }
    private Button makePlayTileButton(){
        int x = 784;
        int y = 600;
        String text = "Play selected tile";
        Button button = createButton(text, x, y);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("MainUI.java handle() top");
                Tile tile = null;
                Player tempPlayer = game.getCurrentPlayer();
                if(toggleGroup.getSelectedToggle() == tileRB1){
                    tile = tempPlayer.removeTile(0);
                }
                else if(toggleGroup.getSelectedToggle() == tileRB2){
                    tile = tempPlayer.removeTile(1);
                }
                else if(toggleGroup.getSelectedToggle() == tileRB2){
                    tile = tempPlayer.removeTile(2);
                }
                else if(toggleGroup.getSelectedToggle() == tileRB2){
                    tile = tempPlayer.removeTile(3);
                }
                else if(toggleGroup.getSelectedToggle() == tileRB2){
                    tile = tempPlayer.removeTile(4);
                }
                else if(toggleGroup.getSelectedToggle() == tileRB2){
                    tile = tempPlayer.removeTile(5);
                }
                else if(toggleGroup.getSelectedToggle() == tileRB2){
                    tile = tempPlayer.removeTile(6);
                }

                if(tile!=null){
                    game.playTile(tile);
                }
                game.nextState();
            }
        });
        return button;
    }
    private Label makeYourSharesLabel(){
        int x = 960;
        int y = 400;
        int fontSize = 30;
        String text = "Your shares";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    private Label makeMoneyAvailableLabel(){
        int x = 1115;
        int y = 400;
        int fontSize = 30;
        String text = "Your money";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    private Label makeMoneyLabel(){
        int x = 1115;
        int y = 450;
        int fontSize = 30;
        String text = "$6000";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    private GridPane makeInfoTable(){
        int x = 700;
        int y = 100;
        int gridLength = 5;
        int gridHeight = 8;

        String[] corporationNames = {"Corporation 1", "Corporation 2", "Corporation 3", "Corporation 4", "Corporation 5", "Corporation 6", "Corporation 7", "Corporation 8"};
        String[] color = {"Blue", "Red", "Green", "Orange", "Brown", "Purple", "Black", "Yellow"};
        String[] size = {"0", "0", "0", "0", "0", "0", "0", "0"};
        String[] price = {"100", "100", "100", "100", "100", "100", "100", "100"};
        String[] status = {"inactive" , "inactive" , "inactive" , "inactive" , "inactive" , "inactive" , "inactive" , "inactive"};
        String[][] tableData = {corporationNames, color, size, price, status};
        Label[][] labAr = new Label[gridLength][gridHeight];
        for(int i=0; i<gridLength;i++){
            for(int j=0;j<gridHeight;j++){
                final Label label = new Label();
                label.setText(tableData[i][j]);
                label.setMinSize(100, 30);
                label.setStyle("-fx-background-color: #ffffff; ");
                label.setStyle("-fx-border-color: #000000; ");
                label.setPrefSize(45,30);
                labAr[i][j] = label;
            }
        }

        GridPane gridPane = createLabGridpane(labAr, x, y);
        return gridPane;
    }

    private GridPane makeSharesTable(){
        int x = 920;
        int y = 430;
        int gridLength = 2;
        int gridHeight = 8;

        String[] corporationNames = {"Corporation 1", "Corporation 2", "Corporation 3", "Corporation 4", "Corporation 5", "Corporation 6", "Corporation 7", "Corporation 8"};
        String[] amount = {"100", "100", "100", "100", "100", "100", "100", "100"};
        String[][] tableData = {corporationNames, amount};
        Label[][] labAr = new Label[gridLength][gridHeight];
        for(int i=0; i<gridLength;i++){
            for(int j=0;j<gridHeight;j++){
                final Label label = new Label();
                label.setText(tableData[i][j]);
                if(i==0){
                    label.setMinSize(100, 10);
                    label.setMaxHeight(20);
                }
                else{
                    label.setMinSize(60, 10);
                    label.setMaxHeight(20);
                }
                label.setStyle("-fx-background-color: #ffffff; ");
                label.setStyle("-fx-border-color: #000000; ");
                labAr[i][j] = label;
            }
        }
        GridPane gridPane = createLabGridpane(labAr, x, y);
        return gridPane;
    }

    private Button makeNextButton(){
        int x = 1125;
        int y = 540;
        String text = "Next";
        Button button = createButton(text, x, y);
        button.setPrefSize(100, 50);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextPhase();
            }
        });
        return button;
    }

}