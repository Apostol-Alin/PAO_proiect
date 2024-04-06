package Repositories;

import model.Achievement;

import java.util.ArrayList;

public class AchievementRepository {
    public static ArrayList<Achievement> retAchievementList(){
        ArrayList<Achievement> achievements = new ArrayList<>();
        Achievement five_grandmas = new Achievement("Who doesn't like grandma's cookies?",
                "You bought five Grandmas", false);
        Achievement ten_cursors = new Achievement("Click that!", "You bought 10 Cursors", false);
        achievements.add(five_grandmas);
        achievements.add(ten_cursors);
        return achievements;
    }
}
