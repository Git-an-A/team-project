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

    public MainUI(Game game) throws Exception {
        this.game = game;
        root = new Group();
        start(new Stage());
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Aquire");

        Scene scene = new Scene(root, 1260, 630);
        scene.setFill(Color.DARKGRAY);

        final ToggleGroup toggleGroup = new ToggleGroup();

        Label title = new Label();



        //grid pane tile
        root.getChildren().add(makeGridpane());
        //tabs bottom left

        //turn label
        root.getChildren().add(makeTurnLabel());
        //menu button
        root.getChildren().add(makeMenuButton());
        //info table / label
        root.getChildren().add(makeInfoTable());
        //Your tiles label
        root.getChildren().add(makeYourTileLabel());
        //tile radio buttons (6)
        int yDistRB = 25;
        for(int i=0;i<6;i++){
            root.getChildren().add(makeTileRadioButtons(toggleGroup, yDistRB, i));
        }
        //play tile button
        root.getChildren().add(makePlayTileButton());
        //your shares label
        root.getChildren().add(makeYourSharesLabel());
        //shares tabel (6 row, 2 collumn)
        root.getChildren().add(makeSharesTable());
        //money available label
        root.getChildren().add(makeMoneyAvailableLabel());
        //money avalable label
        root.getChildren().add(makeMoneyLabel());
        //game action buttons (1-4)

        root.getChildren().add(title);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
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
    private GridPane createGridpane(Button[][] butAr, int x, int y){
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
        int gridLength = 12;
        int gridHeight = 9;

        Button[][] butAr = new Button[gridLength][gridHeight];
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
        GridPane gridPane = createGridpane(butAr, x, y);
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
                //replace with radio button selection
                game.playTile(new Tile());
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
}