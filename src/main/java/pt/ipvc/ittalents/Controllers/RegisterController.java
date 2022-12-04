package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.AreaType;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Exceptions.RegisterException;
import pt.ipvc.ittalents.ViewFactory;

import java.io.IOException;

public class RegisterController{
    public TextField username;
    public Button goToLoginBtn;
    public PasswordField confirmPassword;
    public PasswordField password;
    public Label infoLabel;
    public ComboBox<String> itAreaSelector;

    public void initialize() {
        try {
            Persons.loadData();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void goToLogin(){
        ViewFactory.closeStage((Stage)goToLoginBtn.getScene().getWindow());
        ViewFactory.showLogin();
    }

    private void validator() throws RegisterException {
        if(username.getText().isEmpty()) throw new RegisterException("The username is empty");
        if(!password.getText().equals(confirmPassword.getText())) throw new RegisterException("The passwords must be equals!");
        if(itAreaSelector.getValue().isEmpty()) throw new RegisterException("iTArea is empty!");
        if(password.getText().isEmpty()) throw new RegisterException("The password is empty!");
        for (Person p : Persons.data)
            if (p.getUsername().equals(username.getText()))
                throw new RegisterException("This username already exists, please choose another one.");
    }

    private void registerUser() throws RegisterException {
        this.validator();
        Person person = new Person(username.getText(), password.getText(), AreaType.valueOf(itAreaSelector.getValue()));
        Persons.data.add(person);
        Persons.logedPerson = person;
        try{
            Persons.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void submitRegister() {
        try {
            this.registerUser();
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(0, 1, 0));
            infoLabel.setText("User successfully registered!");
        }catch(RegisterException e){
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(1, 0, 0));
            infoLabel.setText(e.getMessage());
        }
    }
}
