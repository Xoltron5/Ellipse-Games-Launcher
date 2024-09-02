/* 
    Author: Denis Bajgora
    Date: 1/9/2024

    Inventory page class is the page that displays all the items that belong to the user. 
*/

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class InventoryPage extends MainPage {

    @FXML
    private ImageView itemView;

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label levelReqLabel;

    @FXML
    private Label costLabel;

    @FXML
    private Button actionButton;

    @FXML 
    private Label itemDescLabel;

    @FXML
    private Label currentLevelLabel;

    @FXML
    private Label currentCoinsLabel;

    public InventoryPage() {
        super("/assets/fxml/InventoryPage.fxml", "/assets/css/InventoryPage.css");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // sets up initial stuff content that's common between all main pages.
        initialSetUp();

        // sets up the game page content. 
        initialPageSetUp();
    }

    @Override
    protected void initialPageSetUp() {
        ItemDetailsHolder itemDetailsHolder = new ItemDetailsHolder();
        displayInventory(itemDetailsHolder.getEntityDetailsHolder(),
        100, 100, 0, "Item",
        this);
    }
    
    // Iterates through each entity details object held within a holder/container 
    // and adds the needed info to the image view.
    public void displayInventory(ArrayList<EntityDetails> entityDetailsHolder,
    int fitWidth, int fitHeight, int cornerRadius, String typeOfEntity,
    MainPage currentPage) {
        boolean found = false;
        // Iterate through all the entity details and add them to the TilePane
        for (EntityDetails entitysDetails : entityDetailsHolder) {
            // Iterate through all of the players items and check if it is found
            for (String playerItems : Player.getInventory()) {
                if (entitysDetails.getName().equals(playerItems)) found = true;
            }
            // if not found continue. 
            if (!found) continue;
            long entityId = entitysDetails.getId();
            String path = entitysDetails.getIconPath();
    
            EntityView entityView = createView(entityId, path, typeOfEntity,
            entitysDetails, currentPage); 

            // Set the GameView's fitWidth and fitHeight
            entityView.setFitWidth(fitWidth);
            entityView.setFitHeight(fitHeight);
            entityView.setPreserveRatio(true); // Preserve the image's aspect ratio
    
            // Create a rectangle with rounded corners
            Rectangle clip = new Rectangle(fitWidth, fitHeight);
            clip.setArcWidth(cornerRadius);
            clip.setArcHeight(cornerRadius);
            
            // Set the rectangle as the clip for the EntityView
            entityView.setClip(clip);

            entitysDetails.setEntityView(entityView);
    
            getFilteredEntityDetailsList().add(entitysDetails);
            if (entityView instanceof GameView) {
                GameView gameView = (GameView)entityView;
                getTilePane().getChildren().add(gameView);
            } else if (entityView instanceof ItemView) {
                ItemView itemView = (ItemView)entityView;
                getTilePane().getChildren().add(itemView);
            } else { // Add the entityView to the TilePane
                getTilePane().getChildren().add(entityView);
            }
            found = false; // reset found back to false. 
        }
    }

    @Override
    public void filter(ActionEvent event) {
        String filterItem = getFiltersComboBox().getValue(); // Ensure this is not null
        String searchText = getSearchTextField().getText();  // Get the text from the search field
    
        // Acquire the item details holder.
        ItemDetailsHolder itemDetailsHolderMain = new ItemDetailsHolder();
        ArrayList<EntityDetails> itemDetailsHolder = itemDetailsHolderMain.getEntityDetailsHolder();
        ArrayList<EntityDetails> tmpHolder = new ArrayList<>();

        for (EntityDetails entityDetails : itemDetailsHolder) {
            for (String playerItems : Player.getInventory()) {
                if (entityDetails.getName().equals(playerItems)) {
                    tmpHolder.add(entityDetails);
                }
            }
        }
        
        // Create an instance of Filter and pass the necessary parameters
        Filter filterTask = new Filter(filterItem, searchText, getTilePane(), tmpHolder);
    
        // Start a new thread with the Filter
        Thread filterThread = new Thread(filterTask);
    
        filterThread.setName("Filter Item Thread");
        filterThread.setDaemon(true);
        filterThread.start();
    
        // After filtering, set the filtered games to the filteredEntityDetailsList for searching
        setFilteredEntityDetailsList(filterTask.getFilteredEntityDetails());
    }
    
    public ImageView getItemView() {
        return itemView;
    }
    
    public void setItemView(ImageView itemView) {
        this.itemView = itemView;
    }
    
    public Label getItemNameLabel() {
        return itemNameLabel;
    }
    
    public void setItemNameLabel(Label itemNameLabel) {
        this.itemNameLabel = itemNameLabel;
    }
    
    public Label getLevelReqLabel() {
        return levelReqLabel;
    }
    
    public void setLevelReqLabel(Label levelReqLabel) {
        this.levelReqLabel = levelReqLabel;
    }
    
    public Label getCostLabel() {
        return costLabel;
    }
    
    public void setCostLabel(Label costLabel) {
        this.costLabel = costLabel;
    }
    
    public Button getActionButton() {
        return actionButton;
    }
    
    public void setActionButton(Button actionButton) {
        this.actionButton = actionButton;
    }
    
    public Label getItemDescLabel() {
        return itemDescLabel;
    }
    
    public void setItemDescLabel(Label itemDescLabel) {
        this.itemDescLabel = itemDescLabel;
    }
    
    public Label getCurrentLevelLabel() {
        return currentLevelLabel;
    }
    
    public void setCurrentLevelLabel(Label currentLevelLabel) {
        this.currentLevelLabel = currentLevelLabel;
    }
    
    public Label getCurrentCoinsLabel() {
        return currentCoinsLabel;
    }
    
    public void setCurrentCoinsLabel(Label currentCoinsLabel) {
        this.currentCoinsLabel = currentCoinsLabel;
    } 
}
