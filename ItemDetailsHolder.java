import java.util.ArrayList;

public class ItemDetailsHolder extends EntityDetailsHolder {
    // This ArrayList will be shared among all instances of ItemDetailsHolder
    private static ArrayList<EntityDetails> itemDetailsHolder = new ArrayList<>();

    @Override
    public ArrayList<EntityDetails> getEntityDetailsHolder() {
        return itemDetailsHolder;
    }
}