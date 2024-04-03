import model.*;
import game_service.*;
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Player alin = Player.getInstance("Alin", 100, null);
            game_service game = new game_service(alin);
        }
        catch (Exception exc){
            System.out.println(exc.toString());
        }
    }
}