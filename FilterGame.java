import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class FilterGame implements Runnable {
    private String filterItem;
    private TilePane tilePane;
    private List<GameDetails> gameDetailsList;

    public FilterGame(String filterItem, TilePane tilePane, List<GameDetails> gameDetailsList) {
        setFilterItem(filterItem);
        setTilePane(tilePane);
        setGameDetailsList(gameDetailsList);
    }

    @Override
    public void run() {
        List<GameView> filteredViews = new ArrayList<>();

        // Display all games 
        if (filterItem.equals("All")) {
            for (GameDetails gameDetails : gameDetailsList) {
                filteredViews.add(gameDetails.getGameView());
            }
        } else { // Display only filtered games based on filter 
            for (GameDetails gameDetailsObject: gameDetailsList) {
                for (String genre : gameDetailsObject.getGenresContainer()) {
                    if (genre.equals(filterItem)) {
                        filteredViews.add(gameDetailsObject.getGameView());
                    }
                }
            }
        }

        // Update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(filteredViews);
        });
    }

    public String getFilterItem() {
        return filterItem;
    }

    public void setFilterItem(String filterItem) {
        this.filterItem = filterItem;
    }

    public List<GameDetails> getGameDetailsList() {
        return gameDetailsList;
    }

    public void setGameDetailsList(List<GameDetails> gameDetailsList) {
        this.gameDetailsList = gameDetailsList;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane) {
        this.tilePane = tilePane;
    }
}
