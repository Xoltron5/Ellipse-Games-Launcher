import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class Filter implements Runnable {
    private String filterItem;
    private String searchText;
    private TilePane tilePane;
    private ArrayList<GameDetails> gameDetailsList;
    private ArrayList<GameDetails> filteredGameDetails;  // Add this field to store filtered results

    public Filter(String filterItem, String searchText, TilePane tilePane, ArrayList<GameDetails> gameDetailsList) {
        this.filterItem = filterItem != null ? filterItem : "All";  // Default to "All" if filterItem is null
        this.searchText = searchText != null ? searchText.toLowerCase() : ""; // Default to empty string if searchText is null
        this.tilePane = tilePane;
        this.gameDetailsList = gameDetailsList;
        this.filteredGameDetails = new ArrayList<>();  // Initialize the filtered results list
    }

    @Override
    public void run() {
        ArrayList<GameView> filteredViews = new ArrayList<>();

        // Filter based on both the filterItem (genre) and searchText
        for (GameDetails gameDetails : gameDetailsList) {
            boolean matchesFilter = filterItem.equals("All") || gameDetails.getGenresContainer().contains(filterItem);
            boolean matchesSearch = searchText.isEmpty() || gameDetails.getName().toLowerCase().contains(searchText);

            if (matchesFilter && matchesSearch) {
                filteredViews.add(gameDetails.getGameView());
                filteredGameDetails.add(gameDetails);  // Add matching games to filteredGameDetails
            }
        }

        // Update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(filteredViews);
        });
    }

    // Method to retrieve the filtered results
    public ArrayList<GameDetails> getFilteredGameDetails() {
        return filteredGameDetails;
    }
}

