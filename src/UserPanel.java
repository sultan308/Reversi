import javax.swing.*;
import java.awt.*;

/**
 * The user panel holds the player names , scores and also allows inputting the players names and starting a new game
 * through it.
 *
 *
 * @author Ahmed Soltan - as2529.
 * @version 05.05.2021
 */
public class UserPanel extends JPanel
{
    private JTextField playerOneNameTF,playerTwoNameTF;

    private JLabel playerOneScoreLbl,playerTwoScoreLbl;
    private JButton startButton;

    private GridBagConstraints gridBagConstraints;
    private final static String SCORE_LABEL = " | Score : ";
    /**
     *Initiates the panels and the fields.
     */
    public UserPanel(JButton StartBtn)
    {
        super();
        this.setLayout(new GridBagLayout());

        this.gridBagConstraints = new GridBagConstraints();

        this.playerOneNameTF = new JTextField(12);
        this.playerTwoNameTF = new JTextField(12);

        // Aligning text to the middle
        playerOneNameTF.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoNameTF.setHorizontalAlignment(SwingConstants.CENTER);

        this.playerOneScoreLbl = new CenteredTextJLabel("0");
        this.playerTwoScoreLbl = new CenteredTextJLabel("0");

        this.startButton = StartBtn;

        this.constructPanel();



    }
    /**
     *Locks the text fields and hides the button.
     */
    public void lockPanel()
    {
        this.playerOneNameTF.setEnabled(false);
        this.playerTwoNameTF.setEnabled(false);

        this.startButton.setEnabled(false);
        this.startButton.setVisible(false);
    }
    /**
     * Clears the textiles and unlocks them if they are locked.
     * Also undies the start button if its hidden.
     */
    public void resetPanel()
    {
        this.playerOneNameTF.setText("");
        this.playerTwoNameTF.setText("");

        this.playerOneNameTF.setEnabled(true);
        this.playerTwoNameTF.setEnabled(true);

        this.playerOneScoreLbl.setText("0");
        this.playerTwoScoreLbl.setText("0");

        this.startButton.setEnabled(true);
        this.startButton.setVisible(true);
    }
    /**
     * Get's player one's name from the corresponding text field
     *
     * @return player one's name.
     */
    public String getPlayerOneName() {
        return playerOneNameTF.getText().strip();
    }
    /**
     * Get's player two's name from the corresponding text field
     *
     * @return player two's name.
     */
    public String getPlayerTwoName() {
        return playerTwoNameTF.getText().strip();
    }


    /**
     * Player names textfields setter.
     *
     * @param pOneName player one's name.
     * @param pTwoName player two's name.
     */
    public void changePlayerNames(String pOneName, String pTwoName)
    {
        this.playerOneNameTF.setText(pOneName);
        this.playerTwoNameTF.setText(pTwoName);
    }

    /**
     * updates the score lable for each player respectively.
     *
     * @param pOneScore player one's score.
     * @param pTwoScore player two's score.
     */
    public void updateScore(int pOneScore, int pTwoScore)
    {
        this.playerOneScoreLbl.setText(Integer.toString(pOneScore));
        this.playerTwoScoreLbl.setText(Integer.toString(pTwoScore));
    }
    /**
     * Adds each graphical component to its exact location on the panel
     */
    private void constructPanel()
    {
        JLabel playerOne = new CenteredTextJLabel("Player one ( Black )");
        this.addToPanel(playerOne,0,0,3);

        this.addToPanel(this.playerOneNameTF,0,1);
        this.addToPanel(new CenteredTextJLabel(SCORE_LABEL),1,1);
        this.addToPanel(this.playerOneScoreLbl,2,1);

        JLabel playerTwo = new CenteredTextJLabel("Player two ( White )");
        this.addToPanel(playerTwo,0,2,3);

        this.addToPanel(this.playerTwoNameTF,0,3);
        this.addToPanel(new CenteredTextJLabel(SCORE_LABEL),1,3);
        this.addToPanel(this.playerTwoScoreLbl,2,3);

        this.addToPanel(this.startButton,0,4,3);

    }
    /**
     * Add a component to the panel.
     *
     * @param component  graphical component.
     * @param xPosition component's x axis position.
     * @param yPosition component's y axis position.
     *
     */

    private void addToPanel(JComponent component, int xPosition, int yPosition)
    {
        this.addToPanel(component,xPosition,yPosition,1);
    }
    /**
     * Add a component to the panel.
     *
     * @param component  graphical component.
     * @param xPosition component's x axis position.
     * @param yPosition component's y axis position.
     * @param componentWidth component's width.
     */
    private void addToPanel(JComponent component, int xPosition, int yPosition,int componentWidth)
    {
        this.gridBagConstraints.gridx = xPosition;
        this.gridBagConstraints.gridy = yPosition;
        this.gridBagConstraints.gridwidth = componentWidth;
        if (componentWidth > 1) this.gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        this.add(component,this.gridBagConstraints);

    }
}
