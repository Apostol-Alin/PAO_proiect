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

    public Player(String name, long cookies, CookieClicker cookieClickerInstance) throws Exception {
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
    public void initCookieMakerHashtable(CookieMaker c){
        cookieMakers.put(c, 0);
    }
    public int getHowManyCookieMaker(CookieMaker c) throws Exception{
        if(this.cookieMakers.get(c) == null)
            throw new Exception("None");
        else
            return this.cookieMakers.get(c);
    }
    public ArrayList<CookieMaker> getCookieMakerList(){
        return new ArrayList<>(cookieMakers.keySet());
    }

    public final String get_name(){
        return name;
    }
    public long get_cookies(){
        return cookies;
    }
    @Override
    public String toString(){
        return "Name: " + name + "\nCookies: " + cookies;
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
    public void set_clicker(CookieClicker c){
        this.clicker = c;
    }
    public static String get_pic(){
        return Player.pic;
    }
}
