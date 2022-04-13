public class CorruptedGameFile extends Exception{
    private String gameName;
    public CorruptedGameFile(String gameName)
    {
        this.gameName = gameName;

    }
    public String getGameName() {
        return gameName;
    }
}
