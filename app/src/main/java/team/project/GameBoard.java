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

import com.sun.scenario.animation.shared.FiniteClipEnvelope;
import org.checkerframework.checker.units.qual.C;

import java.util.*;

public class GameBoard {
    private Tiles tiles;
    private Stack<Tile> tileStack;
    //private int corporationsPlaced;
    private int size;
    private List<Corporation> corporationList;
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

    public void setTileStack(Stack<Tile> tileStack) {
        Tiles copy = null;
        this.tileStack = copy.getTileList();
    }

    public Stack<Tile> getTileStack() {
        return tileStack;
    }

    public String checkNearCorps(Tile tile){
        Stack<Tile> corpTiles = new Stack<>();
        int identifier = tile.getXpos();
        int letter = tile.getYpos();
        List<Corporation> corpor = Game.getInstance().getActiveCorporations();


        for(Corporation corporation : corpor){
            corpTiles = corporation.getPlayTiles();

            for(Tile tileList : corpTiles){
                int compare = tileList.getXpos();
                int comparison = tileList.getYpos();

                if(compare == identifier + 1 || compare == identifier - 1 || compare == identifier){
                    if(comparison == letter || comparison == 1 + letter || comparison == letter - 1){
                        return corporation.getName();
                    }
                }
            }
        }
        return "This is working";
    }

//    public static void main(String[] args) {
//        Tile trying = new Tile("Z", 3);
//        int letter = trying.getXpos() + trying.getYpos();
//        System.out.println(letter);
//        System.out.println(trying.getYpos());
//
//        GameBoard testing = new GameBoard();
//        testing.getTileStack();
//        System.out.println(testing.getTileStack().toString());
//        testing.createCorporationList();
//        System.out.println(testing.getCorporationList().toString());
//        String works = testing.checkNearCorps(trying);
//        System.out.println(works);
//    }

    //For dead tiles
    public String cannotPlace(Tile tile){
        String m = "";
        checkNearCorps(tile);


        return m;
    }

    public void mergeCorp(List<Corporation> corporations, Tile tile){
        int maxSize = 0;
        Corporation corporation;
        for (Corporation c: corporations) {
            if(c.getSize()>maxSize){
                maxSize = c.getSize();
            }
        }
        List<Corporation> maxCorps = new ArrayList<>();
        for (Corporation c : corporations) {
            if(c.getSize() == maxSize){
                maxCorps.add(c);
            }
        }

        if(maxCorps.size() == 1){
            corporation = maxCorps.get(0);
            for (Corporation item: corporations) {
                for (Tile t : item.getPlayTiles()) {
                    t.setCorp(corporation);
                    Game.getInstance().colorTile(t, corporation.getColorNum());
                }
            }
            tile.setCorp(corporation);
            Game.getInstance().colorTile(tile, corporation.getColorNum());
        }
        else {
            Game.getInstance().pickMerge(maxCorps);
        }
    }

    /**
     * This will give the players the sharebonus when a corporation is merged
     *
     * @param corp corporation that is merged
     */
    public void ShareBonus(Corporation corp){
        Queue playerList = Game.getInstance().playerQueue();
        Player next = findPlayer();
        List<Player> tempCompare = null;

//        playerList.add(new Object());
//        playerList.add(new Object());


        int maxStock = 1;
        int secondStock = 1;
        for(int i= 0; i< playerList.size(); i++){
            if(next.getCorps().contains(corp.getName())){
                tempCompare.add(next);
                continue;
            }
            findNext();
//            System.out.println(next.toString());
//            System.out.println("Got this far");
        }

        Player majorWinner = null;
        Player minorWinner = null;
        for(int i=0; i< tempCompare.size(); i++) {
            int current = tempCompare.get(i).getCorps().size();
            if(current > maxStock){
                maxStock = current;
                majorWinner = tempCompare.get(i);
            }
//            System.out.println(current);
        }
        for(int i=0; i< tempCompare.size(); i++) {
            tempCompare.remove(majorWinner);
            int current = tempCompare.get(i).getCorps().size();

            if (current > secondStock) {
                secondStock = current;
                minorWinner = tempCompare.get(i);
            }
        }

        majorWinner.giveMoney(corp.giveMajorBonus());
        minorWinner.giveMoney(corp.giveMinorBonus());
//        System.out.println(majorWinner.toString() + minorWinner.toString());
    }
//    public static void main(String[] args) {
//        GameBoard testing = new GameBoard();
//        Corporation corp = new Corporation("name", 3);
//        Player player1 = new Player("player1");
//        player1.buyStock(corp,0,0);
//        Player player2 = new Player("player2");
//        player2.buyStock(corp,0,0);
//        player2.buyStock(corp,0,0);
//        testing.ShareBonus(corp);
//    }

    /**
     * Finds the current player
     *
     * @return current player
     */
    public Player findPlayer(){
        Player player = Game.getInstance().getCurrentPlayer();
        return player;
    }

    /**
     * Finds the next player of the queue
     */
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
        //System.out.println("Game board next state top");
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
     * Corporation List
     *
     * @return the list of the 7 corporation
     */
    public List<Corporation> createCorporationList(){
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
        List<Corporation> tempList = new ArrayList<>();
        for (Corporation c: corporationList) {
            if (!c.getPlayed()){
                tempList.add(c);
            }
        }
        return tempList;
    }

    public List<Corporation> getCorporationList() {
        return corporationList;
    }
}
