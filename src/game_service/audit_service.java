package game_service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class audit_service {
    private static final String path = "audit.csv";
    public static void log(String text){
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println(text + "," + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
