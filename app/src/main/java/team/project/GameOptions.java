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

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameOptions {
    private boolean hiddenAsset;
    private String filename;

    public GameOptions(){
    }

    /**
     * This creates, adds, and determines the amount of players in the game
     *
     * @param numPlayers number of players
     */
    public void start(int numPlayers) {
        ArrayList<Player> players = new ArrayList<Player>(numPlayers);
        for (int i = 1; i <= numPlayers; i++) {
            String playerName = "Player" + i;
            Player player = new Player(playerName);
            players.add(player);
        }
    }

    /**
     * Method that will load the game from the json file
     *
     * @param filename String of the filename
     * @return getGame Game object that holds the game
     */
    public Game load(String filename){
        Game getGame = null;
        Gson gson = new Gson();
        File loadFile = new File(filename);

        try{
            Scanner scan = new Scanner(loadFile);
            while(scan.hasNextLine()){
                getGame = gson.fromJson(scan.nextLine(),Game.class);
            }
            scan.close();
        }catch (Exception e){}
        return getGame;
    }

//    public GameOptions readData(String filepath){
//        return new GameOptions();
//    }

    /**
     * Method that will save the current game
     *
     * @param file desired file location made by the player
     * @param go the current state of the game
     */
    public void saveDate(String file, Game go){
        Gson gson = new Gson();
        try{
            File filing = new File(file);
            if(filing.exists()){
                filing.delete();
            }
            FileWriter fileWrite = new FileWriter(file, true);
            fileWrite.write(gson.toJson(go));
            fileWrite.close();
        }catch (Exception e){}
    }

    public boolean Display(){
        return hiddenAsset;
    }
}

