import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * BoardCell extends from JButton, it represents a board cell for the GUI.
 *
 *
 * @author Ahmed Soltan - as2529.
 * @version 05.05.2021
 */

public class BoardCell extends JButton
{
    //Icons for black and white discs in an array.
    private static final ImageIcon[] DISCS_ICONS = new ImageIcon[]{new ImageIcon(Objects.requireNonNull(BoardCell.class.getResource("icons//blackDisc.png"))),new ImageIcon(Objects.requireNonNull(BoardCell.class.getResource("icons//whiteDisc.png")))};
    //Icons indicating a valid next move in black and white.
    private static final ImageIcon[] VALID_MOVES_ICONS = new ImageIcon[]{new ImageIcon(Objects.requireNonNull(BoardCell.class.getResource("icons//nextBlack.png"))),new ImageIcon(Objects.requireNonNull(BoardCell.class.getResource("icons//nextWhite.png")))};



    /**
     * sets up the button and it's background,foreground and border.
     *
     */
    public BoardCell()
    {
        super();
        this.setBackground(Color.lightGray);
        this.setForeground(Color.white);
        this.setBorder(new StrokeBorder(new BasicStroke()));
        this.setOpaque(true);
    }
    /**
     * sets the button icon to the corresponding player disc icon.
     *
     * @param player  the player number 1 or 2
     *
     */
    public void populateWithDisc(int player)
    {
        this.setIcon(DISCS_ICONS[player - 1]);

    }
    /**
     * sets the button icon to the corresponding valid next move icon.
     *
     * @param player  the player number 1 or 2
     *
     */
    public void setAsValid(int player)
    {
        this.setIcon(VALID_MOVES_ICONS[player - 1]);
    }
    /**
     * clears the button from any action listeners.
     *
     */
    public void clearActionListeners()
    {
        ActionListener[] actionListeners =  this.getActionListeners();
        for(ActionListener aL : actionListeners)
        {
            this.removeActionListener(aL);
        }
    }

    /**
     * clears the button from any text and/or icons.
     *
     */
    public void clearCell()
    {
        this.setIcon(null);
        this.setText("");
    }


}
