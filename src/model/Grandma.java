package model;

public class Grandma extends CookieMaker{
    public Grandma(double time_to_wait, long ammount, long cost, String img_path) throws Exception {
        super(time_to_wait, ammount, cost, img_path);
    }

    public Grandma(Grandma c){
        super(c);
    }

    @Override
    public void bonus(Player p) {
        //gives the player a bonus cursor :D
    }
    @Override
    public final String describeBonus(){
        return "Nothing yet.";
    }
}
