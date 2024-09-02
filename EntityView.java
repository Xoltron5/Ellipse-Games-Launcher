/*
    Author: Denis Bajgora
    Date: 1/9/2024
    
    Entity View is the image portion of the Entity object that is displayed on a page. 
*/

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EntityView extends ImageView {
    long id; // uses this to access the entity via its unique id.
    private Image image;
    private EntityDetails entityDetails; // The entityDetails object this image view is part of. 
    private MainPage currentPage; // The page this entity view is part of. 

    public EntityView(long id, String filePath,
    EntityDetails entityDetails, MainPage currentPage) {
        image = new Image(filePath);
        this.setImage(image);
        this.id = id;
        setEntityDetails(entityDetails);
        setCurrentPage(currentPage);
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

    public MainPage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(MainPage currentPage) {
        this.currentPage = currentPage;
    }
}
