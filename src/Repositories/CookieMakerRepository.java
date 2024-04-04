package Repositories;
import model.CookieMaker;
import model.Cursor;
import model.Grandma;

import java.util.ArrayList;

public class CookieMakerRepository {
    public static ArrayList<CookieMaker> retCookieMakersList() throws Exception {
        ArrayList<CookieMaker> lista = new ArrayList<CookieMaker>();
        Cursor c = new Cursor(10, 1, 100, "pictures/cursor.jpg");
        lista.add(c);
        Grandma g = new Grandma(20, 5, 500, "pictures/grandma.jpg");
        lista.add(g);
        return lista;
    }
}
