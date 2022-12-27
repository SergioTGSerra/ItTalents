package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Controllers.Professional.Components.ExprienceItemController;
import pt.ipvc.ittalents.Backend.Exprience;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class ViewExpriencesController {
    public VBox vbox;
    public Label title;
    public static int skillId;

    private String getSkillName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }
    public void initialize() {
        title.setText(Persons.loged.getUsername() + "'s Experiences for Skill " + getSkillName(skillId));
        updateList();
    }
    public void goToDashboard() {
        ViewFactory.closeStage((Stage)vbox.getScene().getWindow());
        ProfessionalRoutes.showDashboard();
    }
    private void updateList() {
        for (Exprience exp : ((Professional) Persons.loged).getExpriences()){
            if(exp.getIdSkill() == skillId){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Professional/Components/ExprienceItem.fxml"));
                try {
                    AnchorPane pane = fxmlLoader.load();
                    ExprienceItemController eic = fxmlLoader.getController();
                    eic.setData(exp);
                    vbox.getChildren().add(pane);
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public void updateList(MouseEvent mouseEvent) {
        vbox.getChildren().removeAll(vbox.getChildren());
        updateList();
    }
}
