import java.util.ArrayList;

public abstract class ItemDetailsHolder {
    private static ArrayList<ItemsDetails> itemDetailsHolder = new ArrayList<>();

    public static ArrayList<ItemsDetails> getItemDetailsHolder() {
        return itemDetailsHolder;
    }
}

