package pt.ipvc.ittalents.Backend.Controllers.Admin.Components;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Controllers.Admin.EditSkillController;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AdminRoutes;

public class SkillItemController {
    public Label error;
    public Label info;
    public Label skillName;
    private Skill skill;
    private int cont;
    private int nAssPerson; //numbers of people with this skill added

    public void setData(Skill s){
        this.skill = s;
        skillName.setText(s.getName());
        for(Person p : Persons.data)
            if (p instanceof Professional)
                if(((Professional) p).getSkills().containsKey(s.getId()))
                    nAssPerson++;
        if(s.getDescription()==null)
            info.setText("Area: "+ s.getAreaType().toString() + ", Published: " + s.isPublished() + ", Associated with " + nAssPerson + " professionals");
        else
            info.setText("Description: " + s.getDescription() + ", Area: "+ s.getAreaType().toString() + ", Published: " + s.isPublished() + ", Associated with " + nAssPerson + " professionals" );
    }

    public void removeSkill() {
        cont++;
        if(nAssPerson != 0){
            error.setVisible(true);
            error.setTextFill(Color.color(1, 0, 0));
            error.setText("Skill is associated with a professional!");
        }
        else if(cont == 1){
            error.setVisible(true);
            error.setTextFill(Color.color(1, 0, 0));
            error.setText("Press again to remove!");
        }else if(cont == 2){
            Skills.data.remove(skill);
            error.setVisible(true);
            error.setTextFill(Color.color(0, 1, 0));
            error.setText("Skill removed successfully!");
        }
    }

    public void goToManageSkill() {
        EditSkillController.selectedSkill = skill;
        AdminRoutes.showEditSkill();
    }
}
