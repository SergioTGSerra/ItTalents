package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Data;
import pt.ipvc.ittalents.Exceptions.DashboardException;

public class DashboardController {
    public Label usernameLabel;
    public Label iTAreaLabel;
    public ListView listSkills;

    public void initialize() {
        try {
            usernameLabel.setText(this.getPerson().getUsername());
            iTAreaLabel.setText(this.getPerson().getITArea().toString());
        }catch (DashboardException e){
            usernameLabel.setText(e.getMessage());
        }
    }

    private Person getPerson() throws DashboardException{
        for (Person p : Data.persons) {
            if(p.getId() == Data.logedUserId)
                return p;
        }
        throw new DashboardException("Error user not found!");
    }
}
