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
import java.util.Stack;

public class Corporation {
    private Stack<Tile> playTiles;
    private int baseValue;
    private int size;
    private Stack<Stock> stocks;
    private String companyName;
    private int number;
    private boolean played;
    private int colorNum;

    // Reference of the prices
    private static final int[] stockPrices = new int[]{200,300,400,500,600,700,800,900,1000,1100,1200};
    private static final int[] majorShare = new int[]{2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000};
    private static final int[] minorShare = new int[]{1000, 1500,2000,2500,3000,3500,4000,4500,5000,5500,6000};

    /*
    for price:
    create a get price method
    create a price style instance variable
    initialize price style in constructor
    in get price call the array of prices that matches the style that was instantiated
     */

    /**
     * Constructor of corporation
     * @param companyName name of corporation
     * @param number identifier
     */
    public Corporation(String companyName, int number, int colorNum){
        this.companyName = companyName;
        this.stocks = new Stack<Stock>();
        this.colorNum = colorNum;
        this.playTiles = new Stack<>();
        stocks = startingStock();
        played = false;
        if(number == 1){
            this.baseValue = 200;
        }
        else if(number == 2){
            this.baseValue = 300;
        }
        else if(number == 3){
            this.baseValue = 400;
        }
    }

    /**
     * Starting stock for the corporation
     * @return the 25 stocks that are given for a corporation
     */
    private Stack<Stock> startingStock(){
        Stack<Stock> startStock = new Stack<>();
        for(int i=0; i<25; i++){
            startStock.add(new Stock(companyName));
        }
        return this.stocks=startStock;
    }

    /**
     * founds a corporation
     * @param player player founding corporation
     * @param tile location of corporation
     */
    public void founded(Player player, Tile tile){
        player.addFounded(this);
        playTiles.add(tile);
    }

    /**
     * Gives the player stock
     *
     * @param player player who receives it
     * @return stock
     */
    public Stock giveStock(Player player){
        int cost;
        int value;
        Stock tempStock = stocks.pop();
        value = 26 - stocks.size();
        if(stocks.size()==24){
            cost = 0;
            founded(player, Game.getInstance().getLastTile());
            tempStock.setCost(cost);
        }
        else {
            cost = getValue(value);
            tempStock.setCost(cost);
        }
        tempStock.setValue(getValue(value));
        return tempStock;
    }

    /**
     * When the player sells a stock
     *
     * @param playerName player who sold it
     */
    public void buyStock(Player playerName){
        int amount = playerName.getCorps().size();
        removeStock(playerName);
        playerName.giveMoney(getValue(amount));

    }

    /**
     * Calculates the current value of the stocks a player owns all together
     *
     * @param number the amount of stocks
     * @return the value
     */
    public int getValue(int number){
        return baseValue + 100 * number;
    }

    /**
     * Sells the stock to the player
     * @param player player who is buying it
     * @param amount amount of stocks
     */
    public void sellStock(Player player, int amount){
        player.takeMoney(getValue(amount));
        player.buyStock(this);

    }

    /**
     * removes stock from player
     *
      * @param playerName player who lost it
     */
    public void removeStock(Player playerName){
        for(int i=0; i< playerName.getCorps().size(); i++) {
            if (companyName.equals(playerName.getCorps())) {
                int numb = playerName.getCorps().size();
                for (int n = 0; n < numb; n++) {
                    playerName.getCorps().remove(n);
                }
            }
        }
    }

    /**
     * Calculates major bonus
     *
     * @return Major bonus
     */
    public int giveMajorBonus(){
        return getPrice()*10;
    }

    /**
     * Calculates minor bonus
     *
     * @return Minor bonus
     */
    public int giveMinorBonus(){return giveMajorBonus()/2;}

    /**
     * Check if the corporation is safe or not
     *
     * @return true if corporation is safe, false if it is less than 11
     */
    public boolean isSafe(){
        if(size >= 11){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Getters and Setters
     * Price is the cost/value of the Corporation
     */

    public int getNumber(){return number;}

    public int getSize(){
        return playTiles.size();
    }
    public void setSize(int size){
        this.size =  size;
    }

    public int getPrice() {
        return baseValue;
    }

    public String getName() {
        return companyName;
    }

    public void setBaseValue() {
        if(playTiles.size() >2 && playTiles.size()<11) {
            this.baseValue = getValue(1);
        }
    }
    public void setPlayed(boolean played){
        this.played = played;
    }
    public boolean getPlayed(){
        return played;
    }
    public String toString(){
        return companyName;
    }
    public void addTile(Tile tiles){
        playTiles.add(tiles);
    }
    public Stack<Tile> getPlayTiles(){
        return playTiles;
    }
<<<<<<< HEAD
    public Stack<Stock> getStocks() {
        return stocks;
=======
    public int getColorNum(){
       return colorNum;
>>>>>>> 44680b53ac097d67b82fa1e9a19cbb7de12fd1db
    }
}

