package DBRepositories;

import DBManager.DBManager;
import model.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CursorRepository {
    DBManager db;
    private static CursorRepository instance = null;
    public static CursorRepository getInstance(){
        if(instance == null){
            instance = new CursorRepository();
        }
        return instance;
    }
    private CursorRepository(){
        db = DBManager.getInstance();
    }
    public void insert(int id) throws SQLException {
        db.executeUpdate("INSERT INTO CURSORS VALUES (" + id + ")");
    }
    public Cursor getCursorByPlayerId(int id) throws Exception {
        ResultSet rs = db.executeQuery("SELECT *" +
                                          " FROM CURSORS c JOIN COOKIEMAKERS cm ON (c.Cursor_id = cm.CookieMaker_id)" +
                                          " WHERE cm.Player_id = " + id);
        if(rs == null){
            return null;
        }
        else{
            rs.next();
            return new Cursor(rs.getDouble("time_to_wait"), rs.getLong("ammount"), rs.getLong("cost"), rs.getString("img_path"));
        }
    }
    public int getCursorIdByPlayerId(int player_id) throws Exception{
        ResultSet rs = db.executeQuery("SELECT c.Cursor_id" +
                                        " FROM CURSORS c JOIN COOKIEMAKERS cm ON (c.Cursor_id = cm.CookieMaker_id)" +
                                        " WHERE cm.Player_id = " + player_id);
        rs.next();
        return rs.getInt(1);
    }
}
