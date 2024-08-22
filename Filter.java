import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class Filter implements Runnable {
    private String filterItem;
    private String searchText;
    private TilePane tilePane;
    private ArrayList<ItemsDetails> itemDetailsList;
    private ArrayList<ItemsDetails> filteredItemDetails;  // Add this field to store filtered results

    public Filter(String filterItem, String searchText, TilePane tilePane, ArrayList<ItemsDetails> itemDetailsList) {
        this.filterItem = filterItem != null ? filterItem : "All";  // Default to "All" if filterItem is null
        this.searchText = searchText != null ? searchText.toLowerCase() : ""; // Default to empty string if searchText is null
        this.tilePane = tilePane;
        this.itemDetailsList = itemDetailsList;
        this.filteredItemDetails = new ArrayList<>();  // Initialize the filtered results list
    }

    @Override
    public void run() {
        ArrayList<ItemView> filteredViews = new ArrayList<>();

        // Filter based on both the filterItem (genre) and searchText
        for (ItemsDetails itemDetails : itemDetailsList) {
            boolean matchesFilter = filterItem.equals("All") || itemDetails.getFilterContentContainer().contains(filterItem);
            boolean matchesSearch = searchText.isEmpty() || itemDetails.getName().toLowerCase().contains(searchText);

            if (matchesFilter && matchesSearch) {
                filteredViews.add(itemDetails.getItemView());
                filteredItemDetails.add(itemDetails);  // Add matching items to filteredGameDetails
            }
        }

        // Update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(filteredViews);
        });
    }

    // Method to retrieve the filtered results
    public ArrayList<ItemsDetails> getFilteredGameDetails() {
        return filteredItemDetails;
    }
}

