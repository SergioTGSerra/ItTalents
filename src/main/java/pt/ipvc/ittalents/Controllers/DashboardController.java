package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Exceptions.SkillException;
import pt.ipvc.ittalents.ViewFactory;

import java.io.IOException;
public class DashboardController {
    public Label usernameLabel;
    public Label iTAreaLabel;
    public ComboBox<String> searchSkill;
    public ListView<String> mySkillsList;
    public TextField yearsExprience;
    public Label errorMessage;

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
        for (int idskill : Persons.logedPerson.getSkillsExprience().keySet()){
            mySkillsList.getItems().add(getSkillName(idskill));
            System.out.println(idskill);
        }
    }

    private String getSkillName(int skillId){
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
        if(searchSkill.getValue().isEmpty()) throw new SkillException("The skill name is empty.");
        String skillName = searchSkill.getValue().substring(0,1).toUpperCase() + searchSkill.getValue().substring(1).toLowerCase();
        for (Skill s : Skills.data)
            if(s.getName().equals(skillName)){
                this.associateSkillPerson(s.getId());
                return;
            }
        Skill newSkill = new Skill(skillName, "Teste", Persons.logedPerson.getITArea());
        Skills.data.add(newSkill);
        this.associateSkillPerson(newSkill.getId());
        try {
            Skills.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void associateSkillPerson(int idSkill) throws SkillException {
        if(yearsExprience.getText().isEmpty()) throw new SkillException("The years of exprience is empty.");
        try{
            Integer.parseInt(yearsExprience.getText());
        }catch (NumberFormatException e) {
            e.printStackTrace();
            throw new SkillException("The years of exprience must be a number.");
        }
        Persons.logedPerson.addSkill(idSkill, Integer.parseInt(yearsExprience.getText()));
        mySkillsList.getItems().add(getSkillName(idSkill));
        this.updatePersons();
    }

    private void updatePersons(){
        Persons.data.set(Persons.data.indexOf(Persons.logedPerson), Persons.logedPerson);
        try {
            Persons.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addSkillBtn() {
        try {
            this.verifySkill();
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Skill added successfully");
        }catch (SkillException e){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText(e.getMessage());
        }
    }

    public void goToMySettings() {
        ViewFactory.showMySettings();
    }

    public void removeSkill() {
        if(mySkillsList.getSelectionModel().getSelectedItem().isEmpty())
            return;
        for(int skillId : Persons.logedPerson.getSkillsExprience().keySet())
            if(mySkillsList.getSelectionModel().getSelectedItem().equals(getSkillName(skillId))){
                Persons.logedPerson.getSkillsExprience().remove(skillId);
                break;
            }
        mySkillsList.getItems().remove(mySkillsList.getSelectionModel().getSelectedItem());
        this.updatePersons();
    }
}
