import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private final String TITLE = "Eclipse Games";
    private final Image ICON = new Image("assets/images/");
    private static Stage stage; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.setStage(primaryStage);
        stage.setTitle(getTITLE());
        stage.getIcons().add(getICON());
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.initializeScene(Main.getStage());
    }
    
    public static void main(String[] args) {
        //System.out.println("Hello World");
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public Image getICON() {
        return ICON;
    }

    public String getTITLE() {
        return TITLE;
    }
}