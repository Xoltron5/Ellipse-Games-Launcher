import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomePage {

    final private String FXMLFILEPATH = "Welcome.fxml";
    final private String CSSFILEPATH = "assets/css/WelcomePage.css";
    final private String CSS = this.getClass().getResource(CSSFILEPATH).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button logIntoAccount;

    @FXML
    private Button createAnAccount; 

    public void initializeStage(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(FXMLFILEPATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(CSS);
        primaryStage.show();
    }

    public void createAnAccount(ActionEvent e) { 
        System.out.println("n");
    }

    public void logIntoAccount(ActionEvent e) {
        System.out.println("p");
    }

    public Button getCreateAnAccount() {
        return createAnAccount;
    }

    public void setCreateAnAccount(Button createAnAccount) {
        this.createAnAccount = createAnAccount;
    }

    public Button getLogIntoAccount() {
        return logIntoAccount;
    }

    public void setLogIntoAccount(Button logIntoAccount) {
        this.logIntoAccount = logIntoAccount;
    }
}
