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


import java.io.IOException;
import java.util.*;

/**
 * Facade for the acquire game backend
 * @author Baylor McElroy
 */
public class Game {
    private Stack<Tile> playedTiles;
    private static Game instance = null;
    private Queue<Player> players;
    private Player currentPlayer;
    private GameBoard gameBoard;
    private GameOptions gameOptions;
    private MainUI mainUI;
    private List<Corporation> corporationList;

    private Game() {

        playedTiles = new Stack<>();
    }

    /**
     * Sets up game with specified game options
     *
     * @param gameOptions intitial game options determined at start of game
     * @param mainUI      UI of game
     * @author Baylor McElroy
     */
    public void setUpGame(GameOptions gameOptions, MainUI mainUI) throws Exception {
        this.gameOptions = gameOptions;
        gameOptions.start(new Stage());
        this.mainUI = mainUI;
    }

    /**
     * Starts game and distributes necessary resources
     *
     * @author Baylor McElroy
     * @author Victoria Weir
     */
    public void startGame() {
//        System.out.println("Game.java startGame() top");

        gameBoard = new GameBoard();

//        System.out.println("Game.java startGame() pre for loop");

        int numPlayers = gameOptions.getNumPlayers();
        players = new ArrayDeque<>(numPlayers);
        for (int i = 1; i <= numPlayers; i++) {
            String playerName = "Player" + i;
            Player player = new Player(playerName);
            System.out.println("PlayerName = " + playerName);
            players.add(player);
            for (int j = 0; j < 6; j++) {
//                System.out.println("j (of 6 tiles)" + j);
                player.addTile(gameBoard.getTiles().dealTile()); //error here
//                System.out.println("Player get start tile? " + player.getTile(j));
            }

//            System.out.println(player);
        }
        System.out.println("Game.java startGame() post for loop");


        currentPlayer = players.poll();
//        System.out.println("current player" + currentPlayer);
//        System.out.println(currentPlayer.toString());
        try {
            gameOptions.hide();
            mainUI.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Game.java startGame() bottom");

    }

    /**
     * returns singleton instance of game
     *
     * @return instance of Game
     * @author Baylor McElroy
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * saves current game state
     *
     * @author Baylor McElroy
     */
    public void saveGame() throws IOException {
        gameOptions.saveDefault();
    }

    /**
     * sets the current players of the game. Called once at start of game.
     *
     * @param players instantiated players starting the game
     * @author Baylor McElroy
     */
    public void setPlayers(ArrayDeque<Player> players) {
        this.players = players;
    }

    public int getNumPlayers() {
        return players.size();
    }

    /**
     * get player whose turn it currently is
     *
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getGameState() {
        System.out.println("Game.java getGameState top");
        return gameBoard.getBoardState();
    }

    /**
     * moves game to next phase of turn
     *
     * @author Baylor McElroy
     */
    public void nextState() {
        gameBoard.nextState();
    }

    /**
     * makes it the next players turn
     *
     * @author Baylor McElroy
     */
    public void nextTurn() {
        players.add(currentPlayer);
        this.currentPlayer = players.poll();
    }

    public Queue playerQueue() {
        return players;
    }

    public boolean tallyScore(Player player, int amount) {
        return true;
    }

    public boolean takeTurn(Player player) {
        return true;
    }

    public void displayInfo() {
    }

    public void openOptions() {
    }

    public boolean drawTile(Player player) {
        return true;
    }

    /**
     * Player buys stock from corporation
     *
     * @param corporation Corporation being bought from
     * @param player player who is buying it
     * @author Victoria Weir
     */
    public void buyStock(Corporation corporation, Player player) {
        player.buyStock(corporation);
        mainUI.showBought(corporation, player);
    }

    /**
     * Gets player shares
     *
     * @return player shares as a list
     * @author Baylor McElroy
     */
    public int[] getPlayerShares(){
        int[] shares = new int[7];
        int i = 0;
        for (Stack<Stock> stack: currentPlayer.getCorps()) {
            shares[i] = stack.size();
        }
        return shares;
    }

    public void sellStock(Corporation corporation) {
        currentPlayer.sellStocks(corporation);
    }

    /**
     * Plays tile specified by UI then marks it on UI
     *
     * @param tile
     * @return
     * @author Baylor McElroy
     */
    public void playTile(Tile tile) {
        currentPlayer.addTile(gameBoard.getTiles().dealTile());
        gameBoard.checkPlace(tile);

        int tileXpos = tile.getXpos();
        int tileYpos = tile.getYpos();
        Stack<Tile> tileStack = new Stack<>();
        Tile tempTile;
        for (Tile playedTile : playedTiles) {
            int playedTileXpos = playedTile.getXpos();
            int playedTileYpos = playedTile.getYpos();
            //left
            if (playedTileXpos - tileXpos == 1 && playedTileYpos - tileYpos == 0) {
                System.out.println("left");
                tileStack.add(playedTile);
            }
            //right
            else if (tileXpos - playedTileXpos == 1 && playedTileYpos - tileYpos == 0) {
                System.out.println("right");
                tileStack.add(playedTile);
            }
            //above
            else if (playedTileYpos - tileYpos == 1 && playedTileXpos - tileXpos == 0) {
                System.out.println("above");
                tileStack.add(playedTile);
            }
            //below
            else if (tileYpos - playedTileYpos == 1 && playedTileXpos - tileXpos == 0) {
                System.out.println("below");
                tileStack.add(playedTile);
            }

        }
        //add tile to corporation
        if (tileStack.size() == 0) {
            //no corporation
            mainUI.colorTile(tile, 0);
        } else if (tileStack.size() == 1) {
            //add to corporation
            tempTile = tileStack.pop();
            //Check for nearby corporation
            String nearCorporation = gameBoard.checkNearCorps(tempTile);
            List<Corporation> active = getActiveCorporations();
            for (Corporation corps : active) {
                String compare = corps.getName();
                if (Objects.equals(nearCorporation, compare)) {
                    tile.setCorp(tempTile.getCorp());
                    mainUI.colorTile(tile, tempTile.getCorp().getColorNum());
                }
            }
//            if (tempTile.getCorp() != null){
//                tile.setCorp(tempTile.getCorp());
//                mainUI.colorTile(tile, tempTile.getCorp().getColorNum());

//            } else {
            //make corporation
            List<Corporation> corps = getUnplacedCorporations();
            System.out.println(corps.toString() + " corps ");
            mainUI.chooseCorp(corps, 1);
            System.out.println("Available corps" + getUnplacedCorporations());
            //get chosen corp
            Corporation tempCorp = null;
            for (Corporation corporation : corps) {
                if (!getUnplacedCorporations().contains(corporation)) {
                    tempCorp = corporation;
                }
            }
            Corporation c = tempCorp;
            System.out.println("new color number: " + c.getColorNum());
            mainUI.colorTile(tile, c.getColorNum());
            mainUI.colorTile(tempTile, c.getColorNum());
            //make other tile corporation
            tempTile.setCorp(c);
//            }
        } else {
            //merge corporation
            Stack<Corporation> corporations = new Stack<>();
            for (Tile t : tileStack) {
                corporations.add(t.getCorp());
            }
            //sending null corporations
        }
        playedTiles.add(tile);
        //get corporation color


    }
//    public static void main(String[] args) {
//        Tile tileEx = new Tile("Z",3);
////        System.out.println(getInstance().gameBoard.checkNearCorps(tileEx));
//        GameBoard createCorp = new GameBoard();
//        List<Corporation> createList = createCorp.createCorporationList();
//        System.out.println(createList.toString());
//        Corporation testMakeCorp = createList.get(1);
//        tileEx.setCorp(testMakeCorp);
//        System.out.println(tileEx.getCorp().toString());
//        System.out.println("Making it work");
//    }

    public void tradeStocks(Corporation oldCorp, Corporation newCorp) {
        currentPlayer.tradeStocks(oldCorp, newCorp);
    }

    /**
     * Gets first of the tile list
     *
     * @return get tile on top of list
     */
    public Tile getLastTile() {
        return playedTiles.peek();
    }

    /**
     * Gets the unactive corporations
     *
     * @return list of unactive corporations
     */
    public List<Corporation> getUnplacedCorporations() {
        return gameBoard.getUnplacedCorporations();
    }

    /**
     * Gets the active corporations
     *
     * @param corporation list of active corporations
     */
    public void playCorporation(Corporation corporation) {
        corporation.giveStock(currentPlayer);
        corporation.setPlayed(true);
    }

    /**
     * Gives the tile a color based on the corporation
     *
     * @param tile tile being added to a corporation
     * @param colorType color based on the corporation
     */
    public void colorTile(Tile tile, int colorType) {
        mainUI.colorTile(tile, colorType);
    }

    /**
     * Gets the game board class
     *
     * @return game board class
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * For the end of the game- sells the players' stocks and get respective shares from active corporations
     *
     * @author Victoria Weir
     */
    public void distributeMoney() {
        for (Player player : players) {
            for (Corporation corporation : getActiveCorporations()) {
                player.sellStocks(corporation);
            }
        }
        for (Corporation corporation : getActiveCorporations()) {
            GameBoard calculate = getGameBoard();
            calculate.ShareBonus(corporation);
        }
    }

    /**
     * Figures out the winner by comparing them to each other
     *
     * @return winner
     * @author Victoria Weir
     */
    public String getWinner() {
        String m = "";
        int mostMoney = 0;
        Player winner = null;

        for (Player player : players) {
            if (player.checkMoney() > mostMoney) {
                mostMoney = player.checkMoney();
                winner = player;
            }
        }
        m = winner.getName() + " is the winner with $" + winner.checkMoney();
        return m;
    }

    /**
     * Looks at all the player's money
     *
     * @return a string with all the player's money
     * @author Victoria Weir
     */
    public String getOthers() {
        String m = "";
        for (Player player : players) {
            m += player.getName() + " has the total of $" + player.checkMoney();
        }
        return m;
    }

    /**
     * Checks to end the game
     *
     * @return sees if the game can end or not
     * @author Victoria Weir
     */
    public boolean checkEndGame() {
        String m = "";
        for (Corporation corporation : getActiveCorporations()) {
            if (corporation.getPlayTiles().size() > 41 || corporation.getPlayTiles().size() > 11) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Tests if the game can end and if so, prints the results
     *
     * @author Victoria Weir
     */
    public void endGame() {
        if (checkEndGame()) {
            distributeMoney();
            getOthers();
            getWinner();
        }
    }

    /**
     * Lets the player pick corporation during merge if both corporations equal eachother
     *
     * @param corporations the corporation in merge
     */
    public void pickMerge(List<Corporation> corporations) {
        mainUI.chooseCorp(corporations, 2);
    }

    /**
     * If a merge tie happens
     *
     * @param corporations corporation 1
     * @param corporation corporation 2
     * @param tile the amount of tiles
     * @author Baylor McElroy
     */
    public void mergeTie(List<Corporation> corporations, Corporation corporation, Tile tile) {
        for (Corporation item : corporations) {
            for (Tile t : item.getPlayTiles()) {
                t.setCorp(corporation);
            }
            tile.setCorp(corporation);
            Game.getInstance().colorTile(tile, corporation.getColorNum());
        }
    }

    /**
     * Gets the active corporations
     *
     * @return active corporations as a list
     * @author Baylor McElroy
     */
    public List<Corporation> getActiveCorporations() {
        List<Corporation> corps = new ArrayList<>();
        for (Corporation c : gameBoard.getCorporationList()) {
            if (c.getPlayed()) {
                corps.add(c);
            }
        }
        return corps;
    }

    /**
     * Gets corporation list
     *
     * @return corporation list
     */
    public List<Corporation> getCorporationList(){
        return gameBoard.getCorporationList();
    }

}