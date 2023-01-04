package pt.ipvc.ittalents.Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professional extends Person {
    private final AreaType iTArea;
    private double priceHour;
    private boolean published;
    private final Map<Integer, Integer> skills = new HashMap<>(); //ID Skill to Year Of exprience
    private final List<Experience> expriences = new ArrayList<>(); //exprience to id of Skill

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
    public void addExprience(Experience exprience){
        expriences.add(exprience);
    }
    public List<Experience> getExpriences() {
        return expriences;
    }
}