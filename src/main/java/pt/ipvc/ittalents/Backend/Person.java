package pt.ipvc.ittalents.Backend;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private static int numberPersons = 0;
    private String username;
    private String password;
    private String name;
    private String country;
    private String email;
    private PersonType personType;
    /**
     * Contrutor Persons
     *
     * @param username  Set username of person
     * @param password  Set password of person
     */
    public Person(String username, String password, PersonType personType) {
        this.id = ++numberPersons;
        this.username = username;
        this.password = password;
        this.personType = personType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getNumberPersons() {
        return numberPersons;
    }

    public static void setNumberPersons(int numberPersons) {
        Person.numberPersons = numberPersons;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
}