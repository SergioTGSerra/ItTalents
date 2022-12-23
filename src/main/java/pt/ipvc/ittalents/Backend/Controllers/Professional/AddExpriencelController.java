package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Exceptions.ExprienceException;
import pt.ipvc.ittalents.Backend.Exceptions.SkillException;
import pt.ipvc.ittalents.Backend.Exprience;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;

public class AddExpriencelController {
    public ComboBox<String> searchSkill;
    public Label errorMessage;
    public Label endDateLabel;
    public DatePicker endDate;
    public TextField company;
    public DatePicker startDate;
    public ToggleButton switchEndDateBtn;

    public void initialize() {
        this.setSkillsCombo();
    }
    private void setSkillsCombo(){
        for (int idskill : ((Professional)Persons.loged).getSkills().keySet())
            searchSkill.getItems().add(getSkillName(idskill));
    }
    private String getSkillName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }

    private int getIdSkillName(String skill){
        for(Skill s : Skills.data)
            if(s.getName().equals(skill))
                return s.getId();
        return -1;
    }

    private void addExprience(){
        Exprience exprience = new Exprience(company.getText(), startDate.getValue(), endDate.getValue(), getIdSkillName(searchSkill.getValue()));
        ((Professional)Persons.loged).addExprience(exprience);
        Persons.updatePersons();
    }

    private void validator() throws SkillException, ExprienceException {
        if(searchSkill.getValue() == null)
            throw new SkillException("The skill name is empty!");
        if(company.getText().isEmpty())
            throw new ExprienceException("The company name is empty!");
        if(startDate.getValue() == null)
            throw new ExprienceException("The start date is empty!");
        if(switchEndDateBtn.getText().equals("No") && endDate.getValue() == null)
            throw new ExprienceException("The end date of exprience is empty!");
    }
    public void switchEndDate() {
        if(switchEndDateBtn.getText().equals("Yes")){
            switchEndDateBtn.setStyle("-fx-background-color: red");
            switchEndDateBtn.setText("No");
            endDateLabel.setVisible(true);
            endDate.setVisible(true);
        } else if(switchEndDateBtn.getText().equals("No")){
            switchEndDateBtn.setStyle("-fx-background-color: green");
            switchEndDateBtn.setText("Yes");
            endDateLabel.setVisible(false);
            endDate.setVisible(false);
        }
    }
    public void addExprienceBtn() {
        try {
            validator();
            addExprience();
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Exprience added successfully");
        }catch (ExprienceException | SkillException e){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText(e.getMessage());
        }
    }
}
