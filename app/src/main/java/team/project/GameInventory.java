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

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class GameInventory {
    private List<Tile> tiles;
    private int stocks;
    private int money;
    private ArrayList<Corporation> corporations;

    public Stock viewStocks(){
        return new Stock();
    }
    public boolean giveStocks(Stock stockName, int amount){
        return true;
    }
    public boolean gainStocks(Stock stockName, int amount){
        return true;
    }
    public boolean giveTiles(Tile tile){
        return true;
    }
    public String tilesToString(ArrayList<Tile> arT){
        return new String();
    }
    public boolean giveMoney(Player player, int amount){
        return true;
    }
    public boolean addMoney(Player player, int amount){
        return true;
    }
    public boolean viewCorporations(ArrayList<Corporation> arC){
        return true;
    }
    public int numTiles(){
        return 0;
    }
}
