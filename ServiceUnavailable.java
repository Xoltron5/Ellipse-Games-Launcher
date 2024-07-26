import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ServiceUnavailable extends Page {

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Label errorCodeLabel;

    @FXML
    private Label errorDescriptionLabel;

    @FXML
    private Button previousPageButton;

    private String errorCodeText;
    private String errorDescriptionText;

    private String fxmlFilePath = "/assets/fxml/ServiceUnavailable.fxml";
    private String cssFilePath = "/assets/css/ServiceUnavailable.css";
    private String css = this.getClass().getResource(getCSSFilePath()).toExternalForm();

    public ServiceUnavailable() {
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }
 
    public void goBack(ActionEvent e) throws IOException {
        Main.getPageManager().navigateBack();
    }   

    // Getters & Setters 
    @Override
    public String getFXMLFilePath() {
        return fxmlFilePath;
    }

    @Override
    public String getCSSFilePath() {
        return cssFilePath;
    }

    @Override
    public String getCSS() {
        return css;
    }

    public Button getPreviousPageButton() {
        return previousPageButton;
    }

    public void setPreviousPageButton(Button previousPageButton) {
        this.previousPageButton = previousPageButton;
    }

    public Label getErrorCodeLabel() {
        return errorCodeLabel;
    }

    public void setErrorCodeLabel(Label errorCodeLabel) {
        this.errorCodeLabel = errorCodeLabel;
    }
    
    public Label getErrorDescriptionLabel() {
        return errorDescriptionLabel;
    }

    public void setErrorDescriptionLabel(Label errorDescriptionLabel) {
        this.errorDescriptionLabel = errorDescriptionLabel;
    }

    public String getErrorCodeText() {
        return errorCodeText;
    }

    public void setErrorCodeText(String errorCodeText) {
        this.errorCodeText = errorCodeText;
    }

    public String getErrorDescriptionText() {
        return errorDescriptionText;
    }

    public void setErrorDescriptionText(String errorDescriptionText) {
        this.errorDescriptionText = errorDescriptionText;
    }
}
