import java.io.Serializable;
import java.util.ArrayList;
/**
 * Board is a class that holds the state of the game board
 *
 *
 * The class has a two dimensional array where each position represent a cell on the board
 *
 * @author Ahmed Soltan.
 * @version 05.05.2021
 */
public class Board implements Serializable {
    //Each position represents a cell in the board 0 if empty, 1 if player one and 2 if player two
    private int[][] boardArray;

    //holds the array board size.
    private int boardSize;

    //keeps cont of the number of each cell value in a frequency array;
    private int[] numOfDiscs = new int[3];


    /**
     * Takes the board size and creates an empty two dimensional array and sets the frequency of
     * empty cells to the number total number of cells.
     *
     * @param size  the size of the board.
     */
    public Board(int size)
    {
        this.boardSize = size;
        this.boardArray = new int[this.boardSize][this.boardSize];
        this.numOfDiscs[0] = size*size;

    }
    /**
     * Gets the the frequency array of the cell values.
     *
     * @return numOfDiscs  The frequency array of the values in the board cells.
     */
    public int[] getNumOfDiscs(){
        return numOfDiscs;
    }

    /**
     * Gets the the value in the cell 1 for player one, 2 for player two and 0 if empty.
     *
     * @param row the row index of the position.
     * @param column the column index of the position
     *
     * @return The value of the cell.
     */
    public int getCell(int row, int column)
    {
        return boardArray[row][column];
    }

    /**
     * Finds the board size
     *
     * @return the board size as an integer.
     */
    public int getBoardSize()
    {
        return this.boardSize;
    }

    /**
     * Set the value of a cell to a given value.
     *
     * @param row the row index of the position.
     * @param column the column index of the position
     * @param value the value that the cell should be set to ie. 1,2 or 0.
     */
    public void setCell(int row ,int column,int value)
    {
        this.numOfDiscs[this.boardArray[row][column]]--;
        this.boardArray[row][column] = value;
        numOfDiscs[value]++;

    }
    /**
     * Set the value of a cell to a given value.
     *
     * @param position  the row and column index of the position in the
     *                  form of an integer array {row,column}
     * @param value the value that the cell should be set to ie. 1,2 or 0.
     */
    public void setCell(Integer[] position ,int value)
    {
        setCell(position[0],position[1],value);

    }

    /**
     * Gets all empty neighbours of a certain cell.
     *
     * @param row the row index of the position.
     * @param column the column index of the position
     *
     * @return emptyPositions ann Array list of integer arrays of length 2 representing the {row,column} of the
     * empty positions
     */

    public ArrayList<Integer[]> getEmptyNeighbors(int row, int column)
    {
        ArrayList<Integer[]> emptyPositions = new ArrayList<>();

        // gets all the neighbour positions and check if there empty.
        for(int i = row-1;i < row+2;i++)
        {
            if (i >= 0 && i < this.boardSize)
            {
                for (int j = column-1; j < column + 2; j++)
                {
                    if ( j >= 0 && j< this.boardSize)
                    {
                        if (this.boardArray[i][j] == 0) emptyPositions.add(new Integer[]{i, j});
                    }
                }
            }
        }
        return emptyPositions;
    }


}
