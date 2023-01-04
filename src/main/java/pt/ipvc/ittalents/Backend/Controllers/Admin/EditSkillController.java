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

public class EditSkillController {
    public Label errorMessage;
    public TextField skillDescription;
    public TextField skillName;
    public ToggleButton publicSkillBtn;
    public ComboBox<AreaType> itArea;
    public static Skill selectedSkill;

    public void initialize(){
        itArea.getItems().add(AreaType.DEVELOPER);
        itArea.getItems().add(AreaType.UXSPECIALISTS);
        itArea.getItems().add(AreaType.PRODUCTMANAGER);
        itArea.getItems().add(AreaType.PROJECTMANAGER);
        setData();
    }
    public void setData(){
        skillName.setText(selectedSkill.getName());
        skillDescription.setText(selectedSkill.getDescription());
        itArea.setValue(selectedSkill.getAreaType());
        if(selectedSkill.isPublished()){
            publicSkillBtn.setText("Yes");
            publicSkillBtn.setStyle("-fx-background-color: green");
        }else {
            publicSkillBtn.setText("No");
            publicSkillBtn.setStyle("-fx-background-color: red");
        }
    }
    public void addSkillBtn() {
        String sName = skillName.getText().substring(0,1).toUpperCase() + skillName.getText().substring(1).toLowerCase();

        selectedSkill.setName(sName);
        selectedSkill.setAreaType(itArea.getValue());
        selectedSkill.setDescription(skillDescription.getText());
        selectedSkill.setPublished(!publicSkillBtn.getText().equals("No"));
        Skills.data.set(Skills.data.indexOf(selectedSkill), selectedSkill);

        errorMessage.setVisible(true);
        errorMessage.setTextFill(Color.color(0, 1, 0));
        errorMessage.setText("Skill successfully updated!");

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
