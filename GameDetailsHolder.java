import java.util.ArrayList;

public class GameDetailsHolder extends EntityDetailsHolder {
    // This ArrayList will be shared among all instances of GameDetailsHolder
    private static ArrayList<EntityDetails> gameDetailsHolder = new ArrayList<>();

    @Override
    public ArrayList<EntityDetails> getEntityDetailsHolder() {
        return gameDetailsHolder;
    }
}