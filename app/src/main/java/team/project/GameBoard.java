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
import java.util.Stack;

public class GameBoard {
    private Tiles tiles;
    //private int corporationsPlaced;
    private int size;
    List<Corporation> corporationList;
    private int[][] board;
    private final String play = "PLAY";
    private final String exchange = "EXCHANGE";
    private final String draw = "DRAW";
    private String boardState;
    public GameBoard(){
        //initialize game board
        tiles = new Tiles();
        for(int i=0;i<10;i++){
            tiles.shuffle();
        }
        corporationList = createCorporationList();

        boardState = play;
    }

    public void mergeCorp(List<Corporation> corporations, Corporation corporation , Tile tile){
        for (Corporation item: corporations) {
            for (Tile t : item.getPlayTiles()) {
                t.setCorp(corporation);
            }
            tile.setCorp(corporation);
            Game.getInstance().colorTile(tile, corporation.getColorNum());
        }
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

    /**
     * moves board to next phase of turn
     * @author Baylor McELroy
     */
    public void nextState(){
        System.out.println("Game board next state top");
        switch (boardState){
            case play -> {
                boardState = exchange;
            }
            case exchange -> {
                boardState = draw;
            }
            case draw -> {
                boardState = play;
                Game.getInstance().getCurrentPlayer().addTile(tiles.dealTile());
            }
        }
    }
    /**
     * Corporation makings
     * @return the list of the 7 corporation
     * Needs more work
     */
    private List<Corporation> createCorporationList(){
        List<Corporation> corporations = new ArrayList<>();
        corporations.add(new Corporation("Worldwide", 1, 1));
        corporations.add(new Corporation("Sackson", 1, 2));
        corporations.add(new Corporation("Festival", 2, 3));
        corporations.add(new Corporation("Imperial", 2, 4));
        corporations.add(new Corporation("American", 2, 5));
        corporations.add(new Corporation("Continental", 3, 6));
        corporations.add(new Corporation("Tower", 3, 7));

        return corporations;
    }
    public String getBoardState(){
        return boardState;
    }

    public Tiles getTiles(){

        return tiles;
    }
    public List<Corporation> getUnplacedCorporations(){
        List<Corporation> tempList= new ArrayList<>();
        for (Corporation c: corporationList) {
            if (!c.getPlayed()){
                tempList.add(c);
            }
        }
        return tempList;
    }
}
