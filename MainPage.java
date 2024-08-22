import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public abstract class MainPage extends Page implements Initializable {
    private static final String APP_VERSION = "V0.0.1";

    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label levelLabel;

    @FXML
    private Label nextLevelXPLabel;

    @FXML
    private Label newsLabel;

    @FXML
    private ComboBox<String> filtersComboBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane tilePane;

    @FXML
    private TextField searchTextField;
    
    @FXML
    private Label appVersionLabel;

    @FXML
    private Label coinsAmountLabel;

    @FXML
    private Button gamesButton;

    @FXML
    private Button storeButton;

    @FXML
    private Button inventoryButton;

    private ArrayList<ItemsDetails> filteredItemDetailsList;

    public MainPage(String fxmlFilePath, String cssFilePath) {
        // Initialize file paths
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);

        // Use the initialized paths
        setCSS(this.getClass().getResource(getCSSFilePath()).toExternalForm());

        // Set the paths using the superclass methods
        setFXMLFilePath(getFXMLFilePath());
        setCSSFilePath(getCSSFilePath());
        setCSS(getCSS());

        setFilteredItemDetailsList(new ArrayList<>());
    }

    public void initialSetUp() {
        
        getAppVersionLabel().setText(getAppVersion());

        getNewsLabel().setText("Check the new Hot Deals! 🔥");

        String currentCoinsText = getCoinsAmountLabel().getText();

        // Sets the player's coins.
        getCoinsAmountLabel().setText(currentCoinsText + Long.toString(Player.getCoins()));

        // Attach the listener to the searchTextField
        getSearchTextField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                onSearchTextChanged(newValue);
            }
        });
    }

    // Iterates through each item details object held within a holder/container 
    // and adds the needed info to the image view.
    public void displayItems(ArrayList<ItemsDetails> itemDetailsHolder) {
        // Set desired dimensions for the GameView
        int fitWidth = 150; // Adjust the width as needed
        int fitHeight = 150; // Adjust the height as needed
        int cornerRadius = 20; // Radius for the rounded corners
        
        // Iterate through all the game details and add them to the TilePane
        for (ItemsDetails itemsDetails : itemDetailsHolder) {
            long itemId = itemsDetails.getId();
            String path = itemsDetails.getIconPath();
            ItemView itemView = new ItemView(itemId, path);
    
            // Set the GameView's fitWidth and fitHeight
            itemView.setFitWidth(fitWidth);
            itemView.setFitHeight(fitHeight);
            itemView.setPreserveRatio(true); // Preserve the image's aspect ratio
    
            // Create a rectangle with rounded corners
            Rectangle clip = new Rectangle(fitWidth, fitHeight);
            clip.setArcWidth(cornerRadius);
            clip.setArcHeight(cornerRadius);
            
            // Set the rectangle as the clip for the GameView
            itemView.setClip(clip);

            itemsDetails.setItemView(itemView);
    
            // Add the itemView to the TilePane
            getFilteredItemDetailsList().add(itemsDetails);
            getTilePane().getChildren().add(itemView);
        }
    }

    // Custom method that gets called every time the searchTextField is updated
    private void onSearchTextChanged(String newText) {
        // Trigger filtering whenever the search text changes
        filter(new ActionEvent());
    }

    protected abstract void initialPageSetUp();

    public abstract void filter(ActionEvent event);

    public abstract void gamesButtonClicked(ActionEvent e) throws IOException;

    public abstract void storeButtonClicked(ActionEvent e) throws IOException;

    public abstract void inventoryButtonClicked(ActionEvent e) throws IOException;
    
    public static String getAppVersion() {
        return APP_VERSION;
    }

    public Label getAppVersionLabel() {
        return appVersionLabel;
    }

    public Label getCoinsAmountLabel() {
        return coinsAmountLabel;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public Button getGamesButton() {
        return gamesButton;
    }

    public Button getStoreButton() {
        return storeButton;
    }

    public Button getInventoryButton() {
        return inventoryButton;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public Label getNextLevelXPLabel() {
        return nextLevelXPLabel;
    }

    public Label getNewsLabel() {
        return newsLabel;
    }

    public ComboBox<String> getFiltersComboBox() {
        return filtersComboBox;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

    public ArrayList<ItemsDetails> getFilteredItemDetailsList() {
        return filteredItemDetailsList;
    }

    public void setFilteredItemDetailsList(ArrayList<ItemsDetails> filteredItemDetailsList) {
        this.filteredItemDetailsList = filteredItemDetailsList;
    }
}
