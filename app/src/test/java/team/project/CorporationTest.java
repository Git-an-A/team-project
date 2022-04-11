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

import org.checkerframework.checker.units.qual.Current;
import org.junit.jupiter.api.Test;

import javax.naming.InitialContext;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CorporationTest {
    @Test
    void testStartingNumberOfStocksInCompany() {
        var corporation = new Corporation("Test Company", 1, 1);
        assertEquals(25, corporation.getStocks().size());
    }

    @Test
    void testFoundedCompany() {
        var corporation = new Corporation("Test Company", 1, 1);

    }

    /**@Test
    void testGivingStocksToPlayer() {
        var corporation = new Corporation("Test Company", 1, 1);
        var player = new Player("Test Name");
        var game = Game.getInstance();
        GameBoard gameBoard = new GameBoard();
        game.setUpGame();
        game.startGame();
        var tile = new Tile("A", 1);
        game.playTile(tile);
        Stock tempStock = corporation.giveStock(player);
        assertNotEquals(null, tempStock);
        int stockCount = player.viewStocks(tempStock.getName(), player);
        assertEquals(1, stockCount);
    }**/

    @Test
    void testBuyStock() {
        var corporation = new Corporation("Test Company", 1, 1);
        var player = new Player("Test Name");
        int initialPlayerMoney = player.checkMoney();
        corporation.buyStock(player);
        int playerCurrentMoney = player.checkMoney();
        assertNotEquals(initialPlayerMoney, playerCurrentMoney);
    }

    @Test
    void testGetValue() {
        var corporation = new Corporation("Test Company", 1, 1);
        assertEquals(300, corporation.getValue(1));
        assertEquals(400, corporation.getValue(2));
    }

    /**@Test
    void testSellStock() {
        var corporation = new Corporation("Test Company", 1, 1);
        var player = new Player("Test Name");
        int initialPlayerMoney = player.checkMoney();
        corporation.sellStock(player, 1);
        int playerCurrentMoney = player.checkMoney();
        assertNotEquals(initialPlayerMoney, playerCurrentMoney);
    }**/
}
