package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Controllers.Professional.Components.SkillItemController;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AuthRoutes;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class DashboardController {
    public Label usernameLabel;
    public Label iTAreaLabel;
    public VBox skillsLayout;
    public AnchorPane anchorPane;

    public void initialize() {
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        updateList();
        usernameLabel.setText(Persons.loged.getUsername());
        iTAreaLabel.setText(((Professional)Persons.loged).getiTArea().toString());
    }
    public void goToAddSkill() {
        ProfessionalRoutes.showAddSkill();
    }
    public void goToMySettings() {
        ProfessionalRoutes.showMySettings();
    }
    public void goToAddExprience() {
        ViewFactory.closeStage((Stage)anchorPane.getScene().getWindow());
        ProfessionalRoutes.showAddExprience();
    }
    public void logout() {
        ViewFactory.closeStage((Stage)anchorPane.getScene().getWindow());
        Persons.loged = null;
        AuthRoutes.showLogin();
    }
    public void updateList(MouseEvent dragEvent) {
        skillsLayout.getChildren().removeAll(skillsLayout.getChildren());
        updateList();
    }
    private void updateList() {
    for (int idskill : ((Professional) Persons.loged).getSkills().keySet()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Professional/Components/SkillItem.fxml"));
            try {
                AnchorPane pane = fxmlLoader.load();
                SkillItemController sic = fxmlLoader.getController();
                sic.setData(idskill);
                skillsLayout.getChildren().add(pane);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}