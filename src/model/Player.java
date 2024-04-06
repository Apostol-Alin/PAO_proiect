package model;

import java.util.ArrayList;
import java.util.Hashtable;

public final class Player {
    private final String name;
    private long cookies;
    private CookieClicker clicker;
    private final Hashtable<CookieMaker, Integer> cookieMakers;
    private ArrayList<Achievement> achievements;
    private final static String pic = "pictures/player.png";
    private static Player player_instance = null;
    private Player(String name, long cookies, CookieClicker cookieClickerInstance) throws Exception {
        if(cookies < 0)
            throw new Exception("Invalid number of cookies!");
        this.name = name;
        this.cookies = cookies;
        this.clicker = cookieClickerInstance;
        this.cookieMakers = new Hashtable<CookieMaker, Integer>();
    }
    public void addCookieMaker(CookieMaker c){
        cookieMakers.merge(c, 1, Integer::sum);
    }
    public int getHowManyCookieMaker(CookieMaker c) throws Exception{
        if(this.cookieMakers.get(c) == null)
            throw new Exception("None");
        else
            return this.cookieMakers.get(c);
    }

    public static Player getInstance(String name, long cookies, CookieClicker clicker) throws Exception {
        if(player_instance == null) {
            if(clicker == null)
                player_instance = new Player(name, cookies, CookieClicker.getCookieClicker_instance(1));
            else
                player_instance = new Player(name, cookies, clicker);
        }
        return player_instance;
    }
    public final String get_name(){
        return player_instance.name;
    }
    public long get_cookies(){
        return player_instance.cookies;
    }
    @Override
    public String toString(){
        return "Name: " + player_instance.name + "\nCookies: " + player_instance.cookies;
    }
    public void update_cookies(long ammount){
        this.cookies += ammount;
    }
    public final void addAchievement(ArrayList<Achievement> a){
        this.achievements = a;
    }
    public ArrayList<Achievement> getAchievements(){return achievements;}
    public CookieClicker get_clicker(){
        return clicker;
    }
    public static String get_pic(){
        return Player.pic;
    }
}
