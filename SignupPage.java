import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignupPage extends Page {
    final private String FXMLFILEPATH = "assets/fxml/SignupPage.fxml";
    final private String CSSFILEPATH = "assets/css/SignupPage.css";
    final private String CSS = this.getClass().getResource(getCSSFILEPATH()).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button loginHere;

    @FXML
    private Button signUp;

    @Override
    public void initializeScene(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(getFXMLFILEPATH()));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getCSS());
        primaryStage.show();
    }

    public void signUp(ActionEvent e) {
        System.out.println("signup");
    }

    public void loginHere(ActionEvent e) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.initializeScene(Main.getStage());
    }

    public String getFXMLFILEPATH() {
        return FXMLFILEPATH;
    }

    public String getCSSFILEPATH() {
        return CSSFILEPATH;
    }

    public String getCSS() {
        return CSS;
    }
}
