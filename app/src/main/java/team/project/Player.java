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

public class Player {
    private ArrayDeque<Tile> playerTiles;

    /**
     * Tries to get the player's tiles
     * @param playerName the respective player's turn
     */
    public Player(String playerName) {
        playerTiles = new ArrayDeque<Tile>();

    }

    /**
     * adds tile to player
     *
     * @param e gives them a tile
     */
    public void addTile(Tile e){playerTiles.addLast(e);}

    public String toString(){
        String sb= "";
        for(var tile: playerTiles){
            sb+= tile.toString() + "\t";
        }
        return sb;
    }

    public boolean buyStock(Stock stockName, int amount) {
        return true;
    }

    public PlayerInv viewStocks() {
        return new PlayerInv();
    }

    public boolean tradeStocks(Stock stockName, int amount) {
        return true;
    }

    public boolean sellStocks(Stock stock, int amount) {
        return true;
    }

    public boolean discardDeadTile(Tile tile) {
        return true;
    }

    public GameOptions endGame() {
        return new GameOptions();
    }
}