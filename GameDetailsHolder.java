/*
    Author: Denis Bajgora
    Date: 1/9/2024
    
    Game Details class is used to hold all the gamedetails objects that represent each game.
*/

import java.util.ArrayList;

public class GameDetailsHolder extends EntityDetailsHolder {
    // This ArrayList will be shared among all instances of GameDetailsHolder
    private static ArrayList<EntityDetails> gameDetailsHolder = new ArrayList<>();

    @Override
    public ArrayList<EntityDetails> getEntityDetailsHolder() {
        return gameDetailsHolder;
    }
}