package model;

public class Grandma extends CookieMaker{
    public Grandma(double time_to_wait, long ammount, long cost, String img_path) throws Exception {
        super(time_to_wait, ammount, cost, img_path);
    }

    //TO DO: FIX THIS FUNCTION
    @Override
    public void bonus(Player p) {
        try {
            long cookies_per_click = p.get_clicker().get_cookies_per_click();
            p.get_clicker().set_cookies_per_click(cookies_per_click + 1);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    @Override
    public final String describeBonus(){
        return "+1 for your clicker :D";
    }
}
