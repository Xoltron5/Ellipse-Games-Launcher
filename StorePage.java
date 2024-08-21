import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class StorePage extends MainPage {

    public StorePage() {
        // Initialize file paths
        setFXMLFilePath("/assets/fxml/StorePage.fxml");
        setCSSFilePath("/assets/css/StorePage.css");

        // Use the initialized paths
        setCSS(this.getClass().getResource(getCSSFilePath()).toExternalForm());

        // Set the paths using the superclass methods
        setFXMLFilePath(getFXMLFilePath());
        setCSSFilePath(getCSSFilePath());
        setCSS(getCSS());

        setFilteredItemDetailsList(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void displayItems(ArrayList<ItemsDetails> itemDetailsHolder) {

    }

    @Override
    public void filter(ActionEvent event) {

    }

    @Override
    public void storeButtonClicked(ActionEvent e) {}

    @Override
    public void gamesButtonClicked(ActionEvent e) throws IOException {
        Main.getPageManager().navigateTo(new GamePage());
    }

    @Override
    public void inventoryButtonClicked(ActionEvent e) throws IOException {

    }    
}
