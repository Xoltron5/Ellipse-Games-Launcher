import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private final String TITLE = "Eclipse Games";
    private final Image ICON = new Image("assets/images/");

    private static Stage stage; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(ICON);
        
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.initializeStage(primaryStage);
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }
    
    public static void main(String[] args) {
        //System.out.println("Hello World");
        launch(args);
    }
}