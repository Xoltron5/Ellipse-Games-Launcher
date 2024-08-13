import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDetailsContr extends DBUtils {
    
    // Query file paths
    private static final String SELECT_GAME_DETAILS_QUERY_PATH = "sql/gameDetails/selectGameDetails.sql";

    // SQL queries 
    private static String selectGameDetailsQuery = loadSQLFromFile(getSelectGameDetailsQueryPath());

    public static void loadGameDetails() {
        // A Connection(Session) with a specific database.
        Connection connection = null;

        // Prepared Statement is a template statement that we can prepare and set values for ? later. 
        PreparedStatement ps = null;              
        
        // A table of data representing the results returned from the database.
        ResultSet resultSet = null;

        // Gets a connection
        connection = connect();            
            
        // Preparing Statements. 
        try {
            // Prepares the statement. 
            ps = connection.prepareStatement(getSelectGameDetailsQuery());
        
            // Executes the query and stores the result within the result set. 
            resultSet = ps.executeQuery();

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

                // Stores the object within a container.
                GameDetailsHolder.getGameDetailsHolder().add(gameDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePs(ps);
            closeConnection(connection);
        }   
    }

    public static String getSelectGameDetailsQuery() {
        return selectGameDetailsQuery;
    }

    public static String getSelectGameDetailsQueryPath() {
        return SELECT_GAME_DETAILS_QUERY_PATH;
    }
}
