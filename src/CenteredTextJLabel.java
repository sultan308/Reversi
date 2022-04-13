import javax.swing.*;
/**
 * CenteredTextJLabel is a subclass of J label that centers the text to the middle
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */
public class CenteredTextJLabel extends JLabel
{
    /**
     * Creates a label with the given text in the center.
     *
     * @param labelText the label content
     */
    public CenteredTextJLabel(String labelText)
    {
        super(labelText,SwingConstants.CENTER);
    }
}
