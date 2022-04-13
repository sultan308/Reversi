import javax.swing.*;
import java.io.File;
import java.io.IOException;
/**
 * Manges game files throughout a user friendly gui.
 *
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */
public class GameFilePicker
{

    private static JFileChooser filePicker = new JFileChooser(System.getProperty("user.dir"));

    /**
     * Allows the user to select a saved game file through a gui interface.
     *
     * @throws CorruptedGameFile if the selected game file has been corrupted.
     * @throws IOException if a problem occurred while trying to read the file.
     */
    public static Game getGame() throws CorruptedGameFile,IOException
    {
        filePicker.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Uses game filter to allow the selection of game files only.
        filePicker.setFileFilter(new GameFileFilter());

        int returnVal = filePicker.showOpenDialog(null);
        if(returnVal != JFileChooser.APPROVE_OPTION)
        {
            return null;
        }
        File selectedFile = filePicker.getSelectedFile();
        return Game.loadGame(selectedFile);
    }
    /**
     * Allows the user to select where to save a game file through a gui interface.
     *  @throws IOException if a problem occurred while trying to write to the file.
     */
    public static void saveGameToFile(Game game) throws IOException
    {
        filePicker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filePicker.resetChoosableFileFilters();
        int res = filePicker.showSaveDialog(null);

        if (res == JFileChooser.APPROVE_OPTION)

        {
            String fileToSaveToPath = filePicker.getSelectedFile().getAbsolutePath();
            game.saveGame(fileToSaveToPath);
        }
    }

}


