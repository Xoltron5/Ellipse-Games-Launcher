import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomePage {

    @FXML 
    private BorderPane mainPane;

    public WelcomePage(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        primaryStage.setTitle("Eclipse Games");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
