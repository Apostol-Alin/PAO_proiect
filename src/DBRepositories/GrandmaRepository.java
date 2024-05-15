package DBRepositories;

import DBManager.DBManager;
import model.Cursor;
import model.Grandma;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrandmaRepository {
    DBManager db;
    private static GrandmaRepository instance = null;
    public static GrandmaRepository getInstance(){
        if(instance == null){
            instance = new GrandmaRepository();
        }
        return instance;
    }
    private GrandmaRepository(){
        db = DBManager.getInstance();
    }
    public void insert(int id) throws SQLException {
        db.executeUpdate("INSERT INTO GRANDMAS VALUES (" + id + ")");
    }
    public Grandma getGrandmaByPlayerId(int id) throws Exception {
        ResultSet rs = db.executeQuery("SELECT *" +
                " FROM Grandmas g JOIN COOKIEMAKERS cm ON (g.Grandma_id = cm.CookieMaker_id)" +
                " WHERE cm.Player_id = " + id);
        if(rs == null){
            return null;
        }
        else{
            rs.next();
            return new Grandma(rs.getDouble("time_to_wait"), rs.getLong("ammount"), rs.getLong("cost"), rs.getString("img_path"));
        }
    }

    public int getGrandmaIdByPlayerId(int player_id) throws Exception{
        ResultSet rs = db.executeQuery("SELECT g.Grandma_id" +
                " FROM Grandmas g JOIN COOKIEMAKERS cm ON (g.Grandma_id = cm.CookieMaker_id)" +
                " WHERE cm.Player_id = " + player_id);
        rs.next();
        return rs.getInt(1);
    }
}
