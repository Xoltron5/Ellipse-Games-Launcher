import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    private String fxmlFilePath;
    private String cssFilePath;
    private String css;

    public GamesPage() {
        // Initialize file paths
        this.fxmlFilePath = "/assets/fxml/GamesPage.fxml";
        this.cssFilePath = "/assets/css/GamesPage.css";
        
        // Use the initialized paths
        this.css = this.getClass().getResource(cssFilePath).toExternalForm();

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
    
            // Add the GameView to the TilePane
            getTilePane().getChildren().add(gameView);
        }
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
}
