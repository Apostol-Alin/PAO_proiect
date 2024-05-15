package game_service;

import DBManager.DBManager;
import DBRepositories.*;
import model.Cursor;
import model.Grandma;
import model.Player;

public class SaveAllProgressService {

    private static final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private static final CookieClickerRepository ccr = CookieClickerRepository.getInstance();
    private static final AchievementRepository achievementRepository = AchievementRepository.getInstance();
    private static final CursorRepository cursorRepository = CursorRepository.getInstance();
    private static final GrandmaRepository grandmaRepository = GrandmaRepository.getInstance();
    private static final CookieMakerRepository cookieMakerRepository = CookieMakerRepository.getInstance();
    public static void saveAllProgress(Player p) throws Exception {
        int player_id = playerRepository.getPlayerId(p.get_name());
        int cookieClicker_id = playerRepository.getCookieClickerId(p.get_name());
        ccr.updateCookies_per_click(cookieClicker_id, p.get_clicker().get_cookies_per_click());
        playerRepository.updateCookies(player_id, p.get_cookies());
        for(var ach: p.getAchievements()){
            int val = 0;
            if(ach.isAchieved())
                val = 1;
            achievementRepository.updateAchieved(ach.getName(), player_id, val);
        }
        for(var cookieMaker : p.getCookieMakerList()){
            int id;
            if(cookieMaker instanceof Cursor){
                id = cursorRepository.getCursorIdByPlayerId(player_id);

            }
            else if (cookieMaker instanceof Grandma){
                id = grandmaRepository.getGrandmaIdByPlayerId(player_id);
            }
            else{
                throw new RuntimeException("Error of cookieMaker instance");
            }
            cookieMakerRepository.updateAll(id, cookieMaker, p.getHowManyCookieMaker(cookieMaker));
        }
        DBManager.getInstance().executeUpdate("COMMIT");
    }
}
