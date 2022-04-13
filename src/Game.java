import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * The game class manges all the reversi game logic.
 *
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */
public class Game implements Serializable{

    private Board board;
    // The player number who's turn is next to play.
    private int currentPlayer = 1;

    // The valid next moves for the current player
    private ArrayList<Integer[]> nextValidPositions;

    //Holds the player names
    private String[] playersNames = new String[2];

    /*If the game is still running the value is -1
     *If the game has ended holds 0 for draw otherwise 1 or 2  corresponding to the wining player*/
    private int winner = -1;

    /**
     * sets up the game.
     *
     * @param pOneName player's one name
     * @param pTwoName player's two name
     * @param boardSize the chosen board size
     *
     */

    public Game(String pOneName,String pTwoName,int boardSize)
    {
        this.playersNames[0] = pOneName;
        this.playersNames[1] = pTwoName;
        this.board = new Board(boardSize);
        this.prepareBoard();
        updateValidNextPositions();
    }

    /**
     * Board field accessor
     *
     * @return the current game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Current player field accessor returns the number of the player that's playing next.
     *
     * @return the current player.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get's the player name corresponding to the given player number.
     *
     * @param player the player number.
     * @return the player name.
     */
    public String getPlayerName(int player)
    {
        return playersNames[player-1];
    }

    /**
     * Next valid moves field accessor.
     *
     * @return an array list of integer arrays that denotes the next valid moves positions.
     */
    public ArrayList<Integer[]> getNextValidPositions() {
        return nextValidPositions;
    }

    /**
     * Returns the winner or 0 if its a draw
     *
     * @throws GameIsStillRunning exception if the game is still running.
     *
     * @return winner player number or 0 if it's a draw.
     */
    public int getWinner() throws GameIsStillRunning
    {
        if (isGameOver()) return winner;
        else throw new GameIsStillRunning();
    }

    /**
     * Makes a move by populating a cell with a player number.
     *
     * @param newDiscPosition an integer array that holds the position to populate.
     *
     * @throws IllegalMoveException exception if the position is not a valid.
     * @throws GameOver if the game has ended.
     * @throws NoValidMoves if their is no valid moves for one of the players and their turn has been passed.
     *
     */

    public void makeMove(Integer[] newDiscPosition) throws IllegalMoveException, GameOver, NoValidMoves {
        if (!(isGameOver()))
        {
            if (checkIfMoveIsValid(newDiscPosition)) {

                this.board.setCell(newDiscPosition, currentPlayer);
                this.commitMove(newDiscPosition);
                this.nextTurn();

            } else {
                throw new IllegalMoveException();
            }
        }
        else
        {
            throw new GameOver(this.winner);
        }

    }

    /**
     * Saves the game to a given file path.
     *
     * @param gameFile the file path where the game object will be saved.
     *
     * @throws IOException exception if a problem occurs while trying to write the file.
     *
     */
    public void saveGame(String gameFile) throws IOException
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(gameFile);
            try(ObjectOutputStream gameObjectStream = new ObjectOutputStream(fileOutputStream))
            {
                gameObjectStream.writeObject(this);
            }
            catch (IOException ioException)
            {
                throw ioException;
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {

        }
    }


    /**
     * A static method that checks if a given file is a serialised game object.
     *
     * @param file the file to be checked.
     *
     * @return true if its a serialised game object or false otherwise.
     *
     */
    public static boolean isGame(File file)
    {
        try (ObjectInputStream gameObjectInputStream =  new ObjectInputStream(new FileInputStream(file)) )
        {
            Game loadedGame = (Game) gameObjectInputStream.readObject();
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }


    /**
     * A static method Reads a serialised game object and returns it.
     *
     * @param gameFile the file path that holds the serialised game object.
     *
     * @throws FileNotFoundException if the file couldn't be accessed.
     * @throws CorruptedGameFile if there was a problem with casting the object to a game object.
     * @throws IOException if there was a problem while trying to read the file.
     *
     * @return the game object.
     *
     */
    public static Game loadGame(File gameFile) throws FileNotFoundException,CorruptedGameFile,IOException  {

        FileInputStream fileInputStream = new FileInputStream(gameFile);
        try (ObjectInputStream gameObjectInputStream =  new ObjectInputStream(fileInputStream) ) {
            Game loadedGame = (Game) gameObjectInputStream.readObject();
            return loadedGame;
        }
        catch (StreamCorruptedException streamCorruptedException)
        {
            throw new CorruptedGameFile(gameFile.getName());
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            throw new CorruptedGameFile(gameFile.getName());
        }
    }

    /**
     * Checks if the game if over.
     *
     * @return true if the game is over or false otherwise.
     */
    private boolean isGameOver()
    {
        if(winner == -1) return false;
        return true;
    }

    /**
     *
     * Prepares the board at the beginning of the game by populating the four middle cells with discs
     *
     */

    private void prepareBoard()
    {
        int mid = (this.board.getBoardSize()/2)-1;
        for(int row = mid; row <= mid+1; row++ )
        {
            for (int col = mid; col <= mid +1; col++)
            {
                int value = row == col ? 1 : 2;
                this.board.setCell(row,col,value);
            }
        }
    }

    /**
     *Swap the player and checks if they have more valid moves.
     *
     * @throws GameOver if both players doesn't have moves.
     * @throws NoValidMoves if one of the players didn't have moves and theri turn has been passed.
     *
     */
    private void nextTurn() throws   GameOver, NoValidMoves
    {
        switchPlayer();
        if(nextValidPositions.size() == 0) {
            // saves the player name who didn't have moves.
            String playerName = this.playersNames[this.currentPlayer-1];

            switchPlayer();
            if (nextValidPositions.size() == 0) {
                this.setWinner();
                throw new GameOver(this.winner);
            }
            throw new NoValidMoves(playerName);
        }
    }

    /**
     *Swaps the player and updates their next valid moves.
     */
    private void switchPlayer()
    {
        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
        updateValidNextPositions();
    }

    /**
     *
     * Updates the next valid moves for the current player.
     *
     */
    private void updateValidNextPositions()
    {
        this.nextValidPositions = new ArrayList<>();
        int opponent = currentPlayer == 1 ? 2 : 1;

        // Gets all possible next moves adjacent to each opponent disc found
        for(int r = 0; r < this.board.getBoardSize(); r++)
        {
            for(int c = 0; c < this.board.getBoardSize(); c++)
            {
                if(this.board.getCell(r,c) == opponent)
                {
                    ArrayList<Integer[]> possibleMoves = getPossibleMoves(r, c);
                    this.nextValidPositions.addAll(possibleMoves);

                }
            }
        }

    }

    /**
     *Checks if placing an opponent disc in the empty neighbor cells of the given position would result in swapping other disc
     * if so it gets added to the possible moves.
     *
     * @param row the row of the target position.
     * @param column the column of the target position.
     *
     * @return Possible moves adjacent to the given position.
     */
    private ArrayList<Integer[]> getPossibleMoves(int row, int column)
    {
        ArrayList<Integer[]> possibleMoves = new ArrayList<>();
        ArrayList<Integer[]> emptyCells = this.board.getEmptyNeighbors(row, column);

        for(Integer[] emptyCell : emptyCells)
        {
            int rDiff = row - emptyCell[0];
            int cDiff = column - emptyCell[1];

            int curR = row;
            int curC = column;

            while(curR < this.board.getBoardSize() &&
                    curC <this.board.getBoardSize() &&
                    curC >= 0 &&
                    curR >=0)
            {

                if(this.board.getCell(curR,curC) == 0) break;

                else if (this.board.getCell(curR,curC) == currentPlayer)
                {
                    possibleMoves.add(emptyCell);
                    break;
                }

                else
                {
                    curC += cDiff;
                    curR += rDiff;
                }
            }

        }

        return possibleMoves;

    }

    /**
     *Finds the game winner by finding which has more discs if both are equal its a draw and updates the winner filed
     * accordingly
     * 1 or 2 if one of the players won.
     * 0 if its a draw.
     */
    private void setWinner()
    {
        int[] numOfDiscs = this.board.getNumOfDiscs();
        if(numOfDiscs[1] > numOfDiscs[2])
        {
            this.winner = 1;
        }
        else if (numOfDiscs[1] < numOfDiscs[2])
        {
            this.winner = 2;
        }
        else
        {
            this.winner = 0;
        }
    }

    /**
     *Checks if a move is valid by iterating over the valid moves.
     *
     * @param newDiscPosition the target position to check.
     *
     * @return true if the position is found in the valid moves list or false otherwise.
     */
    private boolean checkIfMoveIsValid(Integer[] newDiscPosition)
    {

        for(Integer[] validPosition : this.nextValidPositions)
        {

            if(Arrays.equals(newDiscPosition,validPosition)) return true;
        }
        return false;
    }

    /**
     *After a new cell has been populated given cell position this method finds all the effected postions
     * then iterates over them to flip them to the appropriate value.
     *
     * @param newDiscPosition the newly populated cell position.
     *
     */
    private void commitMove(Integer[] newDiscPosition)
    {
        ArrayList<Integer[]> swappedDiscsPositions = new ArrayList<>();

        /* this will keep going in all directions around the newly populated cells when it keeps track of the opponent's
         * discs until it reaches the end of the board if another disc of the current player is found it stops and stores all
         * found opponent discs to flip them later after all directions are checked */
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == j && i == 0)) {
                    int curR = newDiscPosition[0] + i;
                    int curC = newDiscPosition[1] + j;
                    ArrayList<Integer[]> tempP = new ArrayList<>();
                    while (curR < this.board.getBoardSize() &&
                            curC < this.board.getBoardSize() &&
                            curC >= 0 && curR >= 0) {

                        if (this.board.getCell(curR, curC) == 0) break;
                        else if (this.board.getCell(curR, curC) == currentPlayer) {
                            swappedDiscsPositions.addAll(tempP);
                            break;

                        }
                        else {

                            tempP.add(new Integer[]{curR, curC});
                            curR += i;
                            curC += j;
                        }
                    }
                }
            }
        }

        for (Integer[] swappedPosition : swappedDiscsPositions) {
            this.board.setCell(swappedPosition, currentPlayer);
        }

    }


}

