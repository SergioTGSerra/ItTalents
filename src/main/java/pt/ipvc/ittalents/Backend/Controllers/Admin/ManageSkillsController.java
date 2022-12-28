package pt.ipvc.ittalents.Backend.Controllers.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Controllers.Admin.Components.SkillItemController;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AdminRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class ManageSkillsController {
    public AnchorPane anchor;
    public VBox vbox;

    public void initialize(){
        updater();
    }

    public void updater(){
        for (Skill s : Skills.data){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Admin/Components/SkillItem.fxml"));
            try {
                AnchorPane pane = fxmlLoader.load();
                SkillItemController sic = fxmlLoader.getController();
                sic.setData(s);
                vbox.getChildren().add(pane);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void updateList() {
        vbox.getChildren().removeAll(vbox.getChildren());
        updater();
    }

    public void goBack() {
        ViewFactory.closeStage((Stage) anchor.getScene().getWindow());
        AdminRoutes.showDashboard();
    }

    public void addSkill() {
        AdminRoutes.showAddSkill();
    }
}
