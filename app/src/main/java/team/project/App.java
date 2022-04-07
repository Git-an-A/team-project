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

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package team.project;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Acquire");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        scene.setFill(Color.LIGHTGRAY);

        RadioButton newGame = new RadioButton();
        RadioButton loadGame = new RadioButton();
        Button start = new Button();
        Button quit = new Button();
        Label title = new Label();

        final int buttonSpacing = 40;

        final int newGameXloc = 300;
        final int newGameYloc = 200;
        final int loadGameXloc = newGameXloc;
        final int loadGameYloc = newGameYloc + buttonSpacing;
        final int startXloc = newGameXloc + 25;
        final int startYloc = newGameYloc + 75;
        final int quitXloc = startXloc;
        final int quitYloc = startYloc + buttonSpacing;
        final int titleXloc = 75;
        final int titleYloc = 85;


        newGame.setLayoutX(newGameXloc);
        newGame.setLayoutY(newGameYloc);
        newGame.setText("New Game");

        loadGame.setLayoutX(loadGameXloc);
        loadGame.setLayoutY(loadGameYloc);
        loadGame.setText("Load Game");

        start.setLayoutX(startXloc);
        start.setLayoutY(startYloc);
        start.setText("Start!");


        quit.setLayoutX(quitXloc);
        quit.setLayoutY(quitYloc);
        quit.setText("Quit");

        title.setLayoutX(titleXloc);
        title.setLayoutY(titleYloc);
        title.setText("Welcome to Acquire!");
        title.setFont(new Font("Arial", 32));

        start.setOnAction(new EventHandler<ActionEvent>() {
            /**
             *
             * @param event
             */
             @Override
             public void handle(ActionEvent event) {
//                 if(loadGame.isArmed()){
//                     //load game from file
//                     game = new Game();
//                 }
//                 else if(newGame.isArmed()){
//                     game = new Game();
//                 }

                 Game finalGame = Game.getInstance();
                 stage.setOnHidden(new EventHandler<WindowEvent>() {
                     @Override
                     public void handle(WindowEvent event) {
                         try {
                             MainUI mainUI = new MainUI();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 });
                 stage.hide();

             }
        });

        root.getChildren().add(newGame);
        root.getChildren().add(loadGame);
        root.getChildren().add(start);
        root.getChildren().add(quit);
        root.getChildren().add(title);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
