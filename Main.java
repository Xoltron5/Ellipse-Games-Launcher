/* 
    Author: Denis Bajgora
    Date: 1/9/2024

    This is the main class responsible for setting up the intial window loading the database data 
    such as the player and game data. Loading the welcome page. 
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        // Set the primary stage in the Main class
        Main.setStage(primaryStage);
    
        // Set the title and icon of the stage
        stage.setTitle(getTITLE());
        stage.getIcons().add(getICON());
    
        // Initialize the PageManager with the primary stage
        pageManager = new PageManager(primaryStage);
    
        // Load database credentials and game/item details
        DBUtils.loadDatabaseCredentials();
        GameDetailsContr.loadGameDetails();
        ItemDetailsContr.loadItemDetails();
    
        // Navigate to the welcome page
        Welcome welcomePage = new Welcome();
        FXMLLoader loader = pageManager.navigateTo(welcomePage);
        welcomePage = loader.getController();
    
        // Ensure the stage is centered on the screen
        stage.centerOnScreen();
    
        // Show the primary stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

        if (Player.saveData) {
            System.out.println("Data saving!");
            DBUtils.savePlayerData();
        }
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
