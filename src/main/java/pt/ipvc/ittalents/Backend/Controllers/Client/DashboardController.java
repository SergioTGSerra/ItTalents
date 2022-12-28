package pt.ipvc.ittalents.Backend.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AuthRoutes;
import pt.ipvc.ittalents.Routes.GenericRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class DashboardController {
    public Label usernameLabel;

    public void initialize(){
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        usernameLabel.setText(Persons.loged.getUsername());
    }

    public void logout() {
        ViewFactory.closeStage((Stage)usernameLabel.getScene().getWindow());
        Persons.loged = null;
        AuthRoutes.showLogin();
    }

    public void goToFindTalents() {
        ViewFactory.closeStage((Stage) usernameLabel.getScene().getWindow());
        GenericRoutes.showSearchTalents();
    }

    public void goToRegistProject() {
    }
}
