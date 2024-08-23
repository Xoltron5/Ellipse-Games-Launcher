import java.util.ArrayList;

public abstract class EntityDetailsHolder {
    // This will be unique to each subclass, but not static
    private ArrayList<EntityDetails> entityDetailsHolder = new ArrayList<>();

    public ArrayList<EntityDetails> getEntityDetailsHolder() {
        return entityDetailsHolder;
    }
}