package model;

import java.awt.event.ActionEvent;

public class Cursor extends CookieMaker{
    public Cursor(double time_to_wait, long ammount, long cost, String img_path) throws Exception {
        super(time_to_wait, ammount, cost, img_path);
    }

    @Override
    public final String describeBonus(){
        return "It gives you a bonus cookie :D";
    }
    @Override
    public void bonus(Player p) {
        p.update_cookies(1);
        //when bought, it gives a player a bonus cookie :)
    }
    public Cursor(Cursor c){
        super(c);
    }
}
