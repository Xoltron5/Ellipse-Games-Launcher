import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Stack;

public class PageManager {
    // Stack is used to store pervious pages and keep track of them.
    // So the user can navigate back to them.
    private Stack<Page> pageHistory = new Stack<>();
    private Stage primaryStage;

    public PageManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Navigates a user to a new page.
    public FXMLLoader navigateTo(Page page) throws IOException {
        return page.initializeScene(primaryStage);
    }

    // This will save the pervious page. So the user can navigate back to it. 
    public void savePerviousPage(Page page) throws IOException {
        pageHistory.push(page);
    }

    // navigates the user to the pervious page stored on the stack.
    public void navigateBack() throws IOException {
        if (!pageHistory.isEmpty()) {
            Page previousPage = pageHistory.pop();
            Main.setCurrentPage(previousPage);
            previousPage.initializeScene(primaryStage);
        }
    }

    public void clearPageStack() {
        while (!pageHistory.isEmpty()) {
            pageHistory.pop();
        }
    }
}
