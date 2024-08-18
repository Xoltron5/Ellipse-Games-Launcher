import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class GamesPage extends MainPage {
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
    private TextField searchTextField;

    private String fxmlFilePath;

    private String cssFilePath;

    private String css;

    private ArrayList<GameDetails> filteredGameDetailsList;

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
    
    public GamesPage() {
        // Initialize file paths
        this.fxmlFilePath = "/assets/fxml/GamesPage.fxml";
        this.cssFilePath = "/assets/css/GamesPage.css";
        
        // Use the initialized paths
        this.css = this.getClass().getResource(cssFilePath).toExternalForm();

        this.filteredGameDetailsList = new ArrayList<>();

        // Set the paths using the superclass methods
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAppVersionLabel().setText(getAppVersion());

        String currentCoinsText = getCoinsAmountLabel().getText();

        String currentLevelText = getLevelLabel().getText();

        // Sets the player's username.
        getUsernameLabel().setText(Player.getUsername());

        // Sets the player's coins.
        getCoinsAmountLabel().setText(currentCoinsText + Long.toString(Player.getCoins()));

        // Sets the latest news.
        getNewsLabel().setText("Check the new Hot Deals! 🔥");

        // Calculates the player's current level based on their xp.
        int playersLevel = Player.determineLevel(Player.getXp());

        // Sets the player's current level.
        getLevelLabel().setText(currentLevelText + playersLevel);
        
        // Calculates the player's next level based on their xp.
        long nextLevelXP = Player.nextLevelXP(Player.getXp());
        int nextLevel = playersLevel + 1;

        // Sets the player's xp needed for the next level.
        getNextLevelXPLabel().setText(nextLevelXP + " more xp to Level " + nextLevel);

        // Sets up the filter content
        getFiltersComboBox().getItems().addAll(getFilterContent());

        // Attach the listener to the searchTextField
        searchTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                onSearchTextChanged(newValue);
            }
        });

        displayGames();
    }

    // Iterates through each game details object held within a holder/container 
    // and adds the needed info to the image view.
    public void displayGames() {
        // Set desired dimensions for the GameView
        int fitWidth = 150; // Adjust the width as needed
        int fitHeight = 150; // Adjust the height as needed
        int cornerRadius = 20; // Radius for the rounded corners
        
        // Iterate through all the game details and add them to the TilePane
        for (GameDetails gameDetails : GameDetailsHolder.getGameDetailsHolder()) {
            long gameId = gameDetails.getId();
            String path = gameDetails.getIconPath();
            GameView gameView = new GameView(gameId, path);
    
            // Set the GameView's fitWidth and fitHeight
            gameView.setFitWidth(fitWidth);
            gameView.setFitHeight(fitHeight);
            gameView.setPreserveRatio(true); // Preserve the image's aspect ratio
    
            // Create a rectangle with rounded corners
            Rectangle clip = new Rectangle(fitWidth, fitHeight);
            clip.setArcWidth(cornerRadius);
            clip.setArcHeight(cornerRadius);
            
            // Set the rectangle as the clip for the GameView
            gameView.setClip(clip);

            gameDetails.setGameView(gameView);
    
            // Add the GameView to the TilePane
            filteredGameDetailsList.add(gameDetails);
            getTilePane().getChildren().add(gameView);
        }
    }
    public void filter(ActionEvent event) {
        String filterItem = getFiltersComboBox().getValue(); // Ensure this is not null
        String searchText = searchTextField.getText();  // Get the text from the search field
    
        // Acquire the game details holder.
        ArrayList<GameDetails> gameDetailsHolder = GameDetailsHolder.getGameDetailsHolder();
    
        // Create an instance of Filter and pass the necessary parameters
        Filter filterTask = new Filter(filterItem, searchText, getTilePane(), gameDetailsHolder);
    
        // Start a new thread with the Filter
        Thread filterThread = new Thread(filterTask);
    
        filterThread.setName("Filter Game Thread");
        filterThread.setDaemon(true);
        filterThread.start();
    
        // After filtering, set the filtered games to the filteredGameDetailsList for searching
        this.filteredGameDetailsList = filterTask.getFilteredGameDetails();
    }    
    
    // Custom method that gets called every time the searchTextField is updated
    private void onSearchTextChanged(String newText) {
        // Trigger filtering whenever the search text changes
        filter(new ActionEvent());
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

    public static String[] getFilterContent() {
        return filterContent;
    }
}
