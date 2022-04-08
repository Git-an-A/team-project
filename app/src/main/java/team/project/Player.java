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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Player in the game. Each player has money stocks and tiles
 *
 *
 */
public class Player {
    private Tile[] playerTiles;
    private String name;
    private ArrayList<Corporation> founded;
    private ArrayList<Stack<Stock>> corps;
    private Stack<Stock> corp1;
    private Stack<Stock> corp2;
    private Stack<Stock> corp3;
    private Stack<Stock> corp4;
    private Stack<Stock> corp5;
    private Stack<Stock> corp6;
    private Stack<Stock> corp7;
    private Stack<Stock> corp8;
    private int money;

    /**
     * Constructor requires name
     *
     * @param name the respective player's turn
     * @author Baylor McElroy
     */
    public Player(String name) {
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
        System.out.println("Player.java addTile() top");
        for(int i=0;i<6;i++){
            if(playerTiles[i]==null){
//                System.out.println("Player tiles pre " + playerTiles[i] + " location " + i);
                playerTiles[i] = tile;
//                System.out.println("Player tiles post " + playerTiles[i]+ " location " + i);
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
     * String representation of object
     *
     * @return toString of Player (name for now)
     * @author Baylor McElroy
     */
    public String toString(){
        String sb = name;
        return sb;
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
     * @author Tori Weir
     */
    public void printTiles(){
        for(var tile: playerTiles){
            System.out.println(tile.toString() + "\t");
        }
    }
    public void buyStock(Corporation corporation, int cost, int value) {
        //remove stock to corporation stack
        int number = corporation.getNumber();
        Stack<Stock> tempStack = corps.get(number);
        tempStack.add(corporation.giveStock(this));
        corps.set(number, tempStack);

        //subtract price if necessary
        takeMoney(cost);

    }

    public PlayerInv viewStocks() {
        return new PlayerInv();
    }

    public ArrayList<Stack<Stock>> getCorps() {
        return corps;
    }

    //will work more on this
    public void tradeStocks(Corporation oldCorporation, Corporation newCorporation) {
        //get old corporation number
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

    public void sellStocks(Corporation corporation, int cost, int value) {
        //remove stock from player's inventory
        int number = corporation.getNumber();
        Stack<Stock> tempStack = corps.get(number);
        int amount = value * tempStack.size();
        giveMoney(amount);

        corps.remove(tempStack);

    }

    public boolean discardDeadTile(Tile tile) {
        return true;
    }

    public GameOptions endGame() {
        return new GameOptions();
    }

    public void addFounded(Corporation corporation){
        founded.add(corporation);
    }
    public void takeMoney(int amount){
        money = money - amount;
    }
    public void giveMoney(int amount){
        money = money + amount;
    }
    public int checkMoney(){return money;}
}