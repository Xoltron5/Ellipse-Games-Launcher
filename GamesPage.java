import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

        // Displays the player's username.
        getUsernameLabel().setText(Player.getUsername());

        // Displays the player's coins.
        getCoinsAmountLabel().setText(currentCoinsText + Long.toString(Player.getCoins()));

        // Displays the latest news.
        getNewsLabel().setText("Check the new Hot Deals! 🔥");

        // Calculates the player's current level based on their xp.
        int playersLevel = Player.determineLevel(Player.getXp());

        // Displays the player's current level.
        getLevelLabel().setText(currentLevelText + playersLevel);
        
        // Calculates the player's next level based on their xp.
        long nextLevelXP = Player.nextLevelXP(Player.getXp());
        int nextLevel = playersLevel + 1;

        // Displays the player's xp needed for the next level.
        getNextLevelXPLabel().setText(nextLevelXP + " more xp to Level " + nextLevel);

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
