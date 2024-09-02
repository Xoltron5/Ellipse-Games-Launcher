/*
    Author: Denis Bajgora
    Date: 1/9/2024
    
    Entity Details Holder class is used to hold entity detail objects in an
    arraylist. 
*/

import java.util.ArrayList;

public abstract class EntityDetailsHolder {
    // This will be unique to each subclass, but not static
    private ArrayList<EntityDetails> entityDetailsHolder = new ArrayList<>();

    public ArrayList<EntityDetails> getEntityDetailsHolder() {
        return entityDetailsHolder;
    }
}