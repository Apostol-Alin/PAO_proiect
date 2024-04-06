package ProgressBars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game_service.*;

public class CookieMakerProgressBar extends JProgressBar {
    public CookieMakerProgressBar(int maximum){
        this.setMinimum(0);
        this.setMaximum(maximum);
        this.setStringPainted(true);
        this.setVisible(false);
        this.setPreferredSize(new Dimension(100,20));
        this.setForeground(Color.GREEN);
    }
}
