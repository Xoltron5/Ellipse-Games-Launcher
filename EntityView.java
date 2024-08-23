import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EntityView extends ImageView {
    long id; // uses this to access the entity via its unique id.
    private Image image;
    private EntityDetails entityDetails; // The entityDetails object this image view is part of. 

    public EntityView(long id, String filePath,
    EntityDetails entityDetails) {
        image = new Image(filePath);
        this.setImage(image);
        this.id = id;
        setEntityDetails(entityDetails);
        // Add a click event handler
        this.setOnMouseClicked(this::handleClick);
    }

    // Method to handle the click event
    public void handleClick(MouseEvent event) {
        System.out.println("Hello");
    }

    public void setId(long id) {
        this.id = id;
    }

    public EntityDetails getEntityDetails() {
        return entityDetails;
    }

    public void setEntityDetails(EntityDetails entityDetails) {
        this.entityDetails = entityDetails;
    }
}
