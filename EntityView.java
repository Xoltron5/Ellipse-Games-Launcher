import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView extends ImageView {
    long id; // uses this to access the entity via it's unqiue id.
    Image image; 

    public EntityView(long id, String filePath) {
        image = new Image(filePath);
        this.setImage(image);
        this.id = id;
    }
}
