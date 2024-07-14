import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomePage extends Page {
    final private String FXMLFILEPATH = "assets/fxml/WelcomePage.fxml";
    final private String CSSFILEPATH = "assets/css/WelcomePage.css";
    final private String CSS = this.getClass().getResource(getCSSFILEPATH()).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button logIntoAccount;

    @FXML
    private Button createAnAccount; 

    @Override
    public void initializeScene(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(getFXMLFILEPATH()));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getCSS());
        primaryStage.show();
    }

    public void createAnAccount(ActionEvent e) throws IOException { 
        SignupPage signupPage = new SignupPage();
        signupPage.initializeScene(Main.getStage());
    }

    public void logIntoAccount(ActionEvent e) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.initializeScene(Main.getStage());
    }

    public void getCreateAnAccount(ActionEvent e) throws IOException {
        SignupPage signupPage = new SignupPage();
        signupPage.initializeScene(Main.getStage());
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
