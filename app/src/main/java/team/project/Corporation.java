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

public class Corporation {
    private List<Tile> playTiles;
    private int price;
    private String name;
    private int size;
    private List<Stock> ownStock;
    private List<Stock> freeStock;

    private static final int[] stockPrices = new int[]{200,300,400,500,600,700,800,900,1000,1100,1200};
    private static final int[] majorShare = new int[]{2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000};

    /*
    for price:
    create a get price method
    create a price style instance variable
    initialize price style in constructor
    in get price call the array of prices that matches the style that was instantiated
     */

    /**
     * Constructor and sets up the two stock lists (owned and free)
     * @param name Name of the corporation
     * @param price value of the corporation
     */
    public Corporation(String name, int price){
        this.name = name;
        this.price = price;
        this.freeStock = startingStock();
        this.ownStock = new ArrayList<>();
    }

    /**
     * Starting stock for the corporation
     * @return the 25 stocks that are given for a corporation
     */
    private List<Stock> startingStock(){
        List<Stock> startStock = new ArrayList<>();
        for(int i=0; i<25; i++){
            startStock.add(new Stock(name, price));
        }
        return startStock;
    }

    /**
     * Founds a corporation
     * @param name is the player's name
     * @param tilePlacement is how many tiles was played to make a corporation
     * @param cost current value of the corporation
     * @param companyName name of corporation that was chosen
     */
    public void founded(Player name, int tilePlacement, int cost, String companyName){
        Stock foundingCo = new Stock(companyName, cost);

    }

    /**
     * Gives player a stock
     * @param playerName player that receives it
     */
    public void giveStocks(Player playerName){
        Stock availableStock = freeStock.get(0);
        freeStock.remove(availableStock);
        ownStock.add(availableStock);

        //something that gives player the stock

    }

    /**
     * When the player sells a stock
     *
     * @param playerName player who sold it
     */
    public void buyStock(Player playerName){
        removeStock(playerName);
        //something that takes money from player
    }

    /**
     * When the player buys a stock
     * @param playerName player who bought it
     */
    public void sellStock(Player playerName){
        giveStocks(playerName);
        //something that changes player's money
    }

    /**
     * removes stock from player
     *
      * @param playerName player who lost it
     */
    public void removeStock(Player playerName){
        Stock stock = null; // null for now
//        Something that can check for the stocks from player

        ownStock.remove(stock);
        freeStock.add(stock);

        //something that takes stock from player

    }

    /**
     * This figures out the current value of the stocks and Corporations
     *
     *  @return the value of the stock
     */
    public int calculateStockValue(){
        int size = playTiles.size();

        return size;
    }

    /**
     * Trades stocks from corporation
     * @param player
     */
    public void tradeStocks(Player player){

    }


    public boolean giveMajorBonus(Player player){
        return true;
    }

    public boolean giveMinorBonus(Player player){return true;}

    /**
     * Check if the corporation is safe or not
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
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size =  size;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void addTile(Tile tiles){
        playTiles.add(tiles);
    }

    public int getFreeStock() {
        return freeStock.size();
    }

    public int getOwnStock() {
        return ownStock.size();
    }
}

