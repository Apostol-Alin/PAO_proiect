package Buttons;

import javax.swing.*;
import game_service.*;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CookieButton extends JButton{

    public CookieButton(game_service game_service) {
        //set cookie image background
        ImageIcon cookie = new ImageIcon("pictures/buton.png");
        Image image = cookie.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        cookie = new ImageIcon(image);
        this.setIcon(cookie);
        //Make it round with some code I found on stackoverflow
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p = game_service.getPlayer();
                p.update_cookies(p.get_clicker().get_cookies_per_click());
                System.out.println(p.toString());
            }
        });
    }


}
