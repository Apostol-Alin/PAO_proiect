package DBManager;

import model.Player;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
    private static final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String username = "alin";
    private static final String password = "oracle";
    private static Connection connection;
    //Make it Singleton
    private static DBManager instance = null;
    private DBManager(){
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(username);
            ods.setPassword(password);
            connection = ods.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
            return instance;
        }
        else
            return instance;
    }

    public ResultSet executeQuery(String q) throws SQLException {
        try {
            PreparedStatement stmt = connection.prepareStatement(q);
            return stmt.executeQuery();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void insertPlayerSafe(int id, Player p, int cookieClicker_id) throws SQLException {
        String query = "INSERT INTO PLAYERS VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, p.get_name());
            stmt.setLong(3, p.get_cookies());
            stmt.setInt(4, cookieClicker_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exceptions
            throw new SQLException("Error inserting player data into database", e);
        }
    }
    public int executeUpdate(String q) throws SQLException{
        try {
            PreparedStatement stmt = connection.prepareStatement(q);
            return stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        return stmt;
    }
}
