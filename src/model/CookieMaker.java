package model;

public abstract class CookieMaker implements Comparable<CookieMaker>{
    private double time_to_wait; // in seconds
    private long ammount; // cookies
    private long cost;
    private String img_path;
    public CookieMaker(double time_to_wait, long ammount, long cost, String img_path) throws Exception{
        if(time_to_wait <= 0 || ammount <= 0)
            throw new Exception("Invalid parameters for cookie maker");
        this.time_to_wait = time_to_wait;
        this.ammount = ammount;
        this.cost = cost;
        this.img_path = img_path;
    }
    public long getAmmount() {
        return this.ammount;
    }
    public void setAmmount(long a){
        this.ammount = a;
    }
    public double getTime_to_wait(){
        return this.time_to_wait;
    }
    public void setTime_to_wait(double t){
        //waiting time must not be lower than 1 second
        if(t >= 1) {
            this.time_to_wait = t;
        }
    }

    public abstract void bonus(Player p);
    @Override
    public int compareTo(CookieMaker ob){
        return Long.compare(ammount, ob.ammount);
    }
}
