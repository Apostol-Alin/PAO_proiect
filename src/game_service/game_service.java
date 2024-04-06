package game_service;
import Buttons.BuyCookieMaker;
import Buttons.CookieButton;
import Repositories.CookieMakerRepository;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import ProgressBars.*;


public class game_service {
    private final Player player;
    private ArrayList<CookieMaker> CookieMakers;
    private JLabel[] CookieMakersLabels;
    private JLabel[] CookieMakerBonusLabels;
    private CookieMakerProgressBar[] CookieMakersProgressBars;
    private JFrame MainFrame;
    private JLabel PlayerInfo;
    private JPanel PlayerPanel;
    private JPanel ShopPanel;
    public game_service(Player p){
        this.player = p;
        try{
            this.CookieMakers = CookieMakerRepository.retCookieMakersList();
            this.CookieMakersLabels = new JLabel[this.CookieMakers.size()];
            this.CookieMakersProgressBars = new CookieMakerProgressBar[this.CookieMakers.size()];
            this.CookieMakerBonusLabels = new JLabel[this.CookieMakers.size()];
            for(int i = 0; i < this.CookieMakers.size(); i++) {
                this.CookieMakersLabels[i] = new JLabel();
                this.CookieMakersProgressBars[i] = new CookieMakerProgressBar((int)this.CookieMakers.get(i).getTime_to_wait());
                this.CookieMakerBonusLabels[i] = new JLabel();
            }
        } catch (Exception e) {
            System.out.println("Eroare la crearea de Cookie Makers!!!!");
        }
        this.setUpMainFrame();
    }
    public Player getPlayer(){return this.player;}
    public JFrame getMainFrame(){return this.MainFrame;}
    public JLabel getPlayerInfo(){return this.PlayerInfo;}
    public JPanel getShopPanel(){return this.ShopPanel;}

    private void setUpMainFrame(){
        //Create main frame
        MainFrame = new JFrame("Cookie clicker");
        MainFrame.setSize(1000,1000);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setLayout(new BorderLayout());
        MainFrame.getContentPane().setBackground(Color.darkGray);
    }
    private void setUpPlayerPanel(){
        //Create panel that consists of Player info and cookie clicker
        PlayerPanel = new JPanel();
        PlayerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        PlayerPanel.setBackground(Color.BLACK);
        //need html to display \n...
        ImageIcon cook = new ImageIcon(Player.get_pic());
        Image cookimg = cook.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        cook = new ImageIcon(cookimg);
        JLabel playerIcon = new JLabel(cook);
        PlayerPanel.add(playerIcon);

        PlayerInfo = new JLabel("<html>" + player.toString().replaceAll("\n", "<br/>") + "</html>");
        PlayerInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        PlayerInfo.setForeground(Color.WHITE);
        PlayerPanel.add(PlayerInfo);

        //Create the button for the cookie clicker
        JButton cookie_button = new CookieButton(this);
        PlayerPanel.add(cookie_button);
    }
    private void setUpShopPanel(){
        ImageIcon background = new ImageIcon("pictures/background.png");
        Image bckg = background.getImage();
        ShopPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(bckg, 0, 0, null);
            }
        };
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        ShopPanel.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        Collections.sort(this.CookieMakers);

        for (int i = 0; i < this.CookieMakers.size(); i++) {
            BuyCookieMaker button = new BuyCookieMaker(this, this.CookieMakers.get(i), i);
            String z;
            try{
                z = String.valueOf(player.getHowManyCookieMaker(this.CookieMakers.get(i)));
            }catch (Exception ex){
                z = "0";
            }
            this.CookieMakersLabels[i].setForeground(Color.WHITE);
            this.CookieMakersLabels[i].setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            this.CookieMakersLabels[i].setText("<html>" + this.CookieMakers.get(i).toString().replaceAll("\n", "<br/>") + "<br/> You have: " + z + "</html>" );
            this.CookieMakerBonusLabels[i].setForeground(Color.WHITE);
            this.CookieMakerBonusLabels[i].setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            this.CookieMakerBonusLabels[i].setVisible(false);
            this.CookieMakerBonusLabels[i].setText(this.CookieMakers.get(i).describeBonus());

            gridBagConstraints.insets = new Insets(5, 5, 5, 15);
            gridBagConstraints.gridx = i;

            gridBagConstraints.gridy = 0;
            ShopPanel.add(this.CookieMakerBonusLabels[i], gridBagConstraints);

            gridBagConstraints.gridy = 1;
            ShopPanel.add(button, gridBagConstraints);

            gridBagConstraints.gridy = 2;
            ShopPanel.add(this.CookieMakersLabels[i], gridBagConstraints);

            gridBagConstraints.gridy = 3;
            ShopPanel.add(this.CookieMakersProgressBars[i], gridBagConstraints);

        }
    }
    public JLabel getCookieMakerLabel_at(int index){
        return this.CookieMakersLabels[index];
    }
    public JLabel getCookieMakerBonusLabel_at(int index){return this.CookieMakerBonusLabels[index];}
    public CookieMakerProgressBar getCookieMakerPorgressBar_at(int index){
        return this.CookieMakersProgressBars[index];
    }
    public CookieMaker getCookieMaker_at(int index){
        return this.CookieMakers.get(index);
    }
    private void createGUI(){
        setUpMainFrame();
        setUpPlayerPanel();
        setUpShopPanel();
        //Add the playerPanel to the main frame
        MainFrame.add(PlayerPanel, BorderLayout.NORTH);
        //Add the ShopPanel to the main frame
        MainFrame.add(ShopPanel, BorderLayout.CENTER);
        MainFrame.setVisible(true);
    }
    public void run(){
        this.createGUI();
    }

}
