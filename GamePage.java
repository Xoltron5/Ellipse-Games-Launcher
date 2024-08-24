import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

public class GamePage extends MainPage {
    @FXML
    private Label coinsAmountLabel;

    @FXML
    private ImageView profileImageView;
    
    public GamePage() {
        super("/assets/fxml/GamePage.fxml", "/assets/css/GamePage.css");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sets up initial stuff content that's common between all main pages.
        initialSetUp();

        // sets up the game page content. 
        initialPageSetUp();
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

        GameDetailsHolder gameDetailsHolder = new GameDetailsHolder();
        displayEntitys(gameDetailsHolder.getEntityDetailsHolder(),
        150,150,20, "Game", null);

        // Set the GameView's fitWidth and fitHeight
        getProfileImageView().setFitWidth(150);
        getProfileImageView().setFitHeight(150);
        getProfileImageView().setPreserveRatio(true); // Preserve the image's aspect ratio

        // Create a rectangle with rounded corners
        Rectangle clip = new Rectangle(150, 150);
        clip.setArcWidth(150);
        clip.setArcHeight(150);
        
        // Set the rectangle as the clip for the EntityView
        getProfileImageView().setClip(clip);

        // Get the players profile picture and assign it to the Profile Image View
        getProfileImageView().setImage(Player.getPlayerProfileIcon());

        // Add an event handler to the ImageView
        getProfileImageView().setOnMouseClicked(event -> onImageViewClicked());
    }

    private void onImageViewClicked() {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        
        // Set the title of the FileChooser
        fileChooser.setTitle("Select a PNG Image");

        // Set the initial directory (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Filter only PNG files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        // Open the FileChooser and get the selected file
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            // Create an Image from the selected file
            Image image = new Image(file.toURI().toString());

            // Assign the image to the ImageView
            getProfileImageView().setImage(image);
            Player.setPlayerProfileIcon(image);
        }
    }

    public Label getCoinsAmountLabel() {
        return coinsAmountLabel;
    }

    public ImageView getProfileImageView() {
        return profileImageView;
    }
}
