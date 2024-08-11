import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;

public abstract class MainPage extends Page implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane tilePane;

    private static final String APP_VERSION = "V0.0.1";
    
    @FXML
    private Label appVersionLabel;

    @FXML
    private Label coinsAmountLabel;
    
    public static String getAppVersion() {
        return APP_VERSION;
    }

    public Label getAppVersionLabel() {
        return appVersionLabel;
    }

    public Label getCoinsAmountLabel() {
        return coinsAmountLabel;
    }
}
