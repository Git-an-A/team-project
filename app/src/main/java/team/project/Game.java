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

import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Facade for the acquire game backend
 * @author Baylor McElroy
 */
public class Game {
    private static Game instance = null;
    private Queue<Player> players;
    private Player currentPlayer;
    private GameBoard gameBoard;
    private final int startMoney = 6000;
    private GameOptions gameOptions;
    private MainUI mainUI;
    private Game(){

    }
    /**
     * Sets up game with specified game options
     *
     * @param gameOptions intitail game options determined at start of game
     * @param mainUI UI of game
     * @author Baylor McElroy
     */
    public void setUpGame(GameOptions gameOptions, MainUI mainUI) throws Exception {
        this.gameOptions = gameOptions;
        gameOptions.start(new Stage());
        this.mainUI = mainUI;
    }

    /**
     * Starts game and distributes necessary resources
     * @author Baylor McElroy
     * @author Tori Weir
     */
    public void startGame(){
        System.out.println("Game.java startGame() top");

        gameBoard = new GameBoard();

        System.out.println("Game.java startGame() pre for loop");

        int numPlayers = gameOptions.getNumPlayers();
        players = new ArrayDeque<>(numPlayers);
        for (int i = 1; i <= numPlayers; i++) {
            String playerName = "Player" + i;
            Player player = new Player(playerName);
            System.out.println("PlayerName = " + playerName);
            players.add(player);
            for(int j=0; j<6; j++){
                System.out.println("j (of 6 tiles)" + j);
                player.addTile(gameBoard.getTiles().dealTile()); //error here
                System.out.println();
                System.out.println("Player get start tile? " + player.getTile(j));
            }
            System.out.println("All player tiles: ");
            for(int k=0;k<6;k++){
                System.out.println("k " + k + " tile " + player.getTile(k));
            }
            System.out.println(player);
        }
        System.out.println("Game.java startGame() post for loop");


        currentPlayer = players.poll();
        System.out.println("current player" + currentPlayer);
        System.out.println(currentPlayer.toString());
        try {
            System.out.println("test");
            gameOptions.hide();
            mainUI.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Game.java startGame() bottom");

    }

    /**
     * returns singleton instance of game
     * @return instance of Game
     * @author Baylor McElroy
     */
    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    /**
     * Corporation makings
     * @return the list of the 7 corporation
     * Needs more work
     */
    private List<Corporation> corporationList(){
        List<Corporation> hotelChains = new ArrayList<>();
        hotelChains.add(new Corporation("Worldwide", 1));
        hotelChains.add(new Corporation("Sackson", 2));
        hotelChains.add(new Corporation("Festival", 3));
        hotelChains.add(new Corporation("Imperial", 4));
        hotelChains.add(new Corporation("American", 5));
        hotelChains.add(new Corporation("Continental", 6));
        hotelChains.add(new Corporation("Tower", 7));

        return hotelChains;
    }

    /**
     * saves current game state
     * @author Baylor McElroy
     */
    public void saveGame(){
        gameOptions.saveDefault();
    }

    /**
     * sets the current players of the game. Called once at start of game.
     * @param players instantiated players starting the game
     * @author Baylor McElroy
     */
    public void setPlayers(ArrayDeque<Player> players){
        this.players = players;
    }
    public int getNumPlayers(){
        return players.size();
    }

    /**
     *  get player whose turn it currently is
     * @return current player
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public String getGameState(){
        return gameBoard.getBoardState();
    }

    /**
     * moves game to next phase of turn
     * @author Baylor McElroy
     */
    public void nextState(){
        gameBoard.nextState();
    }

    /**
     * makes it the next players turn
     * @author Baylor McElroy
     */
    public void nextTurn() {
        players.add(currentPlayer);
        currentPlayer  = players.poll();
    }

    public boolean tallyScore(Player player, int amount) {
        return true;}

    public boolean takeTurn(Player player) {
        return true;}

    public void displayInfo() {
    }

    public void openOptions() {
    }

    public boolean drawTile(Player player) {
        return true;
    }

    public boolean buyStock(Stock stockName, int amount) {
        return true;}

    public boolean sellStock(Stock stockName, int amount) {
        return true;}

    public Stock viewStocks(Stock stock) {
        return new Stock();}

    /**
     * Plays tile specified by UI then marks it on UI
     * @param tile
     * @return
     * @author Baylor McElroy
     */
    public void  playTile(Tile tile) {
        currentPlayer.addTile(gameBoard.getTiles().dealTile());
        mainUI.playTile(tile);
        //add tile to corporation
    }

    public boolean tradeStocks(Stock stockName, int amount) {
        return true;}

    public boolean sellStocks(Stock stockName, int amount) {
        return true;}

    public void endGame() {}
}