import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ItemView extends ImageView {
    Image image; 

    public ItemView(String filePath) {
        image = new Image(filePath);
        this.setImage(image);
    }
}
