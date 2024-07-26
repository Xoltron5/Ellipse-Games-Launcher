import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DBUtils {

    // Database credentials
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    // File Path to the db properties
    private static String dbPropertiesFilePath = "..\\private\\Ellipse-Games-Launcher\\db.properties";

    // Date format types
    private static final String FORMAT_TYPE_1 = "DD/MM/YYYY to YYYY/MM/DD";
    private static final String FORMAT_TYPE_2 = "YYYY/MM/DD to DD/MM/YYYY";

    // Information taken error messages
    private static final String USERNAME_TAKEN = "Username is already taken!";
    private static final String EMAIL_TAKEN = "Email is already taken!";

    // Server error messages 
    private static final String MYSQL_SERVER_DOWN_MESSAGE = "MYSQL Server is down";
    private static final String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password.";

    // Will fix this in the future. Having issues with passing stuff into the 
    // ServiceUnavailable class and having the content update. 
    // Error codes & descriptions 
    private static final String ERROR_CODE_2002 = "2002";
    private static final String ERROR_CODE_2002_DESC = "We are currently unable to connect to our database. Please try again later.";

    // private static final String ERROR_MESSAGE = "Error Occured";
    // private static final String ERROR_CODE_UNKNOWN = "Unknown Error";
    // private static final String ERROR_CODE_UNKNOWN_DESC = "An unexcepted error has occured. Please try again";

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

        // SQL Queries 
        String checkUserQuery = "SELECT * FROM players WHERE username = ?";
        String checkEmailQuery = "SELECT * FROM players WHERE email_address = ?";
        String insertUserQuery = "INSERT INTO players (username, email_address, date_of_birth, password) VALUES (?, ?, ?, ?)";

        try {
            connection = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword());

            // Preparing Statements. 
            psCheckUserExists = connection.prepareStatement(checkUserQuery);

            psCheckEmailExists = connection.prepareStatement(checkEmailQuery);

            psInsert = connection.prepareStatement(insertUserQuery);
            
            // Binds the parameters to the prepared statement. 
            psCheckUserExists.setString(1, username);
            psCheckEmailExists.setString(1, email);

            // Executes the query. 
            resultSet = psCheckUserExists.executeQuery();

            // Checks to see if the result set is empty.
            // returns false if it is. true if it's not. 
            if (resultSet.isBeforeFirst()) {
                incorrectLabels.get("username").setText(USERNAME_TAKEN);
                return;
            }

            // Executes the query. 
            resultSet = psCheckEmailExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                incorrectLabels.get("email").setText(EMAIL_TAKEN);
                return;
            }

            // Converts the date Of Birth from DD/MM/YYYY to YYYY/MM/DD.
            // MySQL date format is YYYY/MM/DD. 
            String newDateOfBirth = convertDate(getFormatType1(), dateOfBirth);
            String hashedPassword = hashPassword(password);

            // Binds the parameters to the prepared statement. 
            psInsert.setString(1, username);
            psInsert.setString(2, email);
            psInsert.setString(3, newDateOfBirth);
            psInsert.setString(4, hashedPassword);
        
            // Executes the Query. 
            psInsert.executeUpdate();

            // test code if everything goes well. 
            try {
                Main.getPageManager().navigateTo(new Welcome());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (CommunicationsException e) {
            serviceDown(MYSQL_SERVER_DOWN_MESSAGE, ERROR_CODE_2002, ERROR_CODE_2002_DESC, new Signup());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(psCheckUserExists);
            closePs(psInsert);
            closeConnection(connection);
        }
    }
    
    // This method is responisble for setting up the error page if any error occur and saving the 
    // Old page so the user can navigate back to it.
    public static void serviceDown(String serverDownMessage, String errorCode, String errorCodeDes,
    Page perviousPage) throws IOException {
        System.out.println(serverDownMessage);
        ServiceUnavailable serviceUnavailable = new ServiceUnavailable();
        Main.getPageManager().savePerviousPage(perviousPage);
        Main.getPageManager().navigateTo(serviceUnavailable);
    }

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

        String query = "SELECT password FROM players WHERE username = ?";

        try {
            connection = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword());
            ps = connection.prepareStatement(query);
            
            // Binds the parameters to the prepared statement. 
            ps.setString(1, username);

            // Executes the query.
            resultSet = ps.executeQuery();

            // Checks to see if the result set contains the username. 
            // Returns false if it is not found. Returns true if it is found. 
            if (!resultSet.isBeforeFirst()) {
                incorrectLabels.get("password").setText(INCORRECT_USERNAME_OR_PASSWORD);
                return;
            }

            // Gets all records from the result set. 
            while (resultSet.next()) {
                retrievedPassword = resultSet.getString("password");
            }

            // Checks to see if the inputed is not equal to the password stored.
            if (!retrievedPassword.equals(hashPassword(password))) {
                incorrectLabels.get("password").setText(INCORRECT_USERNAME_OR_PASSWORD);
                return;
            }

            // Test code if everything goes well. 
            try {
                Main.getPageManager().navigateTo(new Welcome());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (CommunicationsException  e) {
            serviceDown(MYSQL_SERVER_DOWN_MESSAGE, ERROR_CODE_2002, ERROR_CODE_2002_DESC, new Login());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(ps);
            closeConnection(connection);
        }
    }

    // Method used to hash a password using SHA-256. 
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }

    // Converts Data to either DD/MM/YYYY or YYYY/MM/DD due to MYSQL only suppport YYYY/MM/DD.
    // Meaning You have to convert to and back.
    public static String convertDate(String formatType, String date) {
        // Split the input date string into parts
        String[] parts = date.split("/");
        
        // Ensure that the input date string has the correct format
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use the correct format.");
        }
        
        // Extract day, month, and year from the input date string
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        
        String convertedDate;

        if (formatType.equals("DD/MM/YYYY to YYYY/MM/DD")) {
            // Convert from DD/MM/YYYY to YYYY/MM/DD
            convertedDate = part3 + "/" + part2 + "/" + part1;
        } else if (formatType.equals("YYYY/MM/DD to DD/MM/YYYY")) {
            // Convert from YYYY/MM/DD to DD/MM/YYYY
            convertedDate = part3 + "/" + part2 + "/" + part1;
        } else {
            throw new IllegalArgumentException("Invalid format type. Please use 'DD/MM/YYYY to YYYY/MM/DD' or 'YYYY/MM/DD to DD/MM/YYYY'.");
        }
        
        return convertedDate;
    }

    // Closes ResultSet
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    // Closes Connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    // Closes Prepared Statement
    public static void closePs(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void loadDatabaseCredentials() {
    try (InputStream input = new FileInputStream(dbPropertiesFilePath)) {
        Properties prop = new Properties();
        prop.load(input);

        // Load database credentials from properties file
        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbPassword = prop.getProperty("dbPassword");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Getters & Setters 
    public static String getDbUrl() {
        return dbUrl;
    }

    public static void setDbUrl(String dbUrl) {
        DBUtils.dbUrl = dbUrl;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static void setDbUser(String dbUser) {
        DBUtils.dbUser = dbUser;
    }

    public static String getDbPassword() {
        return dbPassword;
    }
    
    public static void setDbPassword(String dbPassword) {
        DBUtils.dbPassword = dbPassword;
    }

    public static String getFormatType1() {
        return FORMAT_TYPE_1;
    }

    public static String getFormatType2() {
        return FORMAT_TYPE_2;
    }
}
