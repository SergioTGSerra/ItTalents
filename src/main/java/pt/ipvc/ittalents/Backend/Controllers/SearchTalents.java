package pt.ipvc.ittalents.Backend.Controllers;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.PersonType;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AdminRoutes;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

public class SearchTalents {


    public ListView<String> skills;

    public void initialize(){
        skills.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for(Skill s : Skills.data)
            skills.getItems().add(s.getName());
    }
    public void goBack() {
        if(Persons.loged.getPersonType().equals(PersonType.ADMIN))
            AdminRoutes.showDashboard();
        else if(Persons.loged.getPersonType().equals(PersonType.CLIENT))
            AdminRoutes.showDashboard();
        else if(Persons.loged.getPersonType().equals(PersonType.PROFESSIONAL))
            ProfessionalRoutes.showDashboard();
        ViewFactory.closeStage((Stage)skills.getScene().getWindow());
    }

    public void update() {
        System.out.println(skills.getSelectionModel().getSelectedItems());
    }
}
