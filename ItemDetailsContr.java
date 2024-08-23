import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDetailsContr extends DBUtils {
    // Query file paths
    private static final String selectItemDetailsQueryPath = "sql/itemDetails/selectItemDetails.sql";
    private static final String selectItemGenresQueryPath = "sql/itemDetails/selectItemGenres.sql";
    
    // SQL queries 
    private static final String selectItemDetailsQuery = loadSQLFromFile(getSelectItemDetailsQueryPath());
    private static final String selectItemGenresQuery = loadSQLFromFile(getSelectItemGenresQueryPath());

    public static void loadItemDetails() {
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
            ps = connection.prepareStatement(getSelectItemDetailsQuery());
        
            // Executes the query and stores the result within the result set. 
            resultSet = ps.executeQuery();

            // Query used to get all the item genres.
            ps = connection.prepareStatement(getSelectItemGenresQuery());

            while (resultSet.next()) {
                // Gets all the item details
                long id = resultSet.getLong("id");
                long gameId = resultSet.getLong("gameId");
                String itemName = resultSet.getString("name");
                int levelReq = resultSet.getInt("levelReq");
                String description = resultSet.getString("description");
                String iconPath = resultSet.getString("iconPath");
                int cost = resultSet.getInt("cost");

                // Creates and stores the item details within a item details object.
                ItemDetails itemDetails = new ItemDetails(id, gameId, itemName, 
                levelReq, description, iconPath, 
                cost);

                ps.setString(1, itemDetails.getName());

                genreResultSet = ps.executeQuery();

                // Gets each item genre and stores it within a arraylist. 
                while (genreResultSet.next()) {
                    String genre = genreResultSet.getString("genreName");
                    itemDetails.getFilterContentContainer().add(genre);
                }

                // Stores the object within a container.
                ItemDetailsHolder itemDetailsHolder = new ItemDetailsHolder();
                itemDetailsHolder.getEntityDetailsHolder().add(itemDetails);
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

    public static String getSelectItemDetailsQueryPath() {
        return selectItemDetailsQueryPath;
    }

    public static String getSelectItemGenresQueryPath() {
        return selectItemGenresQueryPath;
    }

    public static String getSelectItemDetailsQuery() {
        return selectItemDetailsQuery;
    }    

    public static String getSelectItemGenresQuery() {
        return selectItemGenresQuery;
    }    
}
