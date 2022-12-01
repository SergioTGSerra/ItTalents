package pt.ipvc.ittalents.Backend;

import pt.ipvc.ittalents.Data;

public class Person {
    private int id;
    private String username;
    private String password;
    private AreaType ITArea;

    /**
     * Contrutor Persons
     *
     * @param username  Set username of person
     * @param password  Set password of person
     * @param ITArea    Set It Area Of person
     */
    public Person(String username, String password, AreaType ITArea) {
        this.id += 1;
        this.username = username;
        this.password = password;
        this.ITArea = ITArea;
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
}
