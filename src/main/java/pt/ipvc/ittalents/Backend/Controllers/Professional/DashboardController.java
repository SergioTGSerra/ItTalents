package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Backend.Exceptions.SkillException;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;

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
            System.out.println(e.getMessage());
        }
        this.setSkillsCombo();
        this.setSkillsList();
        usernameLabel.setText(Persons.loged.getUsername());
        iTAreaLabel.setText(((Professional)Persons.loged).getiTArea().toString());
    }
    private void setSkillsList(){
        for (int idskill : ((Professional)Persons.loged).getSkillsExprience().keySet()){
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
        for(int skillIdPerson : ((Professional)Persons.loged).getSkillsExprience().keySet())
            if(skillIdPerson == idSkill)
                throw new SkillException("This user already has this skill associated.");
        try{
            Integer.parseInt(yearsExprience.getText());
        }catch (NumberFormatException e) {
            e.printStackTrace();
            throw new SkillException("The years of exprience must be a number.");
        }
        ((Professional)Persons.loged).addSkill(idSkill, Integer.parseInt(yearsExprience.getText()));
        mySkillsList.getItems().add(getSkillName(idSkill));
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
    public void goToMySettings() {
        ProfessionalRoutes.showMySettings();
    }
    public void removeSkill() {
        if(mySkillsList.getSelectionModel().getSelectedItem() == null)
            return;
        for(int skillId : ((Professional)Persons.loged).getSkillsExprience().keySet())
            if(mySkillsList.getSelectionModel().getSelectedItem().equals(getSkillName(skillId))){
                ((Professional)Persons.loged).getSkillsExprience().remove(skillId);
                break;
            }
        mySkillsList.getItems().remove(mySkillsList.getSelectionModel().getSelectedItem());
        Persons.updatePersons();
    }
}
