package Buttons;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import game_service.*;
import model.CookieMaker;
import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class BuyCookieMaker extends JButton {
    private CookieMaker c;
    private boolean firstTimePressed = true;
    public BuyCookieMaker(game_service game_service, CookieMaker c, int label_index){
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        //set cookie image background
        ImageIcon mkr = new ImageIcon(c.getImg_path());
        Image image = mkr.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        mkr = new ImageIcon(image);
        this.setIcon(mkr);
        this.c = c;
        this.events(game_service, label_index);
    }
    private void events(game_service game_service, int label_index){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p = game_service.getPlayer();
                if(p.get_cookies() >= c.getCost()){
                    p.update_cookies(-c.getCost());
                    p.addCookieMaker(c);
                    game_service.getPlayerInfo().setText("<html>" + p.toString().replaceAll("\n", "<br/>") + "</html>");
                    JLabel associated_label = game_service.getCookieMakerLabel_at(label_index);
                    String z;
                    try{
                        z = String.valueOf(p.getHowManyCookieMaker(c));
                    }catch (Exception ex){
                        z = "0";
                    }
                    if(firstTimePressed){
                        game_service.getCookieMakerPorgressBar_at(label_index).setVisible(true); // Make the cooking time progress bar visible
                        c.setTimer(p, game_service.getPlayerInfo(), game_service.getCookieMakerPorgressBar_at(label_index)); //Set up and start the timer for cooking the Cookies
                        firstTimePressed = false;
                    }
                    associated_label.setText("<html>" + c.toString().replaceAll("\n", "<br/>") + "<br/> You have: " + z + "</html>" );
                }
            }
        });
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                game_service.getCookieMakerBonusLabel_at(label_index).setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                game_service.getCookieMakerBonusLabel_at(label_index).setVisible(false);
            }
        });
    }

}
