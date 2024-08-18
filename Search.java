import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class Search implements Runnable {
    private String searchText;
    private TilePane tilePane;
    private ArrayList<GameDetails> filteredGameDetailsList;

    public Search(String searchText, TilePane tilePane, ArrayList<GameDetails> filteredGameDetailsList) {
        setSearchText(searchText);
        setTilePane(tilePane);
        setFilteredGameDetailsList(filteredGameDetailsList);
    }

    @Override
    public void run() {
        ArrayList<GameView> searchResults = new ArrayList<>();

        // Iterate over all filtered game details and add matching ones to the results
        for (GameDetails gameDetails : filteredGameDetailsList) {
            if (gameDetails.getName().toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(gameDetails.getGameView());
            }
        }

        // Update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(searchResults);
        });
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public ArrayList<GameDetails> getFilteredGameDetailsList() {
        return filteredGameDetailsList;
    }

    public void setFilteredGameDetailsList(ArrayList<GameDetails> filteredGameDetailsList) {
        this.filteredGameDetailsList = filteredGameDetailsList;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane) {
        this.tilePane = tilePane;
    }
}
