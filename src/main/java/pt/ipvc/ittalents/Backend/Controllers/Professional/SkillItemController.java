package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.scene.control.Label;

public class SkillItemController{
    public Label skillName;
    public void setData(String name){
        skillName.setText(name);
    }
}
