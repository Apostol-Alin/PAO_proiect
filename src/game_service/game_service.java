package game_service;
import Buttons.CookieButton;
import model.*;
import javax.swing.*;
import java.awt.*;


public class game_service {
    private Player player;
    private JFrame MainFrame = null;
    public game_service(Player p){
        this.player = p;
        this.createGUI();
    }
    public Player getPlayer(){return this.player;}
    public JFrame getMainFrame(){return this.MainFrame;}
    private void setUpMainFrame(){
        //Create main frame
        MainFrame = new JFrame("Cookie clicker");
        MainFrame.setSize(1000,1000);
        MainFrame.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.getContentPane().setBackground(Color.darkGray);
    }
    private void createGUI(){
        setUpMainFrame();
        //Create panel that consists of Player info and cookie clicker
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        playerPanel.setBackground(Color.RED);
        //need html to display \n...
        ImageIcon cook = new ImageIcon(Player.get_pic());
        Image cookimg = cook.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        cook = new ImageIcon(cookimg);
        JLabel playerIcon = new JLabel(cook);
        playerIcon.setBounds(0, 0, 200, 50);
        playerPanel.add(playerIcon);

        JLabel playerInfo = new JLabel("<html>" + player.toString().replaceAll("\n", "<br/>") + "</html>");
        playerInfo.setBounds(0, 0, 200, 50);
        playerPanel.add(playerInfo);

        //Create the button for the cookie clicker
        JButton cookie_button = new CookieButton(this);

        //Add it to the playerPanel
        playerPanel.add(cookie_button);
        //Set size of the playerPanel
        playerPanel.setSize(new Dimension(50,50));

        //Add the playerPanel to the main frame
        MainFrame.add(playerPanel, BorderLayout.NORTH);
        MainFrame.setVisible(true);
    }
}
