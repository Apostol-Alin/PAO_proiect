package model;

public class Achievement {
    private String name;
    private String description;
    private boolean achieved;

    public Achievement(String name, String description, Boolean achieved) {
        this.name = name;
        this.description = description;
        this.achieved = achieved;
    }
    public boolean isAchieved(){return achieved;}
    public void setAchieved(boolean val){achieved = val;}
    public String getName (){return name;}
    public String getDescription(){return description;}

}
