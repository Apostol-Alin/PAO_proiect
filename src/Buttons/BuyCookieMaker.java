package Buttons;

import javax.swing.*;
import game_service.*;
import model.CookieMaker;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyCookieMaker extends JButton {
    CookieMaker c;
    public BuyCookieMaker(game_service game_service, CookieMaker c){
        //set cookie image background
        ImageIcon mkr = new ImageIcon(c.getImg_path());
        Image image = mkr.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        mkr = new ImageIcon(image);
        this.setIcon(mkr);
        this.c = c;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p = game_service.getPlayer();
                if(p.get_cookies() >= c.getCost()){
                    p.update_cookies(-c.getCost());
                    p.addCookieMaker(c);
                    game_service.getPlayerInfo().setText("<html>" + p.toString().replaceAll("\n", "<br/>") + "</html>");
                }
            }
        });
    }

}
