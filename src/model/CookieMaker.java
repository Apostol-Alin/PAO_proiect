package model;

import ProgressBars.CookieMakerProgressBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public abstract class CookieMaker implements Comparable<CookieMaker>{
    private double time_to_wait; // in seconds
    private long ammount; // cookies
    private final long cost;
    private String img_path;
    java.util.Timer timer;
    public CookieMaker(double time_to_wait, long ammount, long cost, String img_path) throws Exception{
        if(time_to_wait <= 0 || ammount <= 0)
            throw new Exception("Invalid parameters for cookie maker");
        this.time_to_wait = time_to_wait;
        this.ammount = ammount;
        this.cost = cost;
        this.img_path = img_path;
    }
    public void setTimer(Player p, JLabel l, CookieMakerProgressBar pb){
        long h = this.ammount;
        this.timer = new java.util.Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    java.util.Timer pbtimer = new Timer();
                    pbtimer.schedule(new TimerTask() {
                        int old_value = 0;
                        @Override
                        public void run() {
                            pb.setValue(old_value);
                            if(old_value >= time_to_wait)
                                old_value = 0;
                            old_value = old_value + 1;
                        }
                    }, 0, 1000);
                    p.update_cookies(p.getHowManyCookieMaker(CookieMaker.this) * h);
                    l.setText("<html>" + p.toString().replaceAll("\n", "<br/>") + "</html>");
                    //System.out.println("TIMER");
                } catch (Exception ex) {
                    System.out.println("Eroare, nu s-a gasit cookieMakerul!");
                }
            }
        }, 0, (long) (time_to_wait * 1000));

    }
    @Override
    public int hashCode(){
        return Objects.hash(this.cost, this.time_to_wait, this.ammount, this.img_path);
    }
    public long getAmmount() {
        return this.ammount;
    }
    public void setAmmount(long a){
        this.ammount = a;
    }
    public long getCost(){return this.cost;}
    public double getTime_to_wait(){
        return this.time_to_wait;
    }
    public void setTime_to_wait(double t){
        //waiting time must not be lower than 1 second
        if(t >= 1) {
            this.time_to_wait = t;
        }
    }
    public abstract String describeBonus();
    public final String getImg_path(){return this.img_path;}
    public abstract void bonus(Player p);
    @Override
    public int compareTo(CookieMaker ob){
        return Long.compare(ammount, ob.ammount);
    }

    @Override
    public String toString(){
        return this.getClass().getName().replaceAll("model.", "") + "\nCost: " + this.cost + "\n Cookies: " + this.ammount + "\n Seconds: " + this.time_to_wait;
    }
}
