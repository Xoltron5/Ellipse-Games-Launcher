import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomePage {

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button logIntoAccount;

    @FXML
    private Button createAnAccount; 

    String css = this.getClass().getResource("WelcomePage.css").toExternalForm();

    public void initializeStage(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Eclipse Games");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(css);
        primaryStage.show();
    }
}
