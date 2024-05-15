package DBRepositories;

import DBManager.DBManager;
import model.Achievement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AchievementRepository {
    DBManager db;
    private static AchievementRepository instance = null;
    public static AchievementRepository getInstance(){
        if(instance == null){
            instance = new AchievementRepository();
        }
        return instance;
    }
    private AchievementRepository(){
        db = DBManager.getInstance();
    }
    public int getNextId() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT seq_Achievement.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }
    public void insert(int id, int Player_id, Achievement ach) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO ACHIEVEMENTS VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, ach.getName());
        stmt.setString(3, ach.getDescription());
        stmt.setInt(4, 0);
        stmt.setInt(5, Player_id);
        stmt.executeUpdate();
    }
    public void updateAchieved(String name, int player_id, int ach) throws SQLException {
        String query = "UPDATE ACHIEVEMENTS SET achieved = ? WHERE name = ? AND Player_id = ?";
        PreparedStatement pstmt = db.prepareStatement(query);
        pstmt.setInt(1, ach);
        pstmt.setString(2, name);
        pstmt.setInt(3, player_id);
        pstmt.executeUpdate();
    }
    public ArrayList<Achievement> getPlayerAchievements(int player_id) throws SQLException {
        ArrayList<Achievement> list = new ArrayList<>();
        ResultSet rs = db.executeQuery("SELECT * FROM ACHIEVEMENTS WHERE Player_id = " + player_id);
        if(rs != null){
            while(rs.next()){
                boolean achieved = rs.getInt("achieved") != 0;
                list.add(new Achievement(rs.getString("Name"), rs.getString("Description"), achieved));
            }
        }
        return list;
    }
}
