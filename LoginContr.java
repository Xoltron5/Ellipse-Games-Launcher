import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginContr extends DBUtils {
    // Query file paths 
    private static final String SELECT_PASS_QUERY = "sql/login/selectPass.sql";
    private static final String SELECT_CONTENT_QUERY = "sql/login/selectContent.sql";
    private static final String SELECT_OWNED_ITEM_QUERY = "sql/getItemsOwned/selectOwnedItems.sql";

    // SQL queries (for login) 
    private static String selectPassQuery = loadSQLFromFile(getSelectPassQuery());
    private static String selectContentQuery = loadSQLFromFile(getSelectContentQuery());
    private static String selectOwnedItemsQuery = loadSQLFromFile(getSelectOwnedItemQuery());

    // This method is responsible for performing extra checks and logging the user in.
    // Along with connecting to the database. 
    public static void logInPlayer(ActionEvent event,
    HashMap<String, TextField> playerInputs, HashMap<String, Label> incorrectLabels) throws IOException {
        // A Connection(Session) with a specific database.
        Connection connection = null;

        // Prepared Statement is a template statement that we can prepare and set values for ? later. 
        PreparedStatement ps = null;              
        
        // A table of data representing the results returned from the database.
        ResultSet resultSet = null;

        // The retrieved password from the database.
        String retrievedPassword = null;

        String username = playerInputs.get("username").getText();
        String password = playerInputs.get("password").getText();

        try {
            connection = connect();

            // Preparing Statements. 
            ps = connection.prepareStatement(selectPassQuery);
            
            // Binds the parameters to the prepared statement. 
            ps.setString(1, username);

            // Executes the query.
            resultSet = ps.executeQuery();

            // Checks to see if the result set contains the username. 
            // Returns false if it is not found. Returns true if it is found. 
            if (!resultSet.isBeforeFirst()) {
                incorrectLabels.get("password").setText(getIncorrectUsernameOrPassword());
                return;
            }

            // Gets all records from the result set. 
            while (resultSet.next()) {
                retrievedPassword = resultSet.getString("password");
            }

            // Checks to see if the inputed is not equal to the password stored.
            if (!retrievedPassword.equals(hashPassword(password))) {
                incorrectLabels.get("password").setText(getIncorrectUsernameOrPassword());
                return;
            }

            // Set the Session Variables to hold data that will follow the user across pages.
            ps = connection.prepareStatement(selectContentQuery);

            ps.setString(1, username);

            resultSet = ps.executeQuery();

            Player.setUsername(username);
            while (resultSet.next()) {
                Player.setXp(resultSet.getInt("xp"));
                Player.setCoins(resultSet.getInt("coins"));
            }

            ps = connection.prepareStatement(selectOwnedItemsQuery);

            ps.setString(1, username);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Player.getInventory().add(resultSet.getString("name"));
            }

            // Test code if everything goes well. 
            try {
                Main.getPageManager().navigateTo(new GamePage());
                System.out.println("Hello" + Player.getUsername());
                System.out.println("XP: " + Player.getXp());
                System.out.println("Coins: " + Player.getCoins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (CommunicationsException  e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
            serviceDown(new Login());
        } catch (Exception e) {
            incorrectLabels.get("password").setText("An error has occured. Please try again later...");
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(ps);
            closeConnection(connection);
        }
    }

    public static String getSelectPassQuery() {
        return SELECT_PASS_QUERY;
    }

    public static String getSelectContentQuery() {
        return SELECT_CONTENT_QUERY;
    }

    public static String getSelectOwnedItemQuery() {
        return SELECT_OWNED_ITEM_QUERY;
    }
}
