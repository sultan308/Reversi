import java.io.File;
/**
 * Checks if the files are game objets by using Firefighter capabilities
 *
 *
 * @author Ahmed Soltan .
 * @version 05.05.2021
 */
public class GameFileFilter extends javax.swing.filechooser.FileFilter  {

    @Override
    public boolean accept(File pathname) {

        return Game.isGame(pathname);
    }

    @Override
    public String getDescription() {
        return "Checks if its a game file";
    }
}
