import java.util.ArrayList;

public class GameDetailsHolder {
    private static ArrayList<GameDetails> gameDetailsHolder = new ArrayList<>();

    public static ArrayList<GameDetails> getGameDetailsHolder() {
        return gameDetailsHolder;
    }
}
