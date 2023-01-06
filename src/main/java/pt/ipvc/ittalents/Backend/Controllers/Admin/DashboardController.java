package pt.ipvc.ittalents.Backend.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AdminRoutes;
import pt.ipvc.ittalents.Routes.AuthRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class DashboardController {
    public Label usernameLabel;
    public Label nUsers;
    public Label nSkills;

    public void initialize(){
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        usernameLabel.setText(Persons.loged.getUsername());
        nUsers.setText("Number of Users: " + Persons.data.size());
        nSkills.setText("Number of Skills: " + Skills.data.size());
    }
    public void manageSkills() {
        ViewFactory.closeStage((Stage) usernameLabel.getScene().getWindow());
        AdminRoutes.showManageSkills();
    }
    public void logout() {
        ViewFactory.closeStage((Stage)usernameLabel.getScene().getWindow());
        Persons.loged = null;
        AuthRoutes.showLogin();
    }
    public void goToManageUsers() {
        ViewFactory.closeStage((Stage) usernameLabel.getScene().getWindow());
        AdminRoutes.showManageUsers();
    }

    public void goToReports() {
        ViewFactory.closeStage((Stage) usernameLabel.getScene().getWindow());
        AdminRoutes.showReports();
    }
}
