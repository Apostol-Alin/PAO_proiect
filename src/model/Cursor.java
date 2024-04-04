package model;

public class Cursor extends CookieMaker{
    public Cursor(double time_to_wait, long ammount, long cost, String img_path) throws Exception {
        super(time_to_wait, ammount, cost, img_path);
    }

    @Override
    public void bonus(Player p) {
        p.update_cookies(p.get_cookies() + 1);
        //when bought, it gives a player a bonus cookie :)
    }
}
