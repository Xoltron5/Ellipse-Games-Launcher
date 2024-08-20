import java.util.ArrayList;

public abstract class ItemsDetails {
    private long id;
    private String name;
    private String iconPath; 
    private ArrayList<String> genresContainer = new ArrayList<>();

    private ItemView itemView;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getGenresContainer() {
        return genresContainer;
    }

    public ItemView getItemView() {
        return itemView;
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
