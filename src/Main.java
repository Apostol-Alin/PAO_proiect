import model.*;
import game_service.*;
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Player alin = Player.getInstance("Alin", 1000000, null);
            game_service game = new game_service(alin);
            game.run();
        }
        catch (Exception exc){
            System.out.println(exc.toString());
        }
    }
}