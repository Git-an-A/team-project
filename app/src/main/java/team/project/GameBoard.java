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
import java.util.Queue;
import java.util.Stack;

public class GameBoard {
    private Tiles tiles;
    private int corporationsPlaced;
    private int size;
    List<Stack<Stock>> corps;
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
        corps = new ArrayList<Stack<Stock>>();
        for(int i=0;i<8;i++){
            corps.add(new Stack<Stock>());
        }

        boardState = play;
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

    public void ShareBonus(Corporation corp){
        Queue playerList = Game.getInstance().playerQueue();
        Player next = findPlayer();
        List<Player> tempCompare = null;

        int maxStock = 1;
        for(int i= 0; i< playerList.size(); i++){
            if(next.getCorps().contains(corp.getName())){
                tempCompare.add(next);
                continue;
            }
            findNext();
        }
//        for(int i=0; i< tempCompare.size(); i++) {
//            int current = tempCompare.get(i).getCorps().size();
//            if(current > maxStock){
//                maxStock = current;
//                continue;
//            }
//            for(int n= 10; n > 0; n--) {
//                int check = tempCompare.get(n).getCorps().size();
//                if(check > maxStock) {
//                    maxStock = current;
//                }
//            }
//        }
    }

    public Player findPlayer(){
        Player player = Game.getInstance().getCurrentPlayer();
        return player;
    }
    public void findNext(){
        Game.getInstance().playerQueue().poll();
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

    public String getBoardState(){
        return boardState;
    }

    public Tiles getTiles(){

        return tiles;
    }
}
