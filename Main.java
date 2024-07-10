import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.initializeStage(primaryStage);
        
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }
    
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello World");
    }
}