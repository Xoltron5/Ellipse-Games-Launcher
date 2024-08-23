import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class Filter implements Runnable {
    private String filterItem;
    private String searchText;
    private TilePane tilePane;
    private ArrayList<EntityDetails> entityDetailsList;
    private ArrayList<EntityDetails> filteredEntityDetails;  // Add this field to store filtered results

    public Filter(String filterItem, String searchText, TilePane tilePane, ArrayList<EntityDetails> entityDetailsList) {
        this.filterItem = filterItem != null ? filterItem : "All";  // Default to "All" if filterItem is null
        this.searchText = searchText != null ? searchText.toLowerCase() : ""; // Default to empty string if searchText is null
        this.tilePane = tilePane;
        this.entityDetailsList = entityDetailsList;
        this.filteredEntityDetails = new ArrayList<>();  // Initialize the filtered results list
    }

    @Override
    public void run() {
        ArrayList<EntityView> filteredViews = new ArrayList<>();

        // Filter based on both the filterItem (genre) and searchText
        for (EntityDetails entityDetails : entityDetailsList) {
            boolean matchesFilter = filterItem.equals("All") || entityDetails.getFilterContentContainer().contains(filterItem);
            boolean matchesSearch = searchText.isEmpty() || entityDetails.getName().toLowerCase().contains(searchText);

            if (matchesFilter && matchesSearch) {
                filteredViews.add(entityDetails.getEntityView());
                filteredEntityDetails.add(entityDetails);  // Add matching entitys to filteredEntityDetails
            }
        }

        // Update the UI on the JavaFX Application Thread
        Platform.runLater(() -> {
            tilePane.getChildren().clear();
            tilePane.getChildren().addAll(filteredViews);
        });
    }

    // Method to retrieve the filtered results
    public ArrayList<EntityDetails> getFilteredEntityDetails() {
        return filteredEntityDetails;
    }
}

