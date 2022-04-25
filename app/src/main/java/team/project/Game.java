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
 * Facade for the acquire game backend. Calls methods from other classes and controls what the player gets to see and does not.
 *
 * @author Baylor McElroy
 */
public class Game {
    private static Game instance = null;
    private Queue<Player> players;
    private Player currentPlayer;
    private GameBoard gameBoard;
    private GameOptions gameOptions;
    private MainUI mainUI;
    private boolean hide;

    private Game() {}

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
        hide = gameOptions.getHide();
    }

    /**
     * Starts game and distributes necessary resources to the amount of players
     *
     * @author Baylor McElroy
     * @author Victoria Weir
     */
    public void startGame() {
        gameBoard = new GameBoard();
        int numPlayers = gameOptions.getNumPlayers();
        players = new ArrayDeque<>(numPlayers);

        for (int i = 1; i <= numPlayers; i++) {
            String playerName = "Player" + i;
            Player player = new Player(playerName);
            System.out.println("PlayerName = " + playerName);
            players.add(player);
            for (int j = 0; j < 6; j++) {
                player.addTile(gameBoard.getTiles().dealTile());
            }
        }

        currentPlayer = players.poll();
        try {
            gameOptions.hide();
            mainUI.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays tile specified by UI then marks it on UI. Allows current player to choose which tile they wish to play as well.
     *
     * @param tile tile to be played
     * @author Baylor McElroy
     */
    public void playTile(Tile tile) {
        currentPlayer.addTile(gameBoard.getTiles().dealTile());
        gameBoard.checkPlace(tile);

        int tileXpos = tile.getXpos();
        int tileYpos = tile.getYpos();
        Stack<Tile> tileStack = new Stack<>();
        Tile tempTile;
        String action = "";

        for (Tile playedTile : gameBoard.getPlayedTiles()) {
            int playedTileXpos = playedTile.getXpos();
            int playedTileYpos = playedTile.getYpos();
            //left
            if (playedTileXpos - tileXpos == 1 && playedTileYpos - tileYpos == 0) {
                tileStack.add(playedTile);
            }
            //right
            else if (tileXpos - playedTileXpos == 1 && playedTileYpos - tileYpos == 0) {
                tileStack.add(playedTile);
            }
            //above
            else if (playedTileYpos - tileYpos == 1 && playedTileXpos - tileXpos == 0) {
                tileStack.add(playedTile);
            }
            //below
            else if (tileYpos - playedTileYpos == 1 && playedTileXpos - tileXpos == 0) {
                tileStack.add(playedTile);
            }
        }

        //add tile to corporation
        if (tileStack.size() == 0) {
            //no corporation
            mainUI.colorTile(tile, 0);
        } else if (tileStack.size() == 1){
            //add to corporation
            tempTile = tileStack.pop();
            //Check for nearby corporation
            String nearCorporation = gameBoard.checkNearCorps(tempTile);
            List<Corporation> active = getActiveCorporations();
            for (Corporation corps : active) {
                String compare = corps.getName();
                if (nearCorporation == compare) {
                    tile.setCorp(tempTile.getCorp());
                    mainUI.colorTile(tile, tempTile.getCorp().getColorNum());
                }
            }

            //make corporation
            List<Corporation> corps = getUnplacedCorporations();
            System.out.println(corps.toString() + " corps ");
            mainUI.chooseCorp(corps, 1);
            System.out.println("Available corps" + getUnplacedCorporations());
            //get chosen corp
            Corporation tempCorp = null;
            for (Corporation corporation : corps) {
                if (getActiveCorporations().contains(corporation)) {
                    tempCorp = corporation;

                }
            }
            Corporation c = tempCorp;
            System.out.println("new color number: " + c.getColorNum());
            mainUI.colorTile(tile, c.getColorNum());
            mainUI.colorTile(tempTile, c.getColorNum());
            tempCorp.founded(getCurrentPlayer(), tile);
            //make other tile corporation
            tempTile.setCorp(c);

        } else {
            //merge corporation
            Stack<Corporation> corporations = new Stack<>();
            for (Tile t : tileStack){
                String mergingCheck = gameBoard.checkNearCorps(tile);
                if (mergingCheck.equals("MergeAction")) {
                    corporations.add(t.getCorp());
                }
            }
            Corporation win = gameBoard.mergeCorp(corporations, tile);
            gameBoard.ShareBonus(win);
        }
        gameBoard.getPlayedTiles().add(tile);
    }

    /**
     * Move game to next phase of turn
     *
     * @author Baylor McElroy
     */
    public void nextState() {
        gameBoard.nextState();
    }

    /**
     * Makes it the next players turn once player is done with their turn
     *
     * @author Baylor McElroy
     */
    public void nextTurn() {
        players.add(currentPlayer);
        this.currentPlayer = players.poll();
    }

    /**
     * When current player buys stock from corporation
     *
     * @param corporation Corporation being bought from
     * @param player      player who is buying it
     * @author Victoria Weir
     */
    public void buyStock(Corporation corporation, Player player) {
        player.buyStock(corporation);
        mainUI.showBought(corporation, player);
    }

    /**
     * When current player sells all stocks from a corporation
     *
     * @param corporation corporation of stock being sold
     */
    public void sellStock(Corporation corporation) {
        currentPlayer.sellStocks(corporation);
    }

    /**
     * When player trades stocks from one corporation to the merge corporation
     *
     * @param oldCorp corp to be traded from
     * @param newCorp corp being traded to
     */
    public void tradeStocks(Corporation oldCorp, Corporation newCorp) {
        currentPlayer.tradeStocks(oldCorp, newCorp);
    }

    /**
     * Gets the active corporations that are currently on the game board
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
     * @param tile      tile being added to a corporation
     * @param colorType color based on the corporation
     */
    public void colorTile(Tile tile, int colorType) {
        mainUI.colorTile(tile, colorType);
    }

    /**
     * Lets the player pick corporation during merge if both corporations equal each other
     *
     * @param corporations the corporation in merge
     */
    public void pickMerge(List<Corporation> corporations) {
        mainUI.chooseCorp(corporations, 2);
    }

    /**
     * Method for when the corporations are tied in tiles. This asks the current player's choice on which corporation takes over the others.
     *
     * @param corporations corporation 1
     * @param corporation  corporation 2
     * @param tile         the amount of tiles
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
     * Tests if the game can end and if so, prints the results
     *
     * @author Victoria Weir
     */
    public void endGame() {
        EndGame checkEnd = new EndGame();
        if (checkEnd.checkEndGame()) {
            checkEnd.distributeMoney();
            checkEnd.getOthers();
            checkEnd.getWinner();
        }
    }

    /**
     * Sets the current players of the game. Called once at start of game.
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
     * Get player whose turn it currently is
     *
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets players in game
     *
     * @return players in game
     */
    public Queue playerQueue() {
        return players;
    }

    /**
     * Gets player shares
     *
     * @return player shares as a list
     * @author Baylor McElroy
     */
    public int[] getPlayerShares() {
        int[] shares = new int[7];
        int i = 0;
        for (Stack<Stock> stack : currentPlayer.getCorps()) {
            shares[i] = stack.size();
        }
        return shares;
    }

    /**
     * Be able to see player's money
     *
     * @param player's total amount of money
     */
    public void seePlayerMoney(Player player) {
        player.checkMoney();
    }

    /**
     * Get current game state
     *
     * @return
     */
    public String getGameState() {
        System.out.println("Game.java getGameState top");
        return gameBoard.getBoardState();
    }

    /**
     * Gets first of the tile list
     *
     * @return get tile on top of list
     */
    public Tile getLastTile() {
        return gameBoard.getPlayedTiles().peek();
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
     * Gets the active corporations that are on the board
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
     * Gets corporation list that has all corporation names in it
     *
     * @return corporation list
     */
    public List<Corporation> getCorporationList() {
        return gameBoard.getCorporationList();
    }

    /**
     * Sets the game board. Only used for loading a game.
     *
     * @param gameBoard loaded game board
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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
    public boolean getHide(){
        return hide;
    }
}