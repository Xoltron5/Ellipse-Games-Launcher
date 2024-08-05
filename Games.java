import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;

public class Games extends Page {
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

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane tilePane;

    @FXML
    public void initialize() {
        // Bind the TilePane width to the ScrollPane width
        tilePane.prefWidthProperty().bind(scrollPane.widthProperty());
    }
}
