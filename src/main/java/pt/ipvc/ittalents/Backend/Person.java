package pt.ipvc.ittalents.Backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Person implements Serializable {
    private int id;

    private static int numberPersons = 0;
    private String username;
    private String password;
    private AreaType ITArea;
    private String nome;
    private String pais;
    private String email;
    private double priceHour;
    private boolean published;
    private Map<Integer, Integer> skillsExprience = new HashMap<>();

    /**
     * Contrutor Persons
     *
     * @param username  Set username of person
     * @param password  Set password of person
     * @param ITArea    Set It Area Of person
     */
    public Person(String username, String password, AreaType ITArea) {
        this.id = ++numberPersons;
        this.username = username;
        this.password = password;
        this.ITArea = ITArea;
        this.published = false;
    }

    /**
     * @return  Return the id of user
     */
    public int getId() {
        return id;
    }

    /**
     * @return  Return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return  Return password of user
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AreaType getITArea() {
        return ITArea;
    }

    public void setITArea(AreaType ITArea) {
        this.ITArea = ITArea;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
