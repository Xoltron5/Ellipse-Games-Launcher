import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDetailsContr extends DBUtils {
    // Query file paths
    private static final String selectGameDetailsQueryPath = "sql/gameDetails/selectGameDetails.sql";
    private static final String selectGameGenresQueryPath = "sql/gameDetails/selectGameGenres.sql";
    
    // SQL queries 
    private static final String selectGameDetailsQuery = loadSQLFromFile(getSelectGameDetailsQueryPath());
    private static final String selectGameGenresQuery = loadSQLFromFile(getSelectGameGenresQueryPath());

    public static void loadGameDetails() {
        // A Connection(Session) with a specific database.
        Connection connection = null;

        // Prepared Statement is a template statement that we can prepare and set values for ? later. 
        PreparedStatement ps = null;              
        
        // Table of data representing the results returned from the database.
        ResultSet resultSet = null;

        ResultSet genreResultSet = null;

        // Gets a connection
        connection = connect();            
            
        // Preparing Statements. 
        try {
            // Prepares the statement. 
            ps = connection.prepareStatement(getSelectGameDetailsQuery());
        
            // Executes the query and stores the result within the result set. 
            resultSet = ps.executeQuery();

            // Query used to get all the game genres 
            ps = connection.prepareStatement(getSelectGameGenresQuery());
            while (resultSet.next()) {
                // Gets all the game details
                long id = resultSet.getLong("id");
                String gameName = resultSet.getString("name");
                String releasedDate = resultSet.getString("releasedDate");
                String developer = resultSet.getString("developer");
                String description = resultSet.getString("description");
                String iconPath = resultSet.getString("iconPath");

                // Creates and stores the game details within a game details object.
                GameDetails gameDetails = new GameDetails(id, gameName, releasedDate, developer, description, iconPath);

                ps.setString(1, gameDetails.getName());

                genreResultSet = ps.executeQuery();

                // Gets each game genre and stores it within a arraylist. 
                while (genreResultSet.next()) {
                    String genre = genreResultSet.getString("genreName");
                    gameDetails.getFilterContentContainer().add(genre);
                }

                // Stores the object within a container.
                GameDetailsHolder.getItemDetailsHolder().add(gameDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeResultSet(genreResultSet);
            closePs(ps);
            closeConnection(connection);
        }   
    }

    public static String getSelectGameDetailsQuery() {
        return selectGameDetailsQuery;
    }

    public static String getSelectGameDetailsQueryPath() {
        return selectGameDetailsQueryPath;
    }

    public static String getSelectGameGenresQueryPath() {
        return selectGameGenresQueryPath;
    }

    public static String getSelectGameGenresQuery() {
        return selectGameGenresQuery;
    }
}
