import DBManager.DBManager;
import DBRepositories.*;
import model.*;
import game_service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private static final CookieClickerRepository ccr = CookieClickerRepository.getInstance();
    private static final AchievementRepository achievementRepository = AchievementRepository.getInstance();
    private static final CursorRepository cursorRepository = CursorRepository.getInstance();
    private static final GrandmaRepository grandmaRepository = GrandmaRepository.getInstance();
    private static final CookieMakerRepository cookieMakerRepository = CookieMakerRepository.getInstance();
    private static void startNewGame(String name) throws Exception {
        CookieClicker c = CookieClicker.getCookieClicker_instance(1);
        Player p = new Player(name, 0, c);
        int cookieClicker_id = ccr.getNextId();
        ccr.insert(cookieClicker_id, c);
        int player_id = playerRepository.getNextId();
        playerRepository.insert(player_id, p, cookieClicker_id);
        int cursorId = cookieMakerRepository.getNextId();
        Cursor cursor = new Cursor(10 ,1, 100, "pictures/cursor.png");
        p.initCookieMakerHashtable(cursor);
        cookieMakerRepository.insert(cursorId, cursor, player_id);
        cursorRepository.insert(cursorId);
        int grandmaId = cookieMakerRepository.getNextId();
        Grandma g = new Grandma(15, 5, 500, "pictures/grandma.png");
        p.initCookieMakerHashtable(g);
        cookieMakerRepository.insert(grandmaId, g, player_id);
        grandmaRepository.insert(grandmaId);

        ArrayList<Achievement> achievements = new ArrayList<>();
        Achievement five_grandmas = new Achievement("Who doesn't like grandma's cookies?",
                "You bought five Grandmas", false);
        int five_grandmas_id = achievementRepository.getNextId();
        achievementRepository.insert(five_grandmas_id, player_id, five_grandmas);
        Achievement ten_cursors = new Achievement("Click that!", "You bought 10 Cursors", false);
        int ten_cursors_id = achievementRepository.getNextId();
        achievementRepository.insert(ten_cursors_id, player_id, ten_cursors);
        achievements.add(five_grandmas);
        achievements.add(ten_cursors);
        p.addAchievement(achievements);

        game_service game = new game_service(p);
        game.run();
        audit_service.log("Player " + p.get_name() + " started a new game");
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static void playerLogin() throws Exception {
        System.out.println("Hello! Welcome to Cookie Clicker Game. Please enter your name from the following | choose a new name | enter 0 to delete a player!");
        ArrayList<Player> players = PlayerRepository.getInstance().getAllPlayers();
        for(var p : players){
            System.out.println(p.get_name() + " " + p.get_cookies());
        }
        String name = scanner.next();
        boolean playerAlreadyExists = false;
        for(var p : players){
            if(p.get_name().equals(name)){
                playerAlreadyExists = true;
                break;
            }
        }

        if(name.equals("0")){
            System.out.println("Please enter the player you want to delete: ");
            String playerToDelete = scanner.next();
            int cookieClickerId = playerRepository.getCookieClickerId(playerToDelete);
            playerRepository.deletePlayer(playerToDelete);
            ccr.deleteCookieClicker(cookieClickerId);
            audit_service.log("Player " + playerToDelete + " was deleted");

        } else if (playerAlreadyExists) {
            Player p = PlayerRepository.getInstance().getFullPlayerByName(name);
            game_service game = new game_service(p);
            audit_service.log("Started new session with " + p.get_name());
            game.run();
        }
        else{
            startNewGame(name);
        }
    }
    public static void main(String[] args){
        try {
            playerLogin();
        }
        catch (Exception exc){
            System.out.println(exc.toString());
        }
    }
}