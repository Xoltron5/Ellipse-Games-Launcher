import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;

public class Games extends Page implements Initializable{

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane tilePane;

    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label levelLabel;

    @FXML
    private Label moreXpLabel;

    @FXML
    private Label coinsAmountLabel;

    private String fxmlFilePath;
    private String cssFilePath;
    private String css;

    public Games() {
        // Initialize file paths
        this.fxmlFilePath = "/assets/fxml/Game.fxml";
        this.cssFilePath = "/assets/css/Game.css";
        
        // Use the initialized paths
        this.css = this.getClass().getResource(cssFilePath).toExternalForm();

        // Set the paths using the superclass methods
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        String currentCoinsText = getCoinsAmountLabel().getText();

        getUsernameLabel().setText(Player.username);
        getCoinsAmountLabel().setText(currentCoinsText + Long.toString(Player.coins));
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public Label getCoinsAmountLabel() {
        return coinsAmountLabel;
    }

    public Label getMoreXpLabel() {
        return moreXpLabel;
    }
}
