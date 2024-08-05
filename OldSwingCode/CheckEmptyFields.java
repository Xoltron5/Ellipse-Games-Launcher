import java.util.Hashtable;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckEmptyFields {
    public static void checkEmptyFields(BooleanWrapper isInputValid, Hashtable<String, TextField> playerInputs,
    Hashtable<String, Label> incorrectLabels) {
        
        for (String key : playerInputs.keySet()) {
            // Checks if input field is empty. Also removes any white spaces entered by the user using trim() method
            if (playerInputs.get(key).getText().trim().isEmpty()) {
                // override the old incorrect label text.
                incorrectLabels.get(key).setText("Empty Field");
                isInputValid.setValue(false);
                continue;
            } 
            else {
                // clear the old incorrect label text. 
                incorrectLabels.get(key).setText("");
            } 
        }
    }
}
