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

public class Login extends Page implements Initializable {

    private String fxmlFilePath = "/assets/fxml/Login.fxml";
    private String cssFilePath = "/assets/css/Login.css";
    private String css = this.getClass().getResource(getCSSFilePath()).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @FXML
    private Button signUpHere;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password; 

    @FXML 
    private Label incorrectUsername; 

    @FXML
    private Label incorrectPassword;

    private HashMap<String, TextField> playerInputsField;
    private HashMap<String, Label> incorrectLabels;

    public Login() {
        setFXMLFilePath(fxmlFilePath);
        setCSSFilePath(cssFilePath);
        setCSS(css);
    }

    // Validates their input, connects to the database and attempts to log the user in. 
    public void login(ActionEvent event) throws IOException {
        // We validate the input before connecting to the database.
        if (InputValidUtils.validateInputs(playerInputsField, incorrectLabels, false)) {
            System.out.println("db connection");
            // Connect to the database and attempt to log the player in. 
            DBUtils.logInPlayer(event, playerInputsField, incorrectLabels);
        }
    }

    // Navigates the user to the signup page
    public void signUpHere(ActionEvent event) throws IOException {
        Main.getPageManager().navigateTo(new Signup());
    }

    // a Hash table with all the input fields in the login page.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerInputsField = new HashMap<String, TextField>() {{
            put("username", username);
            put("password", password);
        }};

        // a Hash table with all the error labels that correspond to an input field in the login page.
        incorrectLabels = new HashMap<String, Label>() {{
            put("username", incorrectUsername);
            put("password", incorrectPassword);
        }};
    }

    // Getter for getting the FXML File Path. 
    @Override
    public String getFXMLFilePath() {
        return fxmlFilePath;
    }

    // Getter for getting the CSS File Path. 
    @Override
    public String getCSSFilePath() {
        return cssFilePath;
    }

    // Getter for getting the CSS FILE. 
    @Override
    public String getCSS() {
        return css;
    }
}
