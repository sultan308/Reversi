/**
 *If further moves are called on a game that has ended.
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */

public class GameOver extends Exception {
    // the game winner number or 0 if its a draw
    private int winner;

    /**
     *Creates a GameOver exception
     *
     * @param game winner
     */
    public GameOver(int winner)
    {
        this.winner = winner;
    }

    /**
     *get the game winner
     *
     * @returns the winner filed
     */
    public int getWinner() {
        return winner;
    }
}
