import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ItemView extends ImageView {
    long id; // uses this to access the item via it's unqiue id.
    Image image; 

    public ItemView(long id, String filePath) {
        image = new Image(filePath);
        this.setImage(image);
        this.id = id;
    }
}
