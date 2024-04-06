package Buttons;

import game_service.game_service;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class AchievementButton extends JButton {
    public AchievementButton(JFrame frame) {
        //set cookie image background
        ImageIcon achievement = new ImageIcon("pictures/achievement_icon.png");
        Image image = achievement.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        achievement = new ImageIcon(image);
        this.setIcon(achievement);
        //Make it round with some code I found on stackoverflow
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
            }
        });
    }
}
