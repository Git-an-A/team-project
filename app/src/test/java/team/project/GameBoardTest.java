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

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    /**@Test
    void testGetTileStack() {
        var gameBoard = new GameBoard();
        var testTiles = new Tiles();
        gameBoard.setTileStack(testTiles.getTileList());
        Stack<Tile> tileStack = gameBoard.getTileStack();
        assertNotEquals(null, tileStack);
    }**/

    /**@Test
    void testCheckNearCorps() {
        var gameBoard = new GameBoard();
        var testTile = new Tile("A", 1);
        gameBoard.checkNearCorps(testTile);

    }**/

    /**@Test
    void testCheckPlace() {
        var gameBoard = new GameBoard();
        var tile = new Tile("A", 1);
        Boolean trueOrFalse = gameBoard.checkPlace(tile);
        assertEquals(false, trueOrFalse);
    }**/

    @Test
    void testFindPlayer() {
        var gameBoard = new GameBoard();
        var player = new Player("Test Name");
        player = gameBoard.findPlayer();
        assertEquals(null, player);
    }

    @Test
    void testCreateCorporationMethods() {
        var gameBoard = new GameBoard();
        List<Corporation> corporationList = gameBoard.createCorporationList();
        assertEquals(7, corporationList.size());
    }
}