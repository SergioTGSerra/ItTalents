package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Persons;

import java.util.Locale;

public class MySettingsController {
    public ToggleButton publicProfileBtn;
    public TextField name;
    public TextField email;
    public TextField priceHour;
    public Label priceHourLabel;
    public Label errorMessage;
    public ComboBox<String> country;

    public void initialize() {
        updateAreas();
    }
    public void switchPublicProfile() {
        if(publicProfileBtn.getText().equals("No")){
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
    private void updateAreas(){
        name.setText(Persons.loged.getName());
        email.setText(Persons.loged.getEmail());

        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1)
            country.getItems().add(countrylist);
        country.setValue(Persons.loged.getCountry());

        priceHour.setText(String.valueOf(((Professional)Persons.loged).getPriceHour()));
        if(((Professional)Persons.loged).isPublished()){
            publicProfileBtn.setStyle("-fx-background-color: green");
            publicProfileBtn.setText("Yes");
            priceHour.setVisible(true);
            priceHourLabel.setVisible(true);

        }
    }
    public void saveData() {
        Persons.loged.setName(name.getText());
        Persons.loged.setEmail(email.getText());
        Persons.loged.setCountry(country.getValue());
        ((Professional)Persons.loged).setPublished(!((Professional)Persons.loged).isPublished());
        try {
            ((Professional)Persons.loged).setPriceHour(Double.parseDouble(priceHour.getText()));
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
