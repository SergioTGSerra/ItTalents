package pt.ipvc.ittalents.Backend.Controllers.Professional.Components;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Controllers.Professional.ViewExpriencesController;
import pt.ipvc.ittalents.Backend.Exprience;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

public class SkillItemController{
    public Label errorMessage;
    private int idSkill;
    private int cont;
    public Label skillName;
    public Label skillDesc;
    public Label yearsExprience;

    public void setData(int idSkill){
        for (Skill skill : Skills.data)
            if(skill.getId() == idSkill){
                skillName.setText(skill.getName());
                skillDesc.setText(skill.getDescription());
                yearsExprience.setText(((Professional)Persons.loged).getSkills().get(idSkill).toString() + " years of exprience");
                this.idSkill = idSkill;
                break;
            }
    }

    public void removeSkill() {
        cont++;
        for(Exprience e : ((Professional)Persons.loged).getExpriences())
            if(e.getIdSkill() == idSkill){
                errorMessage.setVisible(true);
                errorMessage.setTextFill(Color.color(1, 0, 0));
                errorMessage.setText("There are experiences associated with this skill!");
                return;
            }

        if(cont == 1){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText("Press again to remove!");
        }else if(cont == 2){
            ((Professional)Persons.loged).getSkills().remove(this.idSkill);
            Persons.updatePersons();
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Skill removed successfully!");
        }
    }

    public void viewExpriences() {
        ViewFactory.closeStage((Stage) skillName.getScene().getWindow());
        ViewExpriencesController.skillId = idSkill;
        ProfessionalRoutes.showViewExpriences();
    }
}
