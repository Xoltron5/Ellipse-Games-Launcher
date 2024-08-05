
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Welcome extends Page {
    private String fxmlFilePath = "/assets/fxml/Welcome.fxml";
    private String cssFilePath = "/assets/css/Welcome.css";
    private String css = this.getClass().getResource(getCSSFilePath()).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button logIntoAccount;

    @FXML
    private Button createAnAccount; 

    @FXML
    private Label errorMessageLabel;
    
    public Welcome() {
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }
    
    // Event handlers
    public void createAnAccount(ActionEvent e) throws IOException { 
        Main.getPageManager().navigateTo(new Signup());
    }

    public void logIntoAccount(ActionEvent e) throws IOException {
        Main.getPageManager().navigateTo(new Login());
    }

    public void displayErrorMessage(String errorMessage) {
        getErrorMessageLabel().setText(errorMessage);
    }

    // Getters & Setters 
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

    public Label getErrorMessageLabel() {
        return errorMessageLabel;
    }

    public void setErrorMessageLabel(Label errorMessageLabel) {
        this.errorMessageLabel = errorMessageLabel;
    }
    
    public String getFXMLFilePath() {
        return fxmlFilePath;
    }

    public String getCSSFilePath() {
        return cssFilePath;
    }

    public String getCSS() {
        return css;
    }
}
