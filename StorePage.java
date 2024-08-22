import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class StorePage extends MainPage {

    public StorePage() {
        super("/assets/fxml/StorePage.fxml", "/assets/css/StorePage.css");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sets up initial stuff content that's common between all main pages.
        initialSetUp();

    }

    @Override
    protected void initialPageSetUp() {
        
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
