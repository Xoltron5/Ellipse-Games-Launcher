/*
    Author: Denis Bajgora
    Date: 1/9/2024
    
    Item Details class is used to hold all the item details objects that represent each item.
*/

import java.util.ArrayList;

public class ItemDetailsHolder extends EntityDetailsHolder {
    // This ArrayList will be shared among all instances of ItemDetailsHolder
    private static ArrayList<EntityDetails> itemDetailsHolder = new ArrayList<>();

    @Override
    public ArrayList<EntityDetails> getEntityDetailsHolder() {
        return itemDetailsHolder;
    }
}