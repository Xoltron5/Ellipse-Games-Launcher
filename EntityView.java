import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EntityView extends ImageView {
    long id; // uses this to access the entity via its unique id.
    Image image;

    public EntityView(long id, String filePath) {
        image = new Image(filePath);
        this.setImage(image);
        this.id = id;

        // Add a click event handler
        this.setOnMouseClicked(this::handleClick);
    }

    // Method to handle the click event
    public void handleClick(MouseEvent event) {
        System.out.println("Hello");
    }
}
