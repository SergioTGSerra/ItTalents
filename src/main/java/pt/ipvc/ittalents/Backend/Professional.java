package pt.ipvc.ittalents.Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professional extends Person {
    private AreaType iTArea;
    private double priceHour;
    private boolean published;
    private Map<Integer, Integer> skills = new HashMap<>(); //ID Skill to Year Of Exprience
    private List<Exprience> expriences = new ArrayList<>(); //Exprience to Id of Skill

    public Professional(String username, String password, PersonType personType, AreaType iTArea) {
        super(username, password, personType);
        this.iTArea = iTArea;
    }
    public AreaType getiTArea() {
        return iTArea;
    }
    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }
    public double getPriceHour() {
        return priceHour;
    }
    public void setPriceHour(double priceHour) {
        this.priceHour = priceHour;
    }
    public void addSkill(int skill, int yearsExprience){
        skills.put(skill, yearsExprience);
    }
    public Map<Integer, Integer> getSkills() {
        return skills;
    }
    public void addExprience(Exprience exprience){
        expriences.add(exprience);
    }
    public List<Exprience> getExpriences() {
        return expriences;
    }
}
