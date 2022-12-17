package pt.ipvc.ittalents.Controllers;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Exceptions.SkillException;
import pt.ipvc.ittalents.Models.SkillsPersons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DashboardController {
    public Label usernameLabel;
    public Label iTAreaLabel;
    public ComboBox<String> searchSkill;
    public ListView<String> mySkillsList;

    public void initialize() {
        try{
            Skills.loadData();
            SkillsPersons.loadData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setSkillsCombo();
        this.setSkillsList();
        usernameLabel.setText(Persons.logedPerson.getUsername());
        iTAreaLabel.setText(Persons.logedPerson.getITArea().toString());
    }

    private void setSkillsList(){
        for (int idSkill : SkillsPersons.data.keySet()){
            List<Integer> persons = SkillsPersons.data.get(idSkill);
            for (int idPerson : persons)
                if(idPerson == Persons.logedPerson.getId())
                    mySkillsList.getItems().add(getStringName(idSkill));
        }
    }

    private String getStringName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }

    private void setSkillsCombo(){
        for (Skill s : Skills.data)
            if(s.getAreaType().equals(Persons.logedPerson.getITArea()))
                searchSkill.getItems().add(s.getName());
    }

    private void verifySkill() throws SkillException {
        String skillName = searchSkill.getValue().substring(0,1).toUpperCase() + searchSkill.getValue().substring(1).toLowerCase();
        for (Skill s : Skills.data)
            if(s.getName().equals(skillName)){
                this.associateSkillPerson(s.getId());
                return;
            }
        Skill skill = new Skill(skillName, "Teste", Persons.logedPerson.getITArea());
        Skills.data.add(skill);
        this.associateSkillPerson(skill.getId());
        try {
            Skills.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void associateSkillPerson(int idSkill){
        List<Integer> persons = SkillsPersons.data.get(idSkill);
        if(persons == null) {
            persons = new ArrayList<>();
            persons.add(Persons.logedPerson.getId());
        }else{
            persons.add(Persons.logedPerson.getId());
        }
        SkillsPersons.data.put(idSkill, persons);
        mySkillsList.getItems().add(getStringName(idSkill));
        try {
            SkillsPersons.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addSkillBtn() {
        try {
            this.verifySkill();
        }catch (SkillException e){
            e.printStackTrace();
        }
    }
}
