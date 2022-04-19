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
import java.util.Queue;
import java.util.Stack;

/**
 * GameBoard that relates to anything that happens on the board
 *
 * @author Baylor McElroy
 * @author Victoria Weir
 */
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
    private Stack<Tile> playedTiles;

    /**
     * Initializes GameBoard and the corporations
      */
    public GameBoard(){
        //initialize game board
        tiles = new Tiles();
        for(int i=0;i<10;i++){
            tiles.shuffle();
        }
        corporationList = createCorporationList();
        playedTiles = new Stack<>();
        boardState = play;
    }

    /**
     * Gets a copy from tile stack
     *
     * @param tileStack copy of tile stack
     */
    public void setTileStack(Stack<Tile> tileStack) {
        Tiles copy = null;
        this.tileStack = copy.getTileList();
    }

    /**
     * Getter
     *
     * @return gets tile stack
     */
    public Stack<Tile> getTileStack() {
        return tileStack;
    }

    /**
     * Checks nearby corporations
     *
     * @param tile tile being placed
     * @return corporation if nearby or nothing
     * @author Victoria Weir
     */
    public String checkNearCorps(Tile tile){
        Stack<Tile> corpTiles = new Stack<>();
        int identifier = tile.getXpos();
        int letter = tile.getYpos();

        List<Corporation> moreThanOne = new ArrayList<>();
        List<Corporation> merge = new ArrayList<>();

        List<Corporation> corpor = Game.getInstance().getActiveCorporations();


        for(Corporation corporation : corpor){
            corpTiles = corporation.getPlayTiles();

            for(Tile tileList : corpTiles){
                int compare = tileList.getXpos();
                int comparison = tileList.getYpos();

                if(compare == identifier + 1 || compare == identifier - 1 || compare == identifier){
                    if(comparison == letter || comparison == 1 + letter || comparison == letter - 1){
                        if(corporation.isSafe()) {
                            moreThanOne.add(corporation);
                        }
                        merge.add(corporation);
                        return corporation.getName();
                    }
                }
            }
        }
        if(moreThanOne.size() > 2){
            return "Dead Tile";
        }
        else if(merge.size() > 2){
            return "Merge Action";
        }

        return "";
    }

    /**
     * Checks placement of tile
     *
     * @param tile being played
     * @return boolean, true if there is more than 2 corporation - false if not
     * @author Victoria Weir
     */
    public boolean checkPlace(Tile tile){
        String check = checkNearCorps(tile);

        if(check == "Dead Tile"){
            cannotPlace(tile);
            return true;
        }
        return false;
    }

    /**
     * deletes the tile
     *
     * @param tile dead tile
     * @author Victoria Weir
     */
    public void cannotPlace(Tile tile){
        findPlayer().discardDeadTile(tile);
    }

    /**
     * merging corporation
     *
     * @param corporations being merged
     * @param tile amount
     * @author Baylor McElroy
     */
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
     * @author Victoria Weir
     */
    public void ShareBonus(Corporation corp){
        Queue playerList = Game.getInstance().playerQueue();
        Player next = findPlayer();
        List<Player> tempCompare = null;

        int maxStock = 1;
        int secondStock = 1;
        for(int i= 0; i< playerList.size(); i++){
            if(next.getCorps().contains(corp.getName())){
                tempCompare.add(next);
                continue;
            }
            findNext();
        }

        Player majorWinner = null;
        Player minorWinner = null;
        assert tempCompare != null;
        for (Player player : tempCompare) {
            int current = player.getCorps().size();
            if (current > maxStock) {
                maxStock = current;
                majorWinner = player;
            }
        }
        for(int i=0; i< tempCompare.size(); i++) {
            tempCompare.remove(majorWinner);
            int current = tempCompare.get(i).getCorps().size();

            if (current > secondStock) {
                secondStock = current;
                minorWinner = tempCompare.get(i);
            }
        }

        assert majorWinner != null;
        majorWinner.giveMoney(corp.giveMajorBonus());

        assert minorWinner != null;
        minorWinner.giveMoney(corp.giveMinorBonus());
    }

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

    /**
     * getters for board state and get tiles
     *
     * @return gets booard state and tiles
     */
    public String getBoardState(){
        return boardState;
    }

    public Tiles getTiles(){

        return tiles;
    }

    /**
     * Gets the list of unplaced corporations
     *
     * @return list of unplaced corporations
     * @author Baylor McElroy
     */
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
    public Stack<Tile> getPlayedTiles(){
        return playedTiles;
    }
}
