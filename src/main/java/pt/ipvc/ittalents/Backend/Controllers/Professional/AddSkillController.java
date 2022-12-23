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
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        this.setSkillsCombo();
    }
    private void setSkillsCombo(){
        for (Skill s : Skills.data)
            if(s.getAreaType().equals(((Professional)Persons.loged).getiTArea()) && s.isPublished())
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
        Skill newSkill = new Skill(skillName, "Teste", ((Professional)Persons.loged).getiTArea());
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
        for(int skillIdPerson : ((Professional) Persons.loged).getSkills().keySet())
            if(skillIdPerson == idSkill)
                throw new SkillException("This user already has this skill associated.");
        try{
            Integer.parseInt(yearsExprience.getText());
        }catch (NumberFormatException e) {
            throw new SkillException("The years of exprience must be a number.");
        }
        ((Professional)Persons.loged).addSkill(idSkill, Integer.parseInt(yearsExprience.getText()));
        Persons.updatePersons();
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
}