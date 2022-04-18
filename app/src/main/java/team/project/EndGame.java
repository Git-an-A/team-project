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


import java.util.List;
import java.util.Queue;

/**
 * End Game will check if the game is ready to end
 *
 * @author Victoria Weir
 */
public class EndGame {
    private final Queue<Player> players;
    private final GameBoard gameBoard;

    EndGame(){
        Game getGame = Game.getInstance();

        this.players = getGame.playerQueue();
        this.gameBoard = getGame.getGameBoard();

    }
    public List<Corporation> getActiveCorporations(){return Game.getInstance().getActiveCorporations();}

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * For the end of the game- sells the players' stocks and get respective shares from active corporations
     *
     * @author Victoria Weir
     */
    public void distributeMoney() {
        for (Player player : players) {
            for (Corporation corporation : getActiveCorporations()) {
                player.sellStocks(corporation);
            }
        }
        for (Corporation corporation : getActiveCorporations()) {
            GameBoard calculate = getGameBoard();
            calculate.ShareBonus(corporation);
        }
    }

    /**
     * Figures out the winner by comparing them to each other
     *
     * @return winner
     * @author Victoria Weir
     */
    public String getWinner() {
        String m = "";
        int mostMoney = 0;
        Player winner = null;

        for (Player player : players) {
            if (player.checkMoney() > mostMoney) {
                mostMoney = player.checkMoney();
                winner = player;
            }
        }
        m = winner.getName() + " is the winner with $" + winner.checkMoney();
        return m;
    }

    /**
     * Looks at all the player's money
     *
     * @return a string with all the player's money
     * @author Victoria Weir
     */
    public String getOthers() {
        String m = "";
        for (Player player : players) {
            m += player.getName() + " has the total of $" + player.checkMoney();
        }
        return m;
    }

    /**
     * Checks to end the game
     *
     * @return sees if the game can end or not
     * @author Victoria Weir
     */
    public boolean checkEndGame() {
        for (Corporation corporation : getActiveCorporations()) {
            if (corporation.getPlayTiles().size() > 41 || corporation.getPlayTiles().size() > 11) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
