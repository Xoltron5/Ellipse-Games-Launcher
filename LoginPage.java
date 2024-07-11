import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginPage extends Page {
    final private String FXMLFILEPATH = "assets/fxml/LoginPage.fxml";
    final private String CSSFILEPATH = "assets/css/LoginPage.css";
    final private String CSS = this.getClass().getResource(getCSSFILEPATH()).toExternalForm();

    @FXML 
    private BorderPane mainPane;

    @Override
    public void initializeScene(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(getFXMLFILEPATH()));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getCSS());
        primaryStage.show();
    }

    public void createAnAccount(ActionEvent e) { 
        System.out.println("NOOB");
    }

    public void logIntoAccount(ActionEvent e) {
        System.out.println("YUP");
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
