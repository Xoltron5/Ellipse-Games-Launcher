import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private final String TITLE = "Eclipse Games";
    private final Image ICON = new Image("assets/images/");
    private static Stage stage;
    private static Page currentPage;
    private static PageManager pageManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.setStage(primaryStage);
        stage.setTitle(getTITLE());
        stage.getIcons().add(getICON());
        pageManager = new PageManager(primaryStage);
        DBUtils.loadDatabaseCredentials();

        Welcome welcomePage = new Welcome();
        pageManager.navigateTo(welcomePage);

    }

    public static void main(String[] args) {
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

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(Page currentPage) {
        Main.currentPage = currentPage;
    }

    public static PageManager getPageManager() {
        return pageManager;
    }
}
