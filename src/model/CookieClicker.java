package model;

public class CookieClicker {
    private long cookies_per_click;
    private static CookieClicker CookieClicker_instance = null;
    private CookieClicker(long cookies_per_click) throws Exception {
        if(cookies_per_click <= 0){
            throw new Exception("Cookies per click must be higher than 0!");
        }
        this.cookies_per_click = cookies_per_click;
    }
    public static CookieClicker getCookieClicker_instance(long cookies_per_click) throws Exception{
        if(CookieClicker_instance == null)
            CookieClicker_instance = new CookieClicker(cookies_per_click);
        return CookieClicker_instance;
    }
    public long get_cookies_per_click(){
        return CookieClicker_instance.cookies_per_click;
    }
    public void set_cookies_per_click(long new_value) throws Exception {
        if(new_value <= 0){
            throw new Exception("Cookies per click must be higher than 0!");
        }
        CookieClicker_instance.cookies_per_click = new_value;
    }
    @Override
    public String toString(){
        return "This clicker has " + CookieClicker_instance.cookies_per_click + " cookies per click";
    }
}
