import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class Search implements Runnable {
    private String searchText;
    private TilePane tilePane;
    private ArrayList<ItemsDetails> filteredItemDetailsList;

    public Search(String searchText, TilePane tilePane, ArrayList<ItemsDetails> filteredItemDetailsList) {
        setSearchText(searchText);
        setTilePane(tilePane);
        setFilteredItemDetailsList(filteredItemDetailsList);
    }

    @Override
    public void run() {
        ArrayList<ItemView> searchResults = new ArrayList<>();

        // Iterate over all filtered item details and add matching ones to the results
        for (ItemsDetails itemDetails : filteredItemDetailsList) {
            if (itemDetails.getName().toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(itemDetails.getItemView());
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

    public ArrayList<ItemsDetails> getFilteredItemDetailsList() {
        return filteredItemDetailsList;
    }

    public void setFilteredItemDetailsList(ArrayList<ItemsDetails> filteredItemDetailsList) {
        this.filteredItemDetailsList = filteredItemDetailsList;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane) {
        this.tilePane = tilePane;
    }
}
