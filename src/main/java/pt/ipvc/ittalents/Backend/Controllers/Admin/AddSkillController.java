package pt.ipvc.ittalents.Backend.Controllers.Admin;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.AreaType;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Skills;

import java.io.IOException;

public class AddSkillController {
    public Label errorMessage;
    public TextField skillDescription;
    public TextField skillName;
    public ToggleButton publicSkillBtn;
    public ComboBox<AreaType> itArea;

    public void initialize(){
        itArea.getItems().add(AreaType.DEVELOPER);
        itArea.getItems().add(AreaType.UXSPECIALISTS);
        itArea.getItems().add(AreaType.PRODUCTMANAGER);
        itArea.getItems().add(AreaType.PROJECTMANAGER);
    }
    public void addSkillBtn() {
        String sName = skillName.getText().substring(0,1).toUpperCase() + skillName.getText().substring(1).toLowerCase();
        for (Skill s : Skills.data)
            if(s.getName().equals(sName)){
                errorMessage.setVisible(true);
                errorMessage.setTextFill(Color.color(1, 0, 0));
                errorMessage.setText("This skills already exist!");
                return;
            }
        Skill newSkill = new Skill(sName, skillDescription.getText(), itArea.getValue());
        if(publicSkillBtn.getText().equals("No")){
            newSkill.setPublished(false);
        }else if(publicSkillBtn.getText().equals("Yes")){
            newSkill.setPublished(true);
        }
        Skills.data.add(newSkill);
        errorMessage.setVisible(true);
        errorMessage.setTextFill(Color.color(0, 1, 0));
        errorMessage.setText("Skill successfully added!");
        try {
            Skills.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void publicSkillSwicth() {
        if(publicSkillBtn.getText().equals("No")){
            publicSkillBtn.setText("Yes");
            publicSkillBtn.setStyle("-fx-background-color: green");
        }else if(publicSkillBtn.getText().equals("Yes")){
            publicSkillBtn.setText("No");
            publicSkillBtn.setStyle("-fx-background-color: red");
        }
    }
}
