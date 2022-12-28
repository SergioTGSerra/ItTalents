package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Exceptions.SkillException;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;

import java.io.IOException;

public class AddSkillController {
    public ComboBox<String> searchSkill;
    public Label errorMessage;
    public TextField yearsExprience;

    public void initialize() {
        this.setSkillsCombo();
    }
    private void setSkillsCombo(){
        for (Skill s : Skills.data)
            if(s.getAreaType().equals(((Professional)Persons.loged).getiTArea()) && s.isPublished())
                searchSkill.getItems().add(s.getName());
    }
    private int getIdSkillName(String skill){
        for(Skill s : Skills.data)
            if(s.getName().equals(skill))
                return s.getId();
        return -1;
    }
    private void verifySkill() throws SkillException {
        if(searchSkill.getValue().isEmpty()) throw new SkillException("The skill name is empty.");
        if(yearsExprience.getText().isEmpty()) throw new SkillException("The years of exprience is empty.");
        try{
            Integer.parseInt(yearsExprience.getText());
        }catch (NumberFormatException e) {
            throw new SkillException("The years of exprience must be a number.");
        }
        for(int skillIdPerson : ((Professional) Persons.loged).getSkills().keySet())
            if(skillIdPerson == getIdSkillName(searchSkill.getValue().substring(0,1).toUpperCase() + searchSkill.getValue().substring(1).toLowerCase()))
                throw new SkillException("This user already has this skill associated.");
    }
    private void associateSkillPerson() throws SkillException {
        String skillName = searchSkill.getValue().substring(0,1).toUpperCase() + searchSkill.getValue().substring(1).toLowerCase();
        boolean exit = false;
        int idSkill = -1;
        for (Skill s : Skills.data)
            if(s.getName().equals(skillName)){
                exit = true;
                idSkill = s.getId();
                break;
            }
        if(exit){
            ((Professional)Persons.loged).addSkill(idSkill, Integer.parseInt(yearsExprience.getText()));
        }else {
            Skill newSkill = new Skill(skillName, null, ((Professional)Persons.loged).getiTArea());
            Skills.data.add(newSkill);
            ((Professional)Persons.loged).addSkill(newSkill.getId(), Integer.parseInt(yearsExprience.getText()));
        }
        try {
            Skills.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
        Persons.updatePersons();
    }
    public void addSkillBtn() {
        try {
            verifySkill();
            associateSkillPerson();
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Skill added successfully");
        }catch (SkillException e){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText(e.getMessage());
        }
    }
}