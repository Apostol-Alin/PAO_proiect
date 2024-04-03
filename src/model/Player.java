package model;

public final class Player {
    private final String name;
    private long cookies;
    private CookieClicker clicker;
    private final static String pic = "pictures/player.jpg";
    private static Player player_instance = null;
    private Player(String name, long cookies, CookieClicker cookieClickerInstance) throws Exception {
        if(cookies < 0)
            throw new Exception("Invalid number of cookies!");
        this.name = name;
        this.cookies = cookies;
        clicker = cookieClickerInstance;
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
    public boolean update_cookies(long ammount){
        if(player_instance.cookies < ammount)
            return false;
        else{
            player_instance.cookies += ammount;
            return true;
        }
    }
    public CookieClicker get_clicker(){
        return clicker;
    }
    public static String get_pic(){
        return Player.pic;
    }
}
