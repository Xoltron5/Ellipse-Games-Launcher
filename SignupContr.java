/* 
    Author: Denis Bajgora
    Date: 1/9/2024

    Signup Controller class is used to test the players input and check the data they inputed is 
    valid if so it can be inserted in the database if not then it will change one of the error labels
    part of the signup page. 
*/

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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

            String randomPfpPath = "assets/images/default_pfps/" + chooseProfile();
            System.out.println("randomPfpPath: " + randomPfpPath);
            BufferedImage profilePicture = ImageIO.read(new File(randomPfpPath));

            byte[] profilePicBytes = readImage(randomPfpPath);

            // Binds the parameters to the prepared statement. 
            psInsert.setString(1, username);
            psInsert.setString(2, email);
            psInsert.setString(3, newDateOfBirth);
            psInsert.setString(4, hashedPassword);
            psInsert.setDouble(5, START_COINS); // User starts off with 0 coins when they sign up!
            psInsert.setDouble(6, START_XP); // User starts off with 0 xp when they sign up!
            psInsert.setBytes(7, profilePicBytes); // Stores the default profile picture in the database

            // Executes the Query. 
            psInsert.executeUpdate();

            // Set the Session Variables to hold data that will follow the user across pages.
            Player.setUsername(username);
            Player.setXp(START_XP);
            Player.setCoins(START_COINS);
            Player.setPlayerProfileIcon(profilePicture);

            Player.saveData = true;
            
            // Test code if everything goes well. 
            try {
                GamePage games = new GamePage();
                Main.getPageManager().navigateTo(games);
                System.out.println("Hello" + Player.getUsername());
                System.out.println("XP: " + Player.getXp());
                System.out.println("Coins: " + Player.getCoins());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (CommunicationsException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
            serviceDown(new Signup());
        } catch (Exception e) {
            incorrectLabels.get("confirmPassword").setText("An error has occured. Please try again later...");
            serviceDown(new Signup());
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(psCheckUserExists);
            closePs(psInsert);
            closeConnection(connection);
        }
    }

    public static String chooseProfile() {
        int defaultProfilePicturesAmount = NumOfSubPaths("assets/images/default_pfps/");
        Random rand = new Random();
        
        // Generate a random integer between 1 (inclusive) and 6 (inclusive)
        int randomNumber = rand.nextInt(defaultProfilePicturesAmount) + 1;

        return "pfp" + randomNumber + ".png";
    }

        public static int NumOfSubPaths(String path) {
        String directoryPath = path;  // Specify your directory path here

        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            String[] items = directory.list();
            if (items != null) {
                return items.length;
            } else {
                return -1;  // Return -1 if an error occurs
            }
        } else {
            return -1;  // Return -1 if the path is not a directory
        }
    }
    
    public static byte[] readImage(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] imageBytes = new byte[(int) file.length()];
        fis.read(imageBytes);
        fis.close();
        return imageBytes;
    }

    public static byte[] readImage(BufferedImage image, String format) throws IOException {
        // Step 1: Convert BufferedImage to ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, format, byteArrayOutputStream);

        // Step 2: Convert ByteArrayOutputStream to byte array
        return byteArrayOutputStream.toByteArray();
    }

    public static String getEmailTaken() {
        return EMAIL_TAKEN;
    }

    public static String getUsernameTaken() {
        return USERNAME_TAKEN;
    }
}
