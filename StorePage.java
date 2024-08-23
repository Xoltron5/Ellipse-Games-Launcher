import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StorePage extends MainPage {

    @FXML
    private VBox displayPanelVBox;

    public StorePage() {
        super("/assets/fxml/StorePage.fxml", "/assets/css/StorePage.css");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sets up initial stuff content that's common between all main pages.
        initialSetUp();

        // sets up the store page content. 
        initialPageSetUp();
    }

    @Override
    protected void initialPageSetUp() {
        // Sets up the filter content
        getFiltersComboBox().getItems().addAll(getFilterContent());

        ItemDetailsHolder itemDetailsHolder = new ItemDetailsHolder();
        displayEntitys(itemDetailsHolder.getEntityDetailsHolder(),
        100, 100, 0, "Item");
    }    

    @Override
    public void filter(ActionEvent event) {
        String filterItem = getFiltersComboBox().getValue(); // Ensure this is not null
        String searchText = getSearchTextField().getText();  // Get the text from the search field
    
        // Acquire the item details holder.
        ItemDetailsHolder itemDetailsHolderMain = new ItemDetailsHolder();
        ArrayList<EntityDetails> itemDetailsHolder = itemDetailsHolderMain.getEntityDetailsHolder();
    
        // Create an instance of Filter and pass the necessary parameters
        Filter filterTask = new Filter(filterItem, searchText, getTilePane(), itemDetailsHolder);
    
        // Start a new thread with the Filter
        Thread filterThread = new Thread(filterTask);
    
        filterThread.setName("Filter Item Thread");
        filterThread.setDaemon(true);
        filterThread.start();
    
        // After filtering, set the filtered games to the filteredEntityDetailsList for searching
        setFilteredEntityDetailsList(filterTask.getFilteredEntityDetails());
    } 

    @Override
    public void gamesButtonClicked(ActionEvent e) throws IOException {
        Main.getPageManager().navigateTo(new GamePage());
    }

    @Override
    public void storeButtonClicked(ActionEvent e) {}

    @Override
    public void inventoryButtonClicked(ActionEvent e) throws IOException {

    }
}
