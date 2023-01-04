package pt.ipvc.ittalents.Backend.Controllers.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Controllers.Admin.Components.UserItemController;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Routes.AdminRoutes;
import pt.ipvc.ittalents.Routes.AuthRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class ManageUsersController {
    public AnchorPane anchor;
    public VBox vbox;

    public void initialize(){
        updater();
    }

    private void updater() {
        for (Person p : Persons.data){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Admin/Components/UserItem.fxml"));
            try {
                AnchorPane pane = fxmlLoader.load();
                UserItemController uic = fxmlLoader.getController();
                uic.setData(p);
                vbox.getChildren().add(pane);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void goBack() {
        ViewFactory.closeStage((Stage) anchor.getScene().getWindow());
        AdminRoutes.showDashboard();
    }
    public void addUser() {
        AuthRoutes.showRegister();
    }
    public void updateList() {
        vbox.getChildren().removeAll(vbox.getChildren());
        updater();
    }
}
