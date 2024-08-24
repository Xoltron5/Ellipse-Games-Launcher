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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public abstract class MainPage extends Page implements Initializable {
    private static final String APP_VERSION = "V0.0.1";

    private static final String[] filterContent = {
        "All",
        "Adventure",
        "RPG",
        "Shooter",
        "Strategy",
        "Puzzle",
        "Racing",
        "Horror",
        "Sci-Fi"
    };

    @FXML
    private Label playerNameLabel;

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
    private Label errorlabel;

    @FXML
    private Button gamesButton;

    @FXML
    private Button storeButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Label logoutLabel;

    private ArrayList<EntityDetails> filteredEntityDetailsList;

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

        setFilteredEntityDetailsList(new ArrayList<>());
    }

    public void initialSetUp() {
        getPlayerNameLabel().setText(Player.getUsername());

        getAppVersionLabel().setText(getAppVersion());

        getNewsLabel().setText("Check the new Hot Deals! 🔥");

        // Sets up the filter content
        getFiltersComboBox().getItems().addAll(getFilterContent());

        // Attach the listener to the searchTextField
        getSearchTextField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                onSearchTextChanged(newValue);
            }
        });

        // Set the event handler for the label click
        getLogoutLabel().setOnMouseClicked(this::logout);
    }

    // Iterates through each entity details object held within a holder/container 
    // and adds the needed info to the image view.
    public void displayEntitys(ArrayList<EntityDetails> entityDetailsHolder,
    int fitWidth, int fitHeight, int cornerRadius, String typeOfEntity,
    MainPage currentPage) {
        // Iterate through all the entity details and add them to the TilePane
        for (EntityDetails entitysDetails : entityDetailsHolder) {
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
        }
    }

    public EntityView createView(long entityId, String path, String typeOfEntity,
    EntityDetails entityDetails, MainPage currentPage) {
        switch (typeOfEntity) {
            case "Game":
                GameDetails gameDetails = (GameDetails)entityDetails;
                GameView gameView = new GameView(entityId, path, gameDetails, currentPage);
                return gameView;
            case "Item":
                ItemDetails itemDetails = (ItemDetails)entityDetails;
                ItemView itemView = new ItemView(entityId, path, itemDetails, currentPage);
                return itemView;
            default:
                EntityView entityView = new EntityView(entityId, path, entityDetails, currentPage);
                return entityView;
        }
    }

    // Custom method that gets called every time the searchTextField is updated
    private void onSearchTextChanged(String newText) {
        // Trigger filtering whenever the search text changes
        filter(new ActionEvent());
    }

    public void logout(MouseEvent event) {
        try {
            Main.getPageManager().savePerviousPage(this);
            Main.getPageManager().navigateTo(new Logout());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void initialPageSetUp();

    public abstract void filter(ActionEvent event);

    public void gamesButtonClicked(ActionEvent e) throws IOException {
        Main.getPageManager().navigateTo(new GamePage());
    };

    public void storeButtonClicked(ActionEvent e) throws IOException { 
        Main.getPageManager().navigateTo(new StorePage());
    };

    public void inventoryButtonClicked(ActionEvent e) throws IOException {
        Main.getPageManager().navigateTo(new InventoryPage());
    };

    public static String getAppVersion() {
        return APP_VERSION;
    }

    public Label getAppVersionLabel() {
        return appVersionLabel;
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

    public ArrayList<EntityDetails> getFilteredEntityDetailsList() {
        return filteredEntityDetailsList;
    }

    public void setFilteredEntityDetailsList(ArrayList<EntityDetails> filteredEntityDetailsList) {
        this.filteredEntityDetailsList = filteredEntityDetailsList;
    }

    public static String[] getFilterContent() {
        return filterContent;
    }

    public Label getPlayerNameLabel() {
        return playerNameLabel;
    }

    public Label getErrorlabel() {
        return errorlabel;
    }

    public Label getLogoutLabel() {
        return logoutLabel;
    }
}
