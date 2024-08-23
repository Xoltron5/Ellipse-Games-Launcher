import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GamePage extends MainPage {

    
    @FXML
    private Label coinsAmountLabel;
    
    public GamePage() {
        super("/assets/fxml/GamePage.fxml", "/assets/css/GamePage.css");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sets up initial stuff content that's common between all main pages.
        initialSetUp();

        // sets up the game page content. 
        initialPageSetUp();

        for (String itemString : Player.getInventory()) {
            System.out.println(itemString);
        }
    }

    @Override
    public void filter(ActionEvent event) {
        String filterItem = getFiltersComboBox().getValue(); // Ensure this is not null
        String searchText = getSearchTextField().getText();  // Get the text from the search field
    
        // Acquire the game details holder.
        GameDetailsHolder gameDetailsHolderMain = new GameDetailsHolder();
        ArrayList<EntityDetails> gameDetailsHolder = gameDetailsHolderMain.getEntityDetailsHolder();
    
        // Create an instance of Filter and pass the necessary parameters
        Filter filterTask = new Filter(filterItem, searchText, getTilePane(), gameDetailsHolder);
    
        // Start a new thread with the Filter
        Thread filterThread = new Thread(filterTask);
    
        filterThread.setName("Filter Game Thread");
        filterThread.setDaemon(true);
        filterThread.start();
    
        // After filtering, set the filtered games to the filteredEntityDetailsList for searching
        setFilteredEntityDetailsList(filterTask.getFilteredEntityDetails());
    }    

    @Override
    protected void initialPageSetUp() {
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

        String currentCoinsText = getCoinsAmountLabel().getText();

        // Sets the player's coins.
        getCoinsAmountLabel().setText(currentCoinsText + Long.toString(Player.getCoins()));

        // Sets up the filter content
        getFiltersComboBox().getItems().addAll(getFilterContent());

        GameDetailsHolder gameDetailsHolder = new GameDetailsHolder();
        displayEntitys(gameDetailsHolder.getEntityDetailsHolder(),
        150,150,20, "Game", null);
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

    public Label getCoinsAmountLabel() {
        return coinsAmountLabel;
    }
}
