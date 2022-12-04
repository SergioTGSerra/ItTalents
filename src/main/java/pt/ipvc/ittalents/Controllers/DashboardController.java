package pt.ipvc.ittalents.Controllers;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Exceptions.SkillException;

import java.io.IOException;
import java.util.Map.Entry;

public class DashboardController {
    public Label usernameLabel;
    public Label iTAreaLabel;
    public ComboBox<String> searchSkill;
    public ListView<String> mySkillsList;

    public void initialize() {
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setSkillsCombo();
        this.setSkillsList();
        usernameLabel.setText(Persons.logedPerson.getUsername());
        iTAreaLabel.setText(Persons.logedPerson.getITArea().toString());
    }

    private void setSkillsList(){
        for (Skill s : Skills.data.keySet())
            if(Skills.data.get(s).getId() == Persons.logedPerson.getId())
                System.out.println(s.getName());
    }

    private void setSkillsCombo(){
        for (Entry<Skill, Person> entry : Skills.data.entrySet())
            if(Persons.logedPerson.getITArea() == entry.getValue().getITArea())
                searchSkill.getItems().add(entry.getKey().getName());
    }

    private void addSkill() throws SkillException {
        String skillName = searchSkill.getValue().substring(0,1).toUpperCase() + searchSkill.getValue().substring(1).toLowerCase();
        for (Skill s : Skills.data.keySet()) {
            if(s.getName().equals(skillName))
                throw new SkillException("This skill already exists.");
        }
        Skill skill = new Skill(skillName, "Teste", Persons.logedPerson.getITArea());
        Skills.data.put(skill, Persons.logedPerson);
        try {
            Skills.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addSkillBtn() {
        try {
            this.addSkill();
        }catch ( SkillException e){
            System.out.println(e.getMessage());
        }
    }
}
