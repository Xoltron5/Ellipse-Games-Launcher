import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupContr extends DBUtils {

    // Query file paths 
    private static final String FIND_USER_QUERY_PATH = "sql/signup/findUser.sql";
    private static final String FIND_EMAIL_QUERY_PATH = "sql/signup/findEmail.sql";
    private static final String INSERT_USER_QUERY_PATH = "sql/signup/insertUser.sql";

    // SQL queries (for signup)
    private static String findUserQuery = DBUtils.loadSQLFromFile(FIND_USER_QUERY_PATH);
    private static String findEmailQuery = DBUtils.loadSQLFromFile(FIND_EMAIL_QUERY_PATH);
    private static String insertUserQuery = DBUtils.loadSQLFromFile(INSERT_USER_QUERY_PATH);

    // Error Messages 
    private static final String USERNAME_TAKEN = "Username is already taken!";
    private static final String EMAIL_TAKEN = "Email is already taken!";

    // Starting Stats
    final static long START_XP = 0;
    final static long START_COINS = 0;

    // Signup Page Database Connection. 
    public static void signUpPlayer(ActionEvent event,
    HashMap<String, TextField> playerInputs, HashMap<String, Label> incorrectLabels) throws IOException { 
        // A Connection(Session) with a specific database.
        Connection connection = null;

        // Prepared Statement is a template statement that we can prepare and set values for ? later. 
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psCheckEmailExists = null;
        PreparedStatement psInsert = null;

        // A table of data representing the results returned from the database.
        ResultSet resultSet = null;

        // Info recived from text fields on the Sign Up Page. 
        String username = playerInputs.get("username").getText();
        String email = playerInputs.get("email").getText();
        String dateOfBirth = playerInputs.get("dateOfBirth").getText();
        String password = playerInputs.get("password").getText();

        try {
            connection = connect();

            // Preparing Statements. 
            psCheckUserExists = connection.prepareStatement(findUserQuery);

            psCheckEmailExists = connection.prepareStatement(findEmailQuery);

            psInsert = connection.prepareStatement(insertUserQuery);
            
            // Binds the parameters to the prepared statement. 
            psCheckUserExists.setString(1, username);
            psCheckEmailExists.setString(1, email);

            // Executes the query. 
            resultSet = psCheckUserExists.executeQuery();

            // Checks to see if the result set is empty.
            // returns false if it is. true if it's not. 
            if (resultSet.isBeforeFirst()) {
                incorrectLabels.get("username").setText(getUsernameTaken());
                return;
            }

            // Executes the query. 
            resultSet = psCheckEmailExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                incorrectLabels.get("email").setText(getEmailTaken());
                return;
            } 

            // Converts the date Of Birth from DD/MM/YYYY to YYYY/MM/DD.
            // MySQL date format is YYYY/MM/DD. 
            String newDateOfBirth = DateConvertUtil.convertDate(DateConvertUtil.getFormatType1(), dateOfBirth);
            String hashedPassword = hashPassword(password);

            // Binds the parameters to the prepared statement. 
            psInsert.setString(1, username);
            psInsert.setString(2, email);
            psInsert.setString(3, newDateOfBirth);
            psInsert.setString(4, hashedPassword);
            psInsert.setDouble(5, START_COINS); // User starts off with 0 coins when they sign up!
            psInsert.setDouble(6, START_XP); // User starts off with 0 xp when they sign up!

            // Executes the Query. 
            psInsert.executeUpdate();

            // Set the Session Variables to hold data that will follow the user across pages.
            Player.username = username;
            Player.xp = START_XP;
            Player.coins = START_COINS;
            
            // Test code if everything goes well. 
            try {
                GamesPage games = new GamesPage();
                Main.getPageManager().navigateTo(games);
                System.out.println("Hello" + Player.username);
                System.out.println("XP: " + Player.xp);
                System.out.println("Coins: " + Player.coins);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (CommunicationsException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
            serviceDown(new Signup());
        } catch (Exception e) {
            incorrectLabels.get("confirmPassword").setText("An error has occured. Please try again later...");
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(psCheckUserExists);
            closePs(psInsert);
            closeConnection(connection);
        }
    }

    public static String getEmailTaken() {
        return EMAIL_TAKEN;
    }

    public static String getUsernameTaken() {
        return USERNAME_TAKEN;
    }
}
