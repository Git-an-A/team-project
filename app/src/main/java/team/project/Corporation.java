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
    private ArrayList<Stock> stocks;
    private int price;
    private String name;
    private int size;
    private boolean found = false;
    private List<Stock> stockList;

    private static final int[] stockPrices = new int[]{200,300,400,500,600,700};

    public Corporation(String name, int price){
        this.name = name;
        this.price = price;
    }

    /**
     * Need something that will get the corporation's starting stock
     */
    private List<Stock> startingStock(){
        List<Stock> startStock = new ArrayList<>();

        return startStock;
    }

    /**
     * Founds a corporation
     * @param name is the player's name
     * @param tilePlacement is how many tiles was played to make a corporation
     * @param cost current value of the corporation
     * @param companyName name of corporation
     */
    public void founded(Player name, int tilePlacement, int cost, String companyName){
        Stock foundingCo = new Stock(companyName, 1, cost);

    }

    public boolean giveStocks(Stock stockName, int amount){

        return true;
    }

//    /**
//     * (Hopefully) gets the stocks that are not taken
//     * @return the list of free stocks
//     */
//    private List<Stock> getFreeStocks(){
//        List<Stock> freeStock = new ArrayList<>();
//        for(Stock stk : this.stockList){
//            if(stk.getName() == null){
//                freeStock.add(stk);
//            }
//        }return freeStock;
//    }

    public boolean getStocks(Stock stockName, int amount){
        return true;
    }
    public boolean giveBonus(Player player, int amount){
        return true;
    }

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
}

