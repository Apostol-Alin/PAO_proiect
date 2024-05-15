package DBRepositories;

import DBManager.DBManager;
import model.CookieMaker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CookieMakerRepository {
    DBManager db;
    private static CookieMakerRepository instance = null;
    public static CookieMakerRepository getInstance(){
        if(instance == null){
            instance = new CookieMakerRepository();
        }
        return instance;
    }
    private CookieMakerRepository(){
        db = DBManager.getInstance();
    }
    public int getNextId() throws SQLException {
        ResultSet rs = db.executeQuery("SELECT seq_CookieMaker.nextval FROM DUAL");
        rs.next();
        return rs.getInt(1);
    }
    public void insert(int id, CookieMaker c, int player_id) throws SQLException {
        db.executeUpdate("INSERT INTO COOKIEMAKERS VALUES (" + id + "," + c.getTime_to_wait()
                +"," + c.getAmmount() + "," + c.getCost() + "," + "'" +c.getImg_path()+"'"  + "," +
                player_id + ", 0" +")");
    }
    public int getHowMany(int id) throws SQLException {
        ResultSet rs = db.executeQuery("SELECT how_many FROM COOKIEMAKERS WHERE CookieMaker_id = " + id);
        if(rs == null){
            return 0;
        }
        else {
            rs.next();
            return rs.getInt("how_many");
        }
    }
    public void updateAll(int id, CookieMaker c, int how_many) throws SQLException{
        db.executeUpdate("UPDATE COOKIEMAKERS" +
                            " SET time_to_wait = " + c.getTime_to_wait() +
                            ", ammount = " + c.getAmmount() +
                            ", cost = " + c.getCost() +
                            ", img_path = " + "'" +c.getImg_path()+"'" +
                            ", How_many = " + how_many +
                            " WHERE CookieMaker_id = " + id);
    }
}
