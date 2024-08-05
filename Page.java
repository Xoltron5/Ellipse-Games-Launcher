import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Page {

    private String fxmlFilePath;
    private String cssFilePath;
    private String css;

    Scene scene;
    Parent root;

    public FXMLLoader initializeScene(Stage primaryStage) throws IOException {
        if (getFXMLFilePath() == null) {
            throw new IllegalStateException("FXML file path cannot be null");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getFXMLFilePath()));
        root = loader.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        if (getCSS() != null) {
            scene.getStylesheets().add(getCSS());
        }
        primaryStage.show();

        return loader;
    }

    // Getters & Setters 
    public String getFXMLFilePath() {
        return fxmlFilePath;
    }

    public void setFXMLFilePath(String fxmlFilePath) {
        this.fxmlFilePath = fxmlFilePath;
    }

    public String getCSSFilePath() {
        return cssFilePath;
    }

    public void setCSSFilePath(String cssFilePath) {
        this.cssFilePath = cssFilePath;
    }

    public String getCSS() {
        return css;
    }

    public void setCSS(String css) {
        this.css = css;
    }
}
