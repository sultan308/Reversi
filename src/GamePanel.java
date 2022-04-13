import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
/**
 * Th game panle controls the board,user and feedback panel to display the game information.
 *
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */
public class GamePanel extends JPanel
{
    private BoardPanel boardPanel;
    private UserPanel userPanel;
    private FeedBackPanel feedBackPanel;
    private Game game = null;

    /**
     * The user panel holds the player names , scores and also allows inputting the players names and starting a new game
     * through it.
     *
     *
     * @author Ahmed Soltan - as2529.
     * @version 05.05.2021
     */
    public GamePanel()
    {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setLayout(new BorderLayout(8,8));

        makeUserPanel();
        this.feedBackPanel = new FeedBackPanel();
        this.boardPanel = new BoardPanel();

        this.add(this.boardPanel,BorderLayout.CENTER);
        this.add(this.userPanel,BorderLayout.EAST);
        this.add(this.feedBackPanel,BorderLayout.SOUTH);

    }

    /**
     * Game field accessor.
     *
     *
     * @return current game
     */
    public Game getGame()
    {
        return this.game;
    }
    /**
     * Resets all the panels and sets game to null
     */
    public void reset()
    {
        this.userPanel.resetPanel();
        this.game = null;
        this.feedBackPanel.setDefault();
        this.boardPanel.resetBoard();
    }

    /**
     * Loads a game to the gui and updates all the panels accordingly.
     *
     * @param game the game to be loaded.
     */
    public void loadGame(Game game)
    {
        this.boardPanel.resizeBoard(game.getBoard().getBoardSize());
        this.setGame(game);
        this.userPanel.changePlayerNames(this.game.getPlayerName(1),this.game.getPlayerName(2));
        this.userPanel.lockPanel();

        try {
            int winner = this.game.getWinner();
            if(winner == 0) this.feedBackPanel.announceDraw();
            else this.feedBackPanel.announceWinner(this.game.getPlayerName(winner),winner);
        }
        catch (GameIsStillRunning gameIsStillRunning)
        {
            this.updatePanel();
        }

    }
    /**
     * Resets the panel and resizes the board.
     *
     * @param size new board size
     */

    public void changeGameBoardSize(int size)
    {
        this.reset();
        this.boardPanel.resizeBoard(size);
    }
    /**
     * Sets the game filed to a given game and updates all the cells in the board with the appropriate method and parameters.
     *
     *
     * @param game game to be used.
     */
    private void setGame(Game game)
    {
        this.game = game;

        int row = 0;
        for(ArrayList<BoardCell> rowOCells : this.boardPanel.getCellGrid())
        {
            int column = 0;
            for(BoardCell cell : rowOCells)
            {
                final Integer[] position = new Integer[]{row,column};
                cell.addActionListener(
                        e->{
                            this.placeDisc(position);
                        }
                );
                column++;
            }
            row++;
        }

        updatePanel();
    }

    /**
     * place a disc in the given position and updates the rest of the panels accordingly.
     *
     * @param position the postion to place the new disc as an integer array {row,column}
     */
    private void placeDisc(Integer[] position)
    {
        try {

            this.game.makeMove(position);
            updatePanel();
        }

        catch (GameOver gameOver)
        {
            updatePanel();
            int winner = gameOver.getWinner();
            if(winner == 0) this.feedBackPanel.announceDraw();
            else this.feedBackPanel.announceWinner(this.game.getPlayerName(winner),winner);
        }
        catch (IllegalMoveException illegalMoveException)
        {
            this.feedBackPanel.invalidMoveFeedBack();
        }
        catch (NoValidMoves noValidMoves)
        {
            updatePanel();
            this.noValidMovesMessage(noValidMoves.getPlayerName());
        }

    }
    /**
     * updates all the panels.
     */
    private void updatePanel()
    {
        int player =  this.game.getCurrentPlayer();
        this.boardPanel.updateBoard(this.game.getBoard());
        this.userPanel.updateScore(this.game.getBoard().getNumOfDiscs()[1],this.game.getBoard().getNumOfDiscs()[2]);
        this.boardPanel.showValidPositions(player,this.game.getNextValidPositions());
        this.feedBackPanel.updatePlayerTurn(this.game.getPlayerName(player),player);
    }
    /**
     * this error message is displayed if a player doesnt have any valid moves and their turn is passed.
     *
     * @param pName the player name who's turn has been passed.
     */
    private void noValidMovesMessage(String pName)
    {
        JOptionPane.showMessageDialog(this
                ,pName+"'s turn is passed because their weren't any valid moves"
                ,"No possible valid moves for " + pName
                ,JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * creates user panel
     */
    private void makeUserPanel()
    {
        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(
                (ActionEvent e) -> {
                    this.startGame();
                }
        );
        this.userPanel = new UserPanel(startBtn);
    }

    /**
     * Starts a game by geting the board size from the board panel and the players names from the user panel.
     */
    private void startGame()
    {
        String pOneName = this.userPanel.getPlayerOneName();
        String pTwoName = this.userPanel.getPlayerTwoName();
        if(pOneName.length() > 0 && pTwoName.length() > 0)
        {
            this.userPanel.lockPanel();

            int boardSize = this.boardPanel.getCellGrid().size();

            setGame(new Game(pOneName, pTwoName, boardSize));
        }
        else
        {
            JOptionPane.showMessageDialog(this
                    ,"Both players needs to have a name to start the game"
                    ,"No given name"
                    ,JOptionPane.ERROR_MESSAGE
            );
        }


    }
}
