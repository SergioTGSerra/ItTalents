package pt.ipvc.ittalents.Backend;

import java.util.HashMap;
import java.util.Map;

public class Professional extends Person {
    private AreaType iTArea;
    private double priceHour;
    private boolean published;
    private Map<Integer, Integer> skillsExprience = new HashMap<>();

    public Professional(String username, String password, PersonType personType, AreaType iTArea) {
        super(username, password, personType);
        this.iTArea = iTArea;
    }

    public AreaType getiTArea() {
        return iTArea;
    }

    public void setiTArea(AreaType iTArea) {
        this.iTArea = iTArea;
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
        skillsExprience.put(skill, yearsExprience);
    }
    public Map<Integer, Integer> getSkillsExprience() {
        return skillsExprience;
    }
}
