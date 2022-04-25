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
import java.util.Objects;
import java.util.Stack;

/**
 * Player class that defines the current players in the game. Each player has money, stocks and tiles.
 *
 * @author Baylor McElroy
 * @author Victoria Weir
 */
public class Player {
    private Tile[] playerTiles;
    private String name;
    private final ArrayList<Corporation> founded;
    private final ArrayList<Stack<Stock>> corps;
    private int money;

    /**
     * Constructor that gives the current player's tiles, money, and chosen actions
     *
     * @param name the respective player's turn
     * @author Baylor McElroy
     */
    public Player(String name) {
        founded = new ArrayList<>();
        corps = new ArrayList<>();
        playerTiles = new Tile[6];
        this.name = name;
        this.money = 6000;
        for(int i=0;i<8;i++){
            corps.add(new Stack<Stock>());
        }
    }

    /**
     * Adds a fresh tile to array of tiles owned by player
     *
     * @param tile gives them a tile
     * @author Baylor McElroy
     */
    public void addTile(Tile tile){
        for(int i=0;i<6;i++){
            if(playerTiles[i]==null){
                playerTiles[i] = tile;
                break;
            }
        }
    }

    /**
     * Removes tile from tiles player owns
     *
     * @param i position in array of tiles
     * @return tile removed
     * @author Baylor McElroy
     */
    public Tile removeTile(int i){
        Tile tempTile = playerTiles[i];
        playerTiles[i] = null;
        return tempTile;
    }

    /**
     * Gets a tile from the tiles owned by player
     *
     * @param i place of tile in array of tiles held by player
     * @return Tile of that given place
     * @author Baylor McElroy
     */
    public Tile getTile(int i){
        return playerTiles[i];
    }

    /**
     * Returns name of player
     *
     * @return player name
     * @author Baylor McElroy
     */
    public String getName() {
        return name;
    }

    /**
     * Prints all tiles held by player
     *
     * @author Baylor McElroy
     * @author Victoria Weir
     */
    public void printTiles(){
        for(var tile: playerTiles){
            System.out.println(tile.toString() + "\t");
        }
    }

    /**
     * When player decides to buy a stock from their chosen corporation.
     *
     * @param corporation desired corporation they buy from
     * @author Baylor McElroy
     */
    public void buyStock(Corporation corporation) {
        int number = corporation.getNumber();
        Stack<Stock> tempStack = corps.get(number);
        tempStack.add(corporation.giveStock(this));
        corps.set(number, tempStack);
        takeMoney(corporation.getPrice());
    }

    /**
     * See how many stocks the player has
     *
     * @param stockName is the stock name
     * @param player player who wants to see it
     * @return the number of stocks they own of that type
     * @author Victoria Weir
     */
    public int viewStocks(String stockName, Player player) {
        int counter= 0;
        for(int i=0; i< player.corps.size(); i++){
            String check = player.corps.get(i).toString();
            if(Objects.equals(check, stockName)){
                counter++;
            }
        }
        return counter;
    }


    public ArrayList<Stack<Stock>> getCorps() {
        return corps;
    }

    /**
     * Trade stocks with a ratio of 3:1
     *
     * @param oldCorporation old corporation which lost the merge
     * @param newCorporation new corporation which won the merge
     * @author Victoria Weir
     */
    public void tradeStocks(Corporation oldCorporation, Corporation newCorporation) {
        int number = oldCorporation.getNumber();
        Stack<Stock> oldStack = corps.get(number);
        Stack<Stock> newStack = null;
        Stack<Stock> remainderStack = null;
        int tradeIn;
        int remainder;

        if(oldStack.size()/3 == 0){
            tradeIn= oldStack.size()/3;
            for(int i=0; i< tradeIn; i++){
                newStack.add(newCorporation.giveStock(this));
            }
        }
        else{
            tradeIn = oldStack.size()/3;
            for(int i=0; i< tradeIn; i++){
                newStack.add(newCorporation.giveStock(this));
            }
            remainder = oldStack.size()%3;
            for(int i=0; i< remainder; i++){
                remainderStack.add(oldCorporation.giveStock(this));
            }
        }
        corps.remove(oldStack);
        oldCorporation.removeStock(this);
        corps.add(newStack);
        oldCorporation.giveStock(this);
    }

    /**
     * Player sells stocks back to the corporation
     *
     * @param corporation respective corporation which the stocks are from
     * @author Victoria Weir
     */
    public void sellStocks(Corporation corporation) {
        //remove stock from player's inventory
        int number = corporation.getNumber();
        Stack<Stock> tempStack = corps.get(number);
        int amount = corporation.getValue(tempStack.size());
        giveMoney(amount);
        corporation.removeStock(this);
        corps.remove(tempStack);
    }

    /**
     * Discards dead tiles
     *
     * @param tile tile that needs to be removed
     * @author Victoria Weir
     */
    public void discardDeadTile(Tile tile) {
            for(int i = 0; i< playerTiles.length; i++){
                if(getTile(i)==tile) {
                    removeTile(i);
            }
        }
    }

    /**
     * When the player founds a desired corporation they are able to get a stock bonus.
     *
     * @param corporation corporation which the player is founding
     */
    public void addFounded(Corporation corporation){
        founded.add(corporation);
    }

    /**
     * Methods that deals with checking, taking, and giving money to the player's money amount
     *
     * @param amount price
     */
    public void takeMoney(int amount){
        money = money - amount;
    }
    public void giveMoney(int amount){money = money + amount;}
    public int checkMoney(){
        return money;
    }

    /**
     * String representation of object
     *
     * @return toString of Player (name for now)
     * @author Baylor McElroy
     */
    public String toString(){
        String sb = name;
        return sb;
    }
}