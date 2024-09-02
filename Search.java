/* 
    Author: Denis Bajgora
    Date: 1/9/2024

    Search class keeps is used to iterate over the current displayed items and update the tile pane 
    with the new returned items based on the search. NOTE: THIS SHOULD BE RAN ON A SEPERATE THREAD
    TO AVOID FREEZING OF THE PROGRAM!
*/

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.layout.TilePane;

public class Search implements Runnable {
    private String searchText;
    private TilePane tilePane;
    private ArrayList<EntityDetails> filteredEntityDetailsList;

    public Search(String searchText, TilePane tilePane, ArrayList<EntityDetails> filteredEntityDetailsList) {
        setSearchText(searchText);
        setTilePane(tilePane);
        setFilteredEntityDetailsList(filteredEntityDetailsList);
    }

    @Override
    public void run() {
        ArrayList<EntityView> searchResults = new ArrayList<>();

        // Iterate over all filtered entity details and add matching ones to the results
        for (EntityDetails entityDetails : filteredEntityDetailsList) {
            if (entityDetails.getName().toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(entityDetails.getEntityView());
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

    public ArrayList<EntityDetails> getFilteredEntityDetailsList() {
        return filteredEntityDetailsList;
    }

    public void setFilteredEntityDetailsList(ArrayList<EntityDetails> filteredEntityDetailsList) {
        this.filteredEntityDetailsList = filteredEntityDetailsList;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane) {
        this.tilePane = tilePane;
    }
}
