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


public class Tile {
    private final String letterID;
    private final int numbers;
    private Corporation corp;

    /**
     * Instance Variables
     *
     * @param letter is the letters of the tiles
     * @param num is the numbers of the tiles
     * @author Baylor McElroy
     */
    public Tile(String letter, int num){
        this.letterID = letter;
        this.numbers = num;
        this.corp = null;
    }

    /**
     * Public Getters and Setters
     */
    public int getNumbers(){return numbers;}

    public String getLetterID() {
        return letterID;
    }

    public int getXpos(){
        return numbers;
    }

    public int getYpos(){
        switch (letterID){
            case "A" : return 0;
            case "B" : return 1;
            case "C" : return 2;
            case "D" : return 3;
            case "E" : return 4;
            case "F" : return 5;
            case "G" : return 6;
            case "H" : return 7;
            case "I" : return 8;
            default : return -1;
        }
    }

    public Corporation getCorp(){
        return corp;
    }
    public void setCorp(Corporation corporation){
        this.corp = corporation;
    }

    @Override
    public String toString() {
        String sb = getLetterID() + getNumbers();
        return sb;
    }

}
