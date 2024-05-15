package DBRepositories;

import DBManager.DBManager;
import model.CookieClicker;
import model.Cursor;
import model.Grandma;
import model.Player;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerRepository {
    DBManager db;
    private static PlayerRepository instance = null;
    public static PlayerRepository getInstance(){
        if(instance == null){
            instance = new PlayerRepository();
        }
        return instance;
    }
    private PlayerRepository(){
        db = DBManager.getInstance();
    }
    public int getNextId() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT seq_Player.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }
    public void insert(int id, Player p, int cookieClicker_id) throws SQLException{
        db.insertPlayerSafe(id, p, cookieClicker_id);
    }
    public void updateCookies(int id, long cookies) throws SQLException {
        db.executeUpdate("UPDATE PLAYERS SET cookies = " + cookies + " WHERE Player_id = " + id);
    }
    public int getPlayerId(String name) throws Exception{
        String query = "SELECT Player_id FROM PLAYERS WHERE Name = ?";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt("Player_id");
    }
    public int getCookieClickerId(String name) throws Exception{
        String query = "SELECT CookieClicker_id FROM PLAYERS WHERE Name = ?";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt("CookieClicker_id");
    }
    public Player getFullPlayerByName(String name) throws Exception{
        String query = "SELECT * FROM PLAYERS WHERE Name = ?";
        PreparedStatement stmt = db.prepareStatement(query);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if(rs == null)
            return null;
        else {
            rs.next();
            Player p = new Player(name, rs.getInt("Cookies"), null);
            CookieClickerRepository ccr = CookieClickerRepository.getInstance();
            CookieClicker c = ccr.getById(rs.getInt("CookieClicker_id"));
            p.set_clicker(c);
            CursorRepository cursRep = CursorRepository.getInstance();
            Cursor curs = cursRep.getCursorByPlayerId(rs.getInt("Player_id"));
            int howManyCursors = CookieMakerRepository.getInstance().getHowMany(cursRep.getCursorIdByPlayerId(rs.getInt("Player_id")));
            if(howManyCursors == 0)
                p.initCookieMakerHashtable(curs);
            else {
                for (int i = 0; i < howManyCursors; i++) {
                    p.addCookieMaker(curs);
                }
            }
            GrandmaRepository grandRep = GrandmaRepository.getInstance();
            Grandma grandma = grandRep.getGrandmaByPlayerId(rs.getInt("Player_id"));
            int howManyGrandmas = CookieMakerRepository.getInstance().getHowMany(grandRep.getGrandmaIdByPlayerId(rs.getInt("Player_id")));
            if(howManyGrandmas == 0)
                p.initCookieMakerHashtable(grandma);
            else {
                for (int i = 0; i < howManyGrandmas; i++) {
                    p.addCookieMaker(grandma);
                }
            }
            AchievementRepository achRep = AchievementRepository.getInstance();
            p.addAchievement(achRep.getPlayerAchievements(rs.getInt("Player_id")));
            return p;
        }
    }
    public ArrayList<Player> getAllPlayers() throws Exception {
        ArrayList<Player> list = new ArrayList<>();
        ResultSet rs = db.executeQuery("SELECT * FROM Players");
        if(rs != null){
            while(rs.next()){
                Player p = new Player(rs.getString("Name"), rs.getInt("Cookies"), null);
                list.add(p);
            }
        }
        return list;
    }
}
