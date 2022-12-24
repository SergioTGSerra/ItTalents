package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.scene.control.Label;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;

public class SkillItemController{
    public Label skillName;
    public Label skillDesc;
    public Label yearsExprience;

    public void setData(int idSkill){
        for (Skill skill : Skills.data)
            if(skill.getId() == idSkill){
                skillName.setText(skill.getName());
                skillDesc.setText(skill.getDescription());
                yearsExprience.setText(((Professional)Persons.loged).getSkills().get(idSkill).toString() + " years of exprience");
                break;
            }
    }
}
