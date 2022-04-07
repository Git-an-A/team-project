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
import java.util.Collections;
import java.util.Stack;

/**
 * Java class for Tile
 */
public class Tiles{
    private Stack<Tile> tileList;

    /**
     * Constructor
     */
    public Tiles() {
        tileList = new Stack<Tile>();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        for (var letterID : letters){
            for(int i=0; i<=11; i++ ){
                Tile tileMake = new Tile(letterID,i);
                tileList.add(tileMake);
            }
        }
    }

    public String toString(){
        String sb ="";
        for (var tileMake: tileList){
            sb += tileMake.toString() + "\t";
        }
        return sb;
    }

    /**
     * Methods - size to get size of tiles, shuffle to shuffle through the tiles, and deal
     * -tiles to the player(s)
     *
     */
    public int size(){return tileList.size();}
    public void shuffle(){
        Collections.shuffle(tileList);
    }

    /**
     * Deals the tile
     * @return given tile is removed from the list
     */
    public Tile dealTile(){
        if(tileList.size()> 0){
            System.out.println("Tile delt: " + tileList.pop());
            return tileList.remove(0);
        }

        return null;
    }
}
