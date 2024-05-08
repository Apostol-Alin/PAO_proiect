import Repositories.AchievementRepository;
import model.*;
import game_service.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class Main {
    public static void main(String[] args){
        try {
            Player alin = Player.getInstance("Alin", 10000, null);
            alin.addAchievement(AchievementRepository.retAchievementList());
            game_service game = new game_service(alin);
            game.run();
        }
        catch (Exception exc){
            System.out.println(exc.toString());
        }
    }
}