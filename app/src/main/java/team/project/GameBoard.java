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

public class GameBoard {
    private Tiles tiles;
    private int corporationsPlaced;
    private int size;
    private int[][] board;
    private final String play = "PLAY";
    private final String exchange = "EXCHANGE";
    private final String draw = "DRAW";
    private String boardState;
    public GameBoard(){
        //initialize game board
        tiles = new Tiles();
    }
    public boolean placeCorp(Tile tile){
        return true;
    }
    public boolean placeUnacompanied(Tile tile){
        return true;
    }
    public ArrayList<Tile> mergeCorpCheck(Tile tile){
        return new ArrayList<Tile>();
    }
    public Corporation mergeCorp(Corporation corp1, Corporation corp2){

        return null; //new corp
    }
    public boolean giveStocks(Stock stockName, int amount){
        return true;
    }
    public boolean takeStocks(Stock stockName, int amount){
        return true;
    }
    public boolean giveMoney(Player player, int amount){
        return true;
    }
    public boolean takeMoney(Player player, int amount){
        return true;
    }

    public void nextState(){
        switch (boardState){
            case play : {
                setBoardState(exchange);

            }
            case exchange: {
                setBoardState(draw);
            }
            case draw: {
                setBoardState(play);
                Game.getInstance().getCurrentPlayer().addTile(tiles.dealTile());
            }
        }


    }

    public String getBoardState(){
        return boardState;
    }
    public void setBoardState(String boardState) {
        this.boardState = boardState;
    }
    public Tiles getTiles(){
        return tiles;
    }
}
