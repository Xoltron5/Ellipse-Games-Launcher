/* 
    Author: Denis Bajgora
    Date: 1/9/2024

    Signup class is used to signup the player by taking the player's data and sending it to
    the signup contr class to check their data and see if it is valid and if it is it will navigate
    them to the main page else it will display a error message. 
*/

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Signup extends Page implements Initializable {

    private String fxmlFilePath = "/assets/fxml/Signup.fxml";
    private String cssFilePath = "/assets/css/Signup.css";
    private String css = this.getClass().getResource(getCSSFilePath()).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button loginHere;

    @FXML
    private Button signUp;

    @FXML
    private TextField username; 

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Label incorrectUsername;

    @FXML
    private Label incorrectEmailAddress;

    @FXML
    private Label incorrectDateOfBirth;

    @FXML
    private Label incorrectPassword;

    @FXML
    private Label incorrectConfirmPassword;

    private HashMap<String, TextField> playerInputs;
    private HashMap<String, Label> incorrectLabels;

    public Signup() {
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }

    // When signup info is submitted we check if it is valid and then connect to the database. 
    public void signUp(ActionEvent event) throws IOException {
        if (InputValidUtils.validateInputs(playerInputs, incorrectLabels, true)) {
            System.out.println("db connection");
            SignupContr.signUpPlayer(event, playerInputs, incorrectLabels);
        }
    }

    // We change the scene to the login page. 
    public void loginHere(ActionEvent event) throws IOException {
        Main.getPageManager().navigateTo(new Login());
    }

    // A Hash Map with all the input fields in the signup page.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerInputs = new HashMap<String, TextField>() {{
            put("username", username);
            put("email", emailAddress);
            put("dateOfBirth", dateOfBirth);
            put("password", password);
            put("confirmPassword", confirmPassword);
        }};

        // A Hash Map with all the error labels that correspond to a input field in the signup page.
        incorrectLabels = new HashMap<String, Label>() {{
            put("username", incorrectUsername);
            put("email", incorrectEmailAddress);
            put("dateOfBirth", incorrectDateOfBirth);
            put("password", incorrectPassword);
            put("confirmPassword", incorrectConfirmPassword);
        }};
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
}
