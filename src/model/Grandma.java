package model;

public class Grandma extends CookieMaker{
    public Grandma(double time_to_wait, long ammount, long cost, String img_path) throws Exception {
        super(time_to_wait, ammount, cost, img_path);
    }

    @Override
    public void bonus(Player p) {
        //gives the player a bonus cursor :D
    }
}
