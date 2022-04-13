import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * The FeedBackPanel gives feedback to the user about the current state of the game.
 *
 * @author Ahmed Soltan - as2529.
 * @version 05.05.2021
 */
public class FeedBackPanel extends JPanel
{
    private JLabel instructionsLabel,feedbackLabel;

    /**
     * Constructs the panel and set the messages to the default.
     */

    public FeedBackPanel()
    {
        super();
        this.setLayout(new GridLayout(3,1));

        this.instructionsLabel = new CenteredTextJLabel("");
        this.feedbackLabel = new CenteredTextJLabel("");

        this.add(this.instructionsLabel,0);
        this.add(new JSeparator(),1);
        this.add(this.feedbackLabel,2);

        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.setDefault();
    }
    /**
     * Sets the message to give the user instructions on how to start the game.
     */
    public void setDefault()
    {
        this.instructionsLabel.setText("Enter the players names and start the game when you are ready");
        this.feedbackLabel.setText("");
    }

    /**
     * Updates the lables to indatcted who is turn it is.
     *
     * @param playerName the next player to play name.
     * @param player the player number.
     */
    public void updatePlayerTurn(String playerName, int player)
    {
        String color = player == 1 ? "( Black )" : "( White )";
        String message ="It's "+playerName+"'s "+color+" turn.";
        this.instructionsLabel.setText(message);
        this.feedbackLabel.setText("");
    }
    /**
     * Indicates that the move the player is trying to play is invalid.
     */
    public void invalidMoveFeedBack()
    {
        this.feedbackLabel.setText("This move isn't valid, Please chose a valid move");
    }
    /**
     * Announces the winner.
     *
     * @param winnerName The winner name.
     * @param winner The winners number.
     */
    public void announceWinner(String winnerName,int winner)
    {
        String color = winner == 1 ? "( Black )" : "( White )";
        String message = winnerName+" "+color+" won the game.";
        this.feedbackLabel.setText(message);
        this.instructionsLabel.setText("");
    }
    /**
     * Announces a Draw.
     */
    public void announceDraw()
    {
        String message = "It's a draw";
        this.feedbackLabel.setText(message);
        this.instructionsLabel.setText("");
    }
}
