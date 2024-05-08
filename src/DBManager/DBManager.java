package DBManager;

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
            ods.setURL("jdbc:oracle:thin:@//localhost:1521/xe");
            ods.setUser("alin");
            ods.setPassword("oracle");
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

}
