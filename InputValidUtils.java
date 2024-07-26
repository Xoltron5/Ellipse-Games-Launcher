import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputValidUtils {

    final static int MIN_USERNAME_LENGTH = 1;
    final static int MAX_USERNAME_LENGTH = 20;

    final static int MIN_PASSWORD_LENGTH = 1;
    final static int MAX_PASSWORD_LENGTH = 200;

    // Error Messages
    private static final String INVALID_EMAIL_MESSAGE = "Invalid Email";
    private static final String INVALID_DATE_MESSAGE = "Invalid Date Of Birth";
    private static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match!";
    private static final String EMPTY_FIELDS_MESSAGE = "Empty Fields";
    private static final String USERNAME_LENGTH_MESSAGE = "Usernames must be %d to %d characters long.";
    private static final String PASSWORD_LENGTH_MESSAGE = "Passwords must be %d to %d characters long.";

    public static boolean validateInputs(HashMap<String, TextField> playerInputs,
    HashMap<String, Label> incorrectLabels, boolean isSignUp) {

        // We use this to ensure the boolean value is changed by making it a reference data type. 
        BooleanWrapper isInputValid = new BooleanWrapper(true); // We assume input is valid at start.
        
        // Clear old output.
        clearlabelInfo(incorrectLabels);

        // we run these validations for signup and login. 
        validateUsernameLength(isInputValid, playerInputs, incorrectLabels);

        validatePasswordLength(isInputValid, playerInputs, incorrectLabels);

        // If we are signing up the user run these validations.
        if (isSignUp) {
            validateEmail(isInputValid, playerInputs, incorrectLabels);

            validateDate(isInputValid, playerInputs, incorrectLabels);
    
            validatePasswordsMatch(isInputValid, playerInputs, incorrectLabels);
        }

        // Returns the final result. (false = validation failed, true = validation passed)
        return isInputValid.getValue();
    }
 
    // This method just clears the label text. 
    public static void clearlabelInfo(HashMap<String, Label> labels) {
        for (String key : labels.keySet()) {
            labels.get(key).setText("");
        }
    }

    public static void validateUsernameLength(BooleanWrapper isInputValid, HashMap<String, TextField> playerInputs,
    HashMap<String, Label> incorrectLabels) {

        Label incorrectLabel = incorrectLabels.get("username");
        int usernameLength = playerInputs.get("username").getText().length();

        // Checks if the username length is the appropriate size. 
        if (usernameLength < MIN_USERNAME_LENGTH || usernameLength > MAX_USERNAME_LENGTH) {

            isInputValid.setValue(false);

            incorrectLabel.setText(String.format(USERNAME_LENGTH_MESSAGE, MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH));

            return;
        }
    }

    public static void validateEmail(BooleanWrapper isInputValid, HashMap<String, TextField> playerInputs,
    HashMap<String, Label> incorrectLabels) {

        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        // Compile the regex pattern.
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        // Match the email against the regex pattern.
        Matcher matcher = pattern.matcher(playerInputs.get("email").getText());

        // Return whether the email matches the regex pattern.
        if (!matcher.matches()) {

            isInputValid.setValue(false);

            incorrectLabels.get("email").setText(INVALID_EMAIL_MESSAGE);

            return;
        }
    }

    public static void validateDate(BooleanWrapper isInputValid, HashMap<String, TextField> playerInputs,
    HashMap<String, Label> incorrectLabels) {
        // Regular expression to match the date format DD/MM/YYYY.
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        String date = playerInputs.get("dateOfBirth").getText();

        // Check if the date matches the regex pattern.
        if (!date.matches(regex)) {
            isInputValid.setValue(false);
            incorrectLabels.get("dateOfBirth").setText(INVALID_DATE_MESSAGE);
            return;
        }

        // Try to parse the date using SimpleDateFormat.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // To ensure strict parsing

        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            isInputValid.setValue(false);
            incorrectLabels.get("dateOfBirth").setText(INVALID_DATE_MESSAGE);
        }
    }

    public static void validatePasswordsMatch(BooleanWrapper isInputValid, HashMap<String, TextField> playerInputs,
    HashMap<String, Label> incorrectLabels) {
        String password = playerInputs.get("password").getText();
        String confirmPassword = playerInputs.get("confirmPassword").getText();

        // Checks if the passwords are empty.
        if (password.isEmpty() && confirmPassword.isEmpty()) {

            isInputValid.setValue(false);

            incorrectLabels.get("password").setText(EMPTY_FIELDS_MESSAGE);
            incorrectLabels.get("confirmPassword").setText(EMPTY_FIELDS_MESSAGE);

            return;
        }

        // Checks if passwords don't match.
        if (!password.equals(confirmPassword)) {

            isInputValid.setValue(false);

            incorrectLabels.get("confirmPassword").setText(PASSWORDS_DO_NOT_MATCH);

            return;
        }   
    }

    public static void validatePasswordLength(BooleanWrapper isInputValid, HashMap<String, TextField> playerInputs,
    HashMap<String, Label> incorrectLabels) {

        Label incorrectLabel = incorrectLabels.get("password");
        int passwordLength = playerInputs.get("password").getText().length();

        // checks if the username length is the appropriate size. 
        if (passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH) {

            isInputValid.setValue(false);

            incorrectLabel.setText(String.format(PASSWORD_LENGTH_MESSAGE, MIN_PASSWORD_LENGTH, MAX_USERNAME_LENGTH));

            return;
        }
    }
}
