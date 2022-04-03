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

public class Corporation {
    private ArrayList<Stock> stocks;
    private int price;
    private String name;
    private int size;

    public boolean giveStocks(Stock stockName, int amount){
        return true;
    }
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
}

