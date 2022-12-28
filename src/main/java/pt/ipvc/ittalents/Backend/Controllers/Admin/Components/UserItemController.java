package pt.ipvc.ittalents.Backend.Controllers.Admin.Components;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Models.Persons;

public class UserItemController {
    public Label username;
    public Label info;
    public Label error;
    private Person p;
    private int cont;

    public void setData(Person p){
        this.p = p;
        username.setText(p.getUsername());
        if(p.getName()!=null && p.getEmail()!=null && p.getCountry()!=null)
            info.setText("Name: " + p.getName() + " Email: " + p.getEmail() + " Country: " + p.getCountry() + " Type: " + p.getPersonType().toString());
    }

    public void removeUser() {
        cont++;
        if (Persons.loged.getUsername().equals(p.getUsername())){
            error.setVisible(true);
            error.setTextFill(Color.color(1, 0, 0));
            error.setText("Impossible to remove logged-in user!");
            return;
        }
        if(cont == 1){
            error.setVisible(true);
            error.setTextFill(Color.color(1, 0, 0));
            error.setText("Press again to remove!");
        }else if(cont == 2){
            Persons.data.remove(p);
            Persons.updatePersons();
            error.setVisible(true);
            error.setTextFill(Color.color(0, 1, 0));
            error.setText("User removed successfully!");
        }
    }
}
