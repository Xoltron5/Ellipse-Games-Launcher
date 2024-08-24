import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
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
import java.util.Properties;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class DBUtils {

    // Database credentials
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    // File Path to the db properties
    private static String dbPropertiesFilePath = "..\\private\\Ellipse-Games-Launcher\\db.properties";

    private static final String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password.";

    // Query file paths 
    private static final String selectPlayerDataQueryPath = "sql/updatePlayerData/updatePlayerDataTable.sql";
    private static final String selectPlayerItemsQueryPath = "sql/updatePlayerData/updatePlayerItemsTable.sql";

    // SQL queries (for login) 
    private static String selectPlayerDataQuery = loadSQLFromFile(getSelectPlayerDataQueryPath());
    private static String selectPlayerItemsQuery = loadSQLFromFile(getSelectPlayerItemsQueryPath());

    protected static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword());
        } catch (SQLException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
        }
        return connection;
    }

    public static String loadSQLFromFile(String filePath) {
        StringBuilder sql = new StringBuilder();
        String line;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                sql.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql.toString();
    }

    public static void loadDatabaseCredentials() {
        try (InputStream input = new FileInputStream(getDbPropertiesFilePath())) {
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

    public static void savePlayerData() {
        // A Connection(Session) with a specific database.
        Connection connection = null;

        // Prepared Statement is a template statement that we can prepare and set values for ? later. 
        PreparedStatement ps = null;              
        
        // A table of data representing the results returned from the database.
        ResultSet resultSet = null;

        try {
            connection = connect();

            ps = connection.prepareStatement(getSelectPlayerDataQuery());

            BufferedImage playerIcon = Player.getPlayerProfileIcon();
            
            byte[] profilePicBytes = SignupContr.readImage(playerIcon, "png");

            ps.setLong(1, Player.getXp());
            ps.setLong(2, Player.getCoins());
            ps.setBytes(3, profilePicBytes);
            ps.setLong(4, Player.getId());

            ps.executeUpdate();


            ps = connection.prepareStatement(getSelectPlayerItemsQuery());

            for (Long newItem : Player.getInventoryItemsId()) {
                ps.setLong(1, Player.getId());
                ps.setLong(2, newItem);

                ps.executeUpdate();
            }

        } catch (CommunicationsException  e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(ps);
            closeConnection(connection);
        }
    }
 
    // This method is responisble for setting up the error page if any error occur and saving the 
    // Old page so the user can navigate back to it.
    public static void serviceDown(Page perviousPage) throws IOException {
        ServiceUnavailable serviceUnavailable = new ServiceUnavailable();
        Main.getPageManager().savePerviousPage(perviousPage);
        Main.getPageManager().navigateTo(serviceUnavailable);
    }

    // Method used to hash a password using SHA-256. 
    protected static String hashPassword(String password) {
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

    public static Image convertToFxImage(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            // Convert BufferedImage to Image (WritableImage is a subclass of Image)
            WritableImage fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            return fxImage;
        }
        return null;
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
    
    public static String getIncorrectUsernameOrPassword() {
        return INCORRECT_USERNAME_OR_PASSWORD;
    }

    public static String getDbPropertiesFilePath() {
        return dbPropertiesFilePath;
    }
 
    public static String getSelectPlayerDataQueryPath() {
        return selectPlayerDataQueryPath;
    }

    public static String getSelectPlayerDataQuery() {
        return selectPlayerDataQuery;
    }

    public static String getSelectPlayerItemsQueryPath() {
        return selectPlayerItemsQueryPath;
    }

    public static String getSelectPlayerItemsQuery() {
        return selectPlayerItemsQuery;
    }
}
