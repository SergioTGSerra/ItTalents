package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Persons;

public class MySettingsController {
    public ToggleButton publicProfileBtn;
    public TextField name;
    public TextField email;
    public TextField country;
    public TextField priceHour;
    public Label priceHourLabel;
    public Label errorMessage;

    public void initialize() {
        updateAreas();
    }
    public void switchPublicProfile() {
        ((Professional)Persons.logedPerson).setPublished(!((Professional)Persons.logedPerson).isPublished());
        updateAreas();
    }
    private void updateAreas(){
        name.setText(Persons.logedPerson.getName());
        email.setText(Persons.logedPerson.getEmail());
        country.setText(Persons.logedPerson.getCountry());
        priceHour.setText(String.valueOf(((Professional)Persons.logedPerson).getPriceHour()));
        if(((Professional)Persons.logedPerson).isPublished()){
            publicProfileBtn.setStyle("-fx-background-color: green");
            publicProfileBtn.setText("Yes");
            priceHour.setVisible(true);
            priceHourLabel.setVisible(true);

        }else{
            publicProfileBtn.setStyle("-fx-background-color: red");
            publicProfileBtn.setText("No");
            priceHour.setVisible(false);
            priceHourLabel.setVisible(false);
        }
    }
    public void saveData() {
        Persons.logedPerson.setName(name.getText());
        Persons.logedPerson.setEmail(email.getText());
        Persons.logedPerson.setCountry(country.getText());
        try {
            ((Professional)Persons.logedPerson).setPriceHour(Double.parseDouble(priceHour.getText()));
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Successfully updated data");
        }catch (NumberFormatException e) {
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText("The hour ly price must be a number.");
        }
        updateAreas();
        Persons.updatePersons();
    }
}
