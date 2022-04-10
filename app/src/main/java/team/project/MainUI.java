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


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * The main User interface for the board of the game.
 * @author Baylor McElroy
 */
public class MainUI extends Application {

    private Game game;
    private Group root;
    private Label turnLabel;
    private GridPane buttonGrid;
    private Button[][] butAr;
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
    private CheckBox buyStockCheck;
    private Label[][] labArInfo;
    private Label[][] labArShares;
    final ToggleGroup toggleGroup = new ToggleGroup();
    private final Stage stage = new Stage();

    /**
     * Adds all controls to UI
     * @throws Exception for UI exception
     */
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
        //playTileButton = makePlayTileButton();
        yourSharesLabel = makeYourSharesLabel();
        sharesTable = makeSharesTable();
        moneyAvailableLabel = makeMoneyAvailableLabel();
        moneyLabel = makeMoneyLabel();
        next = makeNextButton();
        buyStockCheck = makeBuyStockCheck();
        game.setUpGame(new GameOptions(), this);
        System.out.println("MainUI.java MainUI() bottom");

    }
    public void colorTile(Tile tile, int colorType){
        int x = tile.getXpos();
        int y = tile.getYpos();

        switch (colorType){
            case 0 -> {
                //black
                butAr[x][y].setStyle("-fx-background-color: #12090d; ");
            }
            case 1 -> {
                //blue
                butAr[x][y].setStyle("-fx-background-color: #3434eb; ");
            }
            case 2 -> {
                //yellow
                butAr[x][y].setStyle("-fx-background-color: #e2eb34; ");
            }
            case 3 -> {
                //red
                butAr[x][y].setStyle("-fx-background-color: #d40f0f; ");
            }
            case 4 -> {
                //purple
                butAr[x][y].setStyle("-fx-background-color: #cf11a9; ");
            }
            case 5 -> {
                //green
                butAr[x][y].setStyle("-fx-background-color: #13cc10; ");
            }
            case 6 -> {
                //orange
                butAr[x][y].setStyle("-fx-background-color: #cc930e; ");
            }
            case 7 -> {
                //pink
                butAr[x][y].setStyle("-fx-background-color: #ff5ca8; ");
            }
        }
        //add different colors


    }

    /**
     * Moves game to next phase
     */
    private void nextPhase(){
        System.out.println("MainUI.java nextPhase() top");
        String gameState = game.getGameState();
        System.out.println(gameState);
        switch (gameState){
            case "PLAY" -> {
                playTile();
                next.setText("Buy Stock");
                game.nextState();
                System.out.println("MainUi.java end switch play:");
                System.out.println(gameState);
            }
            case "EXCHANGE" -> {
                System.out.println("UI State: EXCHANGE");
                for(int i=0;i<3;i++){
                    if(game.getActiveCorporations().size()>0 && buyStockCheck.isSelected()){
                        chooseStock();
                    }
                }
                next.setText("Draw Tile");
                game.nextState();
            }
            case "DRAW" -> {
                game.nextState();
                System.out.println("UI State: DRAW");
                next.setText("Play Tile");
                nextTurn();
            }
        }
    }
    private void playTile(){
        System.out.println("UI State: PLAY");
        Tile tile = null;
        Player tempPlayer = game.getCurrentPlayer();
        if(toggleGroup.getSelectedToggle() == tileRB1){
            tile = tempPlayer.removeTile(0);
            //System.out.println("0 tile played by " + tempPlayer);
        }
        else if(toggleGroup.getSelectedToggle() == tileRB2){
            tile = tempPlayer.removeTile(1);
            //System.out.println("1 tile played by " + tempPlayer);
        }
        else if(toggleGroup.getSelectedToggle() == tileRB3){
            tile = tempPlayer.removeTile(2);
            //System.out.println("2 tile played by " + tempPlayer);
        }
        else if(toggleGroup.getSelectedToggle() == tileRB4){
            tile = tempPlayer.removeTile(3);
            //System.out.println("3 tile played by " + tempPlayer);
        }
        else if(toggleGroup.getSelectedToggle() == tileRB5){
            tile = tempPlayer.removeTile(4);
            //System.out.println("4 tile played by " + tempPlayer);
        }
        else if(toggleGroup.getSelectedToggle() == tileRB6){
            tile = tempPlayer.removeTile(5);
            //System.out.println("5 tile played by " + tempPlayer);
        }

        if(tile!=null){
            System.out.println(tile.toString() + " has been played!");
            game.playTile(tile);
        }
    }
    /**
     * moves turn to next Player
     */
    public void nextTurn(){
        System.out.println("MainUI.java nextTurn() top");
        game.nextTurn();
        Player currentPlayer = game.getCurrentPlayer();
        moneyLabel.setText(String.valueOf(currentPlayer.checkMoney()));
        tileRB1.setText(currentPlayer.getTile(0).toString());
        tileRB2.setText(currentPlayer.getTile(1).toString());
        tileRB3.setText(currentPlayer.getTile(2).toString());
        tileRB4.setText(currentPlayer.getTile(3).toString());
        tileRB5.setText(currentPlayer.getTile(4).toString());
        tileRB6.setText(currentPlayer.getTile(5).toString());
        updateSharesTable(game.getPlayerShares());
        turnLabel.setText(Game.getInstance().getCurrentPlayer().getName()+ "'s Turn");
        System.out.println(Game.getInstance().getCurrentPlayer().getName()+ "'s Turn" + " <- label name");
    }

    public void showBought(Corporation corporation, Player player){
        String lastText;
        int newVal;
        lastText = labArShares[corporation.getColorNum()][1].getText();
        newVal = Integer.valueOf(lastText) + 1;
        labArShares[corporation.getColorNum()][1].setText(String.valueOf(newVal));
    }
    private void updateSharesTable(int[] shares){
        for (int i: shares) {
            labArShares[1][i].setText(String.valueOf(shares[i]));
        }
    }

    /**
     * Shows stage
     * @param stage new stage to show
     * @throws Exception UI exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage = this.stage;
        System.out.println("MainUI.java start() top");
        stage.setTitle("Acquire");

        Scene scene = new Scene(root, 1260, 630);
        scene.setFill(Color.DARKGRAY);

        Label title = new Label();

        root.getChildren().add(buttonGrid);
        //tabs bottom left
        Player currentPlayer = game.getCurrentPlayer();
        tileRB1.setText(currentPlayer.getTile(0).toString());
        tileRB2.setText(currentPlayer.getTile(1).toString());
        tileRB3.setText(currentPlayer.getTile(2).toString());
        tileRB4.setText(currentPlayer.getTile(3).toString());
        tileRB5.setText(currentPlayer.getTile(4).toString());
        tileRB6.setText(currentPlayer.getTile(5).toString());

        root.getChildren().add(turnLabel);
        root.getChildren().add(menuButton);
        root.getChildren().add(infoTable);
        root.getChildren().add(tileRB1);
        root.getChildren().add(tileRB2);
        root.getChildren().add(tileRB3);
        root.getChildren().add(tileRB4);
        root.getChildren().add(tileRB5);
        root.getChildren().add(tileRB6);
        //root.getChildren().add(playTileButton);
        root.getChildren().add(yourTileLabel);
        root.getChildren().add(sharesTable);
        root.getChildren().add(moneyAvailableLabel);
        root.getChildren().add(moneyLabel);
        root.getChildren().add(next);
        root.getChildren().add(buyStockCheck);
        //game action buttons (1-4)

        root.getChildren().add(title);


        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
        System.out.println("MainUI.java start() bottom");

    }
    /**
     * Displays menu.
     */
    private void dispMenu(){
        Stage disp = new Stage();
        disp.setTitle("Courses");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        scene.setFill(Color.LIGHTGRAY);


        //button to call save
        Button save = createButton("save", 50, 50);
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    game.saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // button to quit without saving
        Button quit = createButton("Quit", 50, 80);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        disp.setResizable(false);
        disp.setScene(scene);
        disp.show();

        System.out.println("MainUI.java dispMenu()");

    }

    private void chooseStock(){
        stage.hide();
        Stage disp = new Stage();
        disp.setTitle("Courses");
        moneyLabel.setText(String.valueOf(game.getCurrentPlayer().checkMoney()));
        System.out.println(game.getCurrentPlayer().checkMoney());
        disp.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        disp.setTitle("Choose a stock to buy");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        scene.setFill(Color.LIGHTGRAY);

        List<Corporation> corporations = game.getActiveCorporations();

        ComboBox<String> comboBox = new ComboBox<>();
        System.out.println(corporations);
        for (Corporation item: corporations) {
            comboBox.getItems().add(item.toString());
            System.out.println(item + "item");
        }

        Button button = createButton("Quit", 20, 20);
        button.setCancelButton(true);
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Corporation item: corporations) {
                    if(comboBox.getValue().equals(item.toString())){
                        game.buyStock(item, game.getCurrentPlayer());
                    }
                }
                disp.hide();
            }
        });
        root.getChildren().add(comboBox);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disp.hide();
            }
        });
        disp.setResizable(false);
        disp.setScene(scene);
        disp.showAndWait();
        System.out.println("MainUI.java dispMenu()");
    }
    /**
     * Displays a new window to select starting a new corporation
     */
    public void chooseCorp(List<Corporation> corporations, int type){
        stage.hide();
        Stage disp = new Stage();
        disp.initStyle(StageStyle.UNDECORATED);
        disp.initStyle(StageStyle.UNDECORATED);
        disp.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        disp.setTitle("Choose a corporation");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        scene.setFill(Color.LIGHTGRAY);

        ComboBox<String> comboBox = new ComboBox<>();
        System.out.println(corporations);
        for (Corporation item: corporations) {
            comboBox.getItems().add(item.toString());
            System.out.println(item + "item");
        }

        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Corporation item: corporations) {
                    if(comboBox.getValue().equals(item.toString())){
                        if (type == 1){
                            System.out.println("Type = 1");
                            game.playCorporation(item);
                            game.getLastTile().setCorp(item);
                        }
                        else{
                            game.mergeTie(corporations, item, game.getLastTile());
                        }
                    }
                }
                disp.hide();
            }
        });
        root.getChildren().add(comboBox);

        disp.setResizable(false);
        disp.setScene(scene);

        if(corporations.size()!=0){
            disp.showAndWait();
        }

    }
    /**
     * Creates a button on location with specified text.
     * @param text text displayed by button
     * @param x absolut x location of button
     * @param y absolute y location of button
     * @return created button
     */
    private Button createButton(String text, int x, int y){
        Button button = new Button();
        button.setText(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
    /**
     * Creates radio button at location with specified text.
     * @param text to be displayed on radio button
     * @param x absolute x location
     * @param y absoulte y location
     * @return created radio button
     */
    private RadioButton createRadioButton(String text, int x, int y){
        RadioButton Rbutton = new RadioButton();
        Rbutton.setText(text);
        Rbutton.setLayoutX(x);
        Rbutton.setLayoutY(y);
        return Rbutton;
    }
    /**
     * creates label with specified text location and text size
     * @param text text displayed but label
     * @param x absolute x location
     * @param y absolute y location
     * @param fontSize text size of label
     * @return created label
     */
    private Label createLabel(String text, int x, int y, int fontSize){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(new Font("Arial", 20));
        return label;
    }
    /**
     * Creates button gridpane representing tiles
     * @param x absolute x location of top corner of grid
     * @param y abosulte y location of top corner of grid
     * @return grid pane of buttons
     */
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
    /**
     * creates label grid pane to mimic tables
     * @param labAr array of labels in gridpane to be added
     * @param x absolute x location of top corner of grid
     * @param y abosulte y location of top corner of grid
     * @return grid pane of labels
     */
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
    /**
     * makes button gridpane
     * @return gridpane
     */
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
    /**
     * makes label depicting turn
     * @return constructed label
     */
    private Label makeTurnLabel(){
        int x = 800;
        int y = 60;
        int fontSize = 30;
        String text = "Player 1's turn";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    /**
     * makes menu button
     * @return constructed button
     */
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
    /**
     * make label that says "Your tiles"
     * @return constructed label
     */
    private Label makeYourTileLabel(){
        int x = 800;
        int y = 400;
        int fontSize = 30;
        String text = "Your tiles";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    /**
     * makes radio button representing selected tile to be played
     * @param toggleGroup toggle group of radio button
     * @param yDist distance between radio buttons
     * @param numRB number of radio button
     * @return constructed radio button
     */
    private RadioButton makeTileRadioButtons(ToggleGroup toggleGroup, int yDist, int numRB){
        int x = 815;
        int y = 445 + yDist * numRB;
        String text = "TILE";
        RadioButton radioButton = createRadioButton(text, x, y);
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }
    /**
     * make play tile button that plays tile on selected radio button
     * @return constructed button
     */
//    private Button makePlayTileButton(){
//        int x = 784;
//        int y = 600;
//        String text = "Play selected tile";
//        Button button = createButton(text, x, y);
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//            }
//        });
//        return button;
//    }
    /**
     * makes label that says "your shares"
     * @return constructed label
     */
    private Label makeYourSharesLabel(){
        int x = 960;
        int y = 400;
        int fontSize = 30;
        String text = "Your shares";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    /**
     * make label that says "money available"
     * @return constructed label
     */
    private Label makeMoneyAvailableLabel(){
        int x = 1115;
        int y = 400;
        int fontSize = 30;
        String text = "Your money";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    /**
     * makes label depicting money a player has
     * @return constructed label
     */
    private Label makeMoneyLabel(){
        int x = 1115;
        int y = 450;
        int fontSize = 30;
        String text = "$6000";
        Label label = createLabel(text, x, y, fontSize);
        return label;
    }
    /**
     *  makes gridpane for info table on top right
     * @return constructed gridpane
     */
    private GridPane makeInfoTable(){
        int x = 700;
        int y = 100;
        int gridLength = 5;
        int gridHeight = 7;

        String[] corporationNames = {"Corporation 1", "Corporation 2", "Corporation 3", "Corporation 4", "Corporation 5", "Corporation 6", "Corporation 7"};
        String[] color = {"Blue", "Yellow", "Red", "Purple", "Green", "Orange", "Pink"};
        String[] size = {"0", "0", "0", "0", "0", "0", "0"};
        String[] price = {"100", "100", "100", "100", "100", "100", "100"};
        String[] status = {"inactive" , "inactive" , "inactive" , "inactive" , "inactive" , "inactive" , "inactive"};
        String[][] tableData = {corporationNames, color, size, price, status};
        labArInfo = new Label[gridLength][gridHeight];
        for(int i=0; i<gridLength;i++){
            for(int j=0;j<gridHeight;j++){
                final Label label = new Label();
                label.setText(tableData[i][j]);
                label.setMinSize(100, 30);
                label.setStyle("-fx-background-color: #ffffff; ");
                label.setStyle("-fx-border-color: #000000; ");
                label.setPrefSize(45,30);
                labArInfo[i][j] = label;
            }
        }

        GridPane gridPane = createLabGridpane(labArInfo, x, y);
        return gridPane;
    }
    /**
     * makes table showing shares in a players inventory
     * @return constructed gridpane
     */
    private GridPane makeSharesTable(){
        int x = 920;
        int y = 430;
        int gridLength = 2;
        int gridHeight = 8;

        String[] corporationNames = {"Corporation 1", "Corporation 2", "Corporation 3", "Corporation 4", "Corporation 5", "Corporation 6", "Corporation 7", "Corporation 8"};
        String[] amount = {"0", "0", "0", "0", "0", "0", "0", "0"};
        String[][] tableData = {corporationNames, amount};
        labArShares = new Label[gridLength][gridHeight];
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
                labArShares[i][j] = label;
            }
        }
        GridPane gridPane = createLabGridpane(labArShares, x, y);
        return gridPane;
    }
    /**
     * makes button to move to next phase of turn
     * @return constructed button
     */
    private Button makeNextButton(){
        int x = 1125;
        int y = 540;
        String text = "Next";
        Button button = createButton(text, x, y);
        button.setPrefSize(100, 50);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //change text when pressed to depict state
                nextPhase();
            }
        });
        return button;
    }
    private CheckBox makeBuyStockCheck(){
        int x = 1120;
        int y = 500;
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);
        checkBox.setText("Buy Stock?");
        return checkBox;
    }
}