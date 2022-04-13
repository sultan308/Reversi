import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * BoardPannel is a class that inherits from J panel it is the visual representation of the game board.
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */

public class BoardPanel extends JPanel
{
    // An two dimensional array list of buttons actioning as board cells.
    private ArrayList<ArrayList<BoardCell>> cellGrid;

    /**
     * Creates a default 8 x 8  grid of buttons and sets the background to white.
     *
     */
    public BoardPanel()
    {
        super();
        this.setBackground(Color.white);
        this.cellGrid = new ArrayList<>();

        for (int row = 0; row < 8;row++ )
        {
            ArrayList<BoardCell> cellsRow = new ArrayList<>();

            for (int column = 0 ; column < 8;column++)
            {

                cellsRow.add(new BoardCell());
            }
            this.cellGrid.add(cellsRow);
        }
        displayGrid();
    }
    /**
     * Gets the board on the form of a two dimensional array list of buttons.
     *
     * @return cellGrid the two dimensional array list of buttons
     */
    public ArrayList<ArrayList<BoardCell>> getCellGrid()
    {
        return cellGrid;
    }
    /**
     * Resets the frame then adds the all grid buttons to the frame to be displayed to the user
     */
    private void displayGrid()
    {
        this.removeAll();
        this.setLayout(new GridLayout(this.cellGrid.size(),this.cellGrid.size()));
        for(ArrayList<BoardCell> row : cellGrid)
        {
            for (BoardCell cell : row)
            {
                this.add(cell);
            }
        }
        this.revalidate();
        this.repaint();
    }
    /**
     * Re adds all the cells and resets all of the cells.
     */
    public void resetBoard()
    {
        this.resetCells();
        displayGrid();
    }
    /**
     * Changes the board size.
     *
     * @param newSize new board size.
     */
    public void resizeBoard(int newSize)
    {
        int diff = this.cellGrid.size()-newSize;

        while (diff > 0)
        {
            this.removeRow();
            this.removeColumn();
            diff--;
        }

        while (diff < 0)
        {
            this.addRow();
            this.addColumn();
            diff++;
        }

        resetBoard();
    }
    /**
     * Iterates over all the Board cells and remove any action listeners,text or icons.
     */
    private void resetCells()
    {
        for(ArrayList<BoardCell> row : this.cellGrid)
        {
            for (BoardCell cell:row)
            {
                cell.clearActionListeners();
                cell.clearCell();
            }
        }
    }
    /**
     * Iterates over all the Board cells and adds the appropriate visual representation according to the
     * corresponding position in the given board.
     *
     * Empty if 0
     * Black if 1,
     * and White if 2.
     *
     * @param board an Object of type board
     */
    public void updateBoard(Board board)
    {
        for(int row = 0; row < board.getBoardSize();row++)
        {
            for (int column = 0 ; column < board.getBoardSize();column++)
            {
                int player = board.getCell(row,column);
                if(player == 0)
                {
                    this.getCellGrid().get(row).get(column).clearCell();
                }
                else
                {
                    this.getCellGrid().get(row).get(column).populateWithDisc(player);
                }
            }
        }

    }
    /**
     * Iterates over the invalid position and set them as valid to indicate that they are possible moves.
     *
     * @param player the player turn 1 or 2.
     * @param nextValidMoves an Arraylist of integer arrays that represents the next valid positions {row,column}.
     *
     */
    public void showValidPositions(int player,ArrayList<Integer[]> nextValidMoves)
    {
        for(Integer[] move: nextValidMoves)
        {
            this.cellGrid.get(move[0]).get(move[1]).setAsValid(player);
        }
    }
    /**
     * Removes the last row from the board.
     */
    private void removeRow()
    {
        cellGrid.remove(cellGrid.size()-1);
    }
    /**
     * Adds one row to the end of the board.
     */
    private void addRow()
    {
        int width = cellGrid.get(0).size();
        ArrayList<BoardCell> row = new ArrayList<>();
        for (int i = 0 ; i < width ; i++)
        {
            row.add(new BoardCell());
        }
        this.cellGrid.add(row);
    }
    /**
     * Adds one column to the end of the board.
     */
    private void addColumn()
    {
        int height = cellGrid.size();

        for(int i = 0 ; i < height; i++)
        {

            this.cellGrid.get(i).add(new BoardCell());

        }
    }
    /**
     * Removes the last column from the board.
     */
    private void removeColumn()
    {
        int height = cellGrid.size();

        for(int i = 0 ; i < height; i++)
        {
            int lastIndex = this.cellGrid.get(i).size()-1;
            this.cellGrid.get(i).remove(lastIndex);

        }
    }


}
