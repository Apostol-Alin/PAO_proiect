package Repositories;
import model.CookieMaker;
import model.Cursor;
import model.Grandma;

import java.util.ArrayList;

public class CookieMakerRepository {
    public static ArrayList<CookieMaker> retCookieMakersList() throws Exception {
        ArrayList<CookieMaker> lista = new ArrayList<CookieMaker>();
        Cursor c = new Cursor(1, 1, 100, "pictures/cursor.png");
        lista.add(c);
        Grandma g = new Grandma(2, 5, 500, "pictures/grandma.png");
        lista.add(g);
        return lista;
    }
}
