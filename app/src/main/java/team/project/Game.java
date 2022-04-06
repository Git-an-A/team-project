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

import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList<Player> players;
    private GameBoard gameBoard;
    private final int startMoney = 6000;

    /**
     * Setting up game
     *
     * @param go thing that makes it go
     * @param numPlayers how many players will play
     */
    public void setUpGame(GameOptions go, int numPlayers) {
        GameOptions getPlayers = new GameOptions();
        getPlayers.start(numPlayers);
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



    public boolean nextTurn(Player player) {
        return true;}

    public boolean tallyScore(Player player, int amount) {
        return true;}

    public boolean takeTurn(Player player) {
        return true;}

    public void displayInfo() {
    }

    public void openOptions() {
    }

    public boolean drawTile(Player player) {
        return true;}

    public boolean buyStock(Stock stockName, int amount) {
        return true;}

    public boolean sellStock(Stock stockName, int amount) {
        return true;}

    public Stock viewStocks(Stock stock) {
        return new Stock();}

    public boolean playTile(Tile tile) {
        //MainUI.playTile();
        return true;
    }

    public boolean tradeStocks(Stock stockName, int amount) {
        return true;}

    public boolean sellStocks(Stock stockName, int amount) {
        return true;}

    public void endGame() {}
}