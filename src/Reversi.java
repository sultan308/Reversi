import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;



/**
 *The reversi frame holds the game panel.
 *
 * @author Ahmed Soltan.
 * @version 05.05.2021
 */
public class Reversi extends JFrame {

    private final GamePanel gamePanel = new GamePanel();

    public static void main(String[] args) {
        new Reversi();
    }


    /**
     *Sets up a reversi frame
     */
    public Reversi() {
        super();
        this.setTitle("Reversi");
        this.setContentPane(this.gamePanel);
        this.setVisible(true);
        this.setMinimumSize( new Dimension(900,800));
        this.createMenuBar();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *Affrims that the user wants to close reversi if a game is running then,
     *
     *@returns a WindowConstant that corsosponds with the action that needs to be taken
     */



    /**
     *Creates menu bar
     */
    private void createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem newGame = new JMenuItem("New");
        gameMenu.add(newGame);
        newGame.addActionListener((ActionEvent e)-> {

                    boolean confirmed = this.gamePanel.getGame() == null ? true : this.confirmAction("If you start a new game any unsaved progress will be lost.", "Start a new game");
                    if (confirmed) this.gamePanel.reset();
                }
        );



        JMenuItem saveGame = new JMenuItem("Save");
        gameMenu.add(saveGame);
        saveGame.addActionListener((ActionEvent e)-> {this.saveGame(); });


        JMenuItem openGame = new JMenuItem("Open");
        openGame.addActionListener((ActionEvent e)-> {this.loadGameFile(); });
        gameMenu.add(openGame);


        JMenu boardMenu = new JMenu("Board");
        menuBar.add(boardMenu);
        JMenu boardSizes = new JMenu("Change Size");
        boardMenu.add(boardSizes);

        // creates board size choices from 6x6 to 14x14 incrementing in 2.
        for (int size = 6 ; size <= 14 ; size +=2)
        {
            JMenuItem boardSize = new JMenuItem( size+" X " +size ,SwingConstants.CENTER);
            final int s = size;
            boardSize.addActionListener(
                    (ActionEvent e)->
                    {
                        boolean confirmed = this.gamePanel.getGame() == null ? true : this.confirmAction("If you change the board size any unsaved progress will be lost.", "Change size");
                        if(confirmed) this.gamePanel.changeGameBoardSize(s);

                    });
            boardSizes.add(boardSize);
        }

        this.setJMenuBar(menuBar);
    }
    /**
     *loads a game from a saved file
     */
    private void loadGameFile()
    {


        try {
            Game selectedGame = GameFilePicker.getGame();
            boolean confirmed = this.gamePanel.getGame() == null
                    ? true : this.confirmAction("After loading the saved game, Any unsaved progress in the current game will be lost.", "load the game");
            if(confirmed) this.gamePanel.loadGame(selectedGame);
        } catch (FileNotFoundException fileNotFoundException) {
            displayErrorMessage("The file couldn't be found");
        } catch (CorruptedGameFile corruptedGameFile) {
            displayErrorMessage("Couldn't read game file because, it was corrupted");
        }
        catch (IOException ioException)
        {
            displayErrorMessage("A problem occurred while trying to read thr file");
        }

    }
    /**
     *saves the current running game.
     */
    private void saveGame()
    {

        Game currentGame = this.gamePanel.getGame();
        if(currentGame == null )
        {
            displayErrorMessage("There needs to be a running game for it to be saved");
        }
        else {
            try {
                GameFilePicker.saveGameToFile(currentGame);
            } catch (IOException ioException) {
                displayErrorMessage("An error occurred while trying to save the game");
            }
        }
    }
    /**
     *Displays an error message.
     *
     * @param errorMessage  the message to be displayed
     */
    private void displayErrorMessage(String errorMessage)
    {
        JOptionPane.showMessageDialog(this
                ,errorMessage
                ,"A problem occurred."
                ,JOptionPane.ERROR_MESSAGE
        );
    }
    /**
     *Confirms an action that the user will take that might have an unwanted effect
     *
     * @param message Explanation of the action and the consequences.
     * @param action The confirming button text.
     *
     * @return the user response true if confirmed or false otherwise.
     */
    public boolean confirmAction(String message,String action)
    {
        Object[] options = {action , "Cancel"};
        int resp = JOptionPane.showOptionDialog(this,
                message,
                "Action confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[1]); //default button title
        if (resp == 0) return true;
        else return false;
    }






}
