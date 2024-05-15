package DBRepositories;

import DBManager.DBManager;
import model.CookieClicker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieClickerRepository {
    DBManager db;
    private static CookieClickerRepository instance = null;
    public static CookieClickerRepository getInstance(){
        if(instance == null){
            instance = new CookieClickerRepository();
        }
        return instance;
    }
    private CookieClickerRepository(){
        db = DBManager.getInstance();
    }
    public CookieClicker getById(int id) throws Exception {
        ResultSet rs = db.executeQuery("SELECT * FROM CookieClickers where CookieClicker_id = " + id);
        rs.next();
        return CookieClicker.getCookieClicker_instance(rs.getInt("cookies_per_click"));
    }
    public void updateCookies_per_click(int cookie_clicker_id, long x) throws SQLException {
        db.executeUpdate("UPDATE COOKIECLICKERS SET cookies_per_click = " + x + " WHERE CookieClicker_id = " + cookie_clicker_id);
    }
    public int getNextId() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT seq_CookieClicker.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }
    public void insert(int id, CookieClicker c) throws SQLException{
        db.executeUpdate("INSERT INTO COOKIECLICKERS VALUES (" + id + "," + c.get_cookies_per_click()
                + ")");
    }
    public void deleteCookieClicker(int id) throws Exception{
        String query = "DELETE FROM COOKIECLICKERS WHERE CookieClicker_id = ?";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        db.executeUpdate("COMMIT");
    }
}
