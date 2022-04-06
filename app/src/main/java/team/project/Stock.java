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

public class Stock {
    public String name;
    public int amount;
    public int cost;
    private Player player = null;

    public Stock(){};

    /**
     * Constructor
     *
     * @param name Name of the corporation in which this stock is in
     * @param cost Cost/value of the stock
     */
    public Stock(String name, int cost){
        this.amount = amount;
        this.cost = cost;
        this.name = name;
    }
    /**
     * Public and Setters
     */
    public int getAmount() {return amount;}
    public String getName() {return name;}
    public int getCost(){return cost;}

    public void setName(String name) {this.name = name;}

    public void setAmount(int amount) {this.amount = amount;}

    public void setCost(int cost) {this.cost = cost;}

    /**
     * Gets player
     * @return current player
     */
    public Player getPlayer() {return player;}

    /**
     * gets the player's value of the stock
     * @param cost Value of the stock owned by the player
     */
    public void setPlayerValue(Player cost) {
        this.player = cost;
    }

}
