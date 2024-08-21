import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class GamePage extends MainPage {

    private static final String[] filterContent = {
        "All",
        "Adventure",
        "RPG",
        "Shooter",
        "Strategy",
        "Puzzle",
        "Racing",
        "Horror",
        "Sci-Fi"
    };
    
    public GamePage() {
        // Initialize file paths
        setFXMLFilePath("/assets/fxml/GamePage.fxml");
        setCSSFilePath("/assets/css/GamePage.css");

        // Use the initialized paths
        setCSS(this.getClass().getResource(getCSSFilePath()).toExternalForm());

        // Set the paths using the superclass methods
        setFXMLFilePath(getFXMLFilePath());
        setCSSFilePath(getCSSFilePath());
        setCSS(getCSS());

        setFilteredItemDetailsList(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // sets up initial stuff content that's common between all main pages.
        initialSetUp();

        String currentLevelText = getLevelLabel().getText();

        // Sets the player's username.
        getUsernameLabel().setText(Player.getUsername());

        // Calculates the player's current level based on their xp.
        int playersLevel = Player.determineLevel(Player.getXp());

        // Sets the player's current level.
        getLevelLabel().setText(currentLevelText + playersLevel);
        
        // Calculates the player's next level based on their xp.
        long nextLevelXP = Player.nextLevelXP(Player.getXp());
        int nextLevel = playersLevel + 1;

        // Sets the player's xp needed for the next level.
        getNextLevelXPLabel().setText(nextLevelXP + " more xp to Level " + nextLevel);

        // Sets up the filter content
        getFiltersComboBox().getItems().addAll(getFilterContent());

        displayItems(GameDetailsHolder.getItemDetailsHolder());
    }

    @Override
    public void filter(ActionEvent event) {
        String filterItem = getFiltersComboBox().getValue(); // Ensure this is not null
        String searchText = getSearchTextField().getText();  // Get the text from the search field
    
        // Acquire the game details holder.
        ArrayList<ItemsDetails> gameDetailsHolder = GameDetailsHolder.getItemDetailsHolder();
    
        // Create an instance of Filter and pass the necessary parameters
        Filter filterTask = new Filter(filterItem, searchText, getTilePane(), gameDetailsHolder);
    
        // Start a new thread with the Filter
        Thread filterThread = new Thread(filterTask);
    
        filterThread.setName("Filter Game Thread");
        filterThread.setDaemon(true);
        filterThread.start();
    
        // After filtering, set the filtered games to the filteredGameDetailsList for searching
        setFilteredItemDetailsList(filterTask.getFilteredGameDetails());
    }    

    @Override
    public void gamesButtonClicked(ActionEvent e) throws IOException {}

    @Override
    public void storeButtonClicked(ActionEvent e) throws IOException {
        Main.getPageManager().navigateTo(new StorePage());
    }

    @Override
    public void inventoryButtonClicked(ActionEvent e) throws IOException {

    }

    public static String[] getFilterContent() {
        return filterContent;
    }
}
