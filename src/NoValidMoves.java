/**
 *No valid moves exception gets thrown if a player has no moves to play.
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */
public class NoValidMoves extends Exception{
    String playerName;
    /**
     *Creates an exception for a player that doesn't have moves.
     *
     * @param playerName the player name
     */
    public NoValidMoves(String playerName)
    {
        this.playerName = playerName;
    }

    /**
     *Player name field accessor.
     *
     * @returns the player name
     */
    public String getPlayerName() {
        return playerName;
    }
}

