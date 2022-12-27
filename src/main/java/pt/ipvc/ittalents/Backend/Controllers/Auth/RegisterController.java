package pt.ipvc.ittalents.Backend.Controllers.Auth;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.AreaType;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.PersonType;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Backend.Exceptions.RegisterException;
import pt.ipvc.ittalents.Routes.AuthRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class RegisterController{
    public TextField username;
    public Button goToLoginBtn;
    public PasswordField confirmPassword;
    public PasswordField password;
    public Label infoLabel;
    public ComboBox<String> itAreaSelector;
    public ComboBox<String> personType;

    public void initialize() {
        try {
            Persons.loadData();
            boolean exit = false;
            for(Person p : Persons.data)
                if(p.getUsername().equals("admin")) {
                    exit = true;
                    break;
                }
            if(!exit){
                Person p = new Person("admin", "admin", PersonType.ADMIN);
                Persons.data.add(p);
                Persons.saveData();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void goToLogin(){
        ViewFactory.closeStage((Stage)goToLoginBtn.getScene().getWindow());
        AuthRoutes.showLogin();
    }
    private void validator() throws RegisterException {
        if(username.getText().isEmpty()) throw new RegisterException("The username is empty");
        if(password.getText().isEmpty()) throw new RegisterException("The password is empty!");
        if(!password.getText().equals(confirmPassword.getText())) throw new RegisterException("The passwords must be equals!");
        if(personType.getValue() == null) throw new RegisterException("PersonType is empty!");
        if(itAreaSelector.getValue() == null && personType.getValue().equals("PROFESSIONAL")) throw new RegisterException("iTArea is empty!");
        for (Person p : Persons.data)
            if (p.getUsername().equals(username.getText()))
                throw new RegisterException("This username already exists, please choose another one.");
    }
    private void registerUser() throws RegisterException {
        this.validator();
        Person person = null;
        if (personType.getValue().equals("PROFESSIONAL"))
            person = new Professional(username.getText(), password.getText(), PersonType.valueOf(personType.getValue()), AreaType.valueOf(itAreaSelector.getValue()));
        else if (personType.getValue().equals("CLIENT"))
            person = new Person(username.getText(), password.getText(), PersonType.valueOf(personType.getValue()));
        Persons.data.add(person);
        Persons.loged = person;
        try{
            Persons.saveData();
        }catch (IOException e){
            System.out.println(e.getMessage());
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
    public void switchTypePerson() {
        if (personType.getValue().equals("PROFESSIONAL"))
            itAreaSelector.setVisible(true);
        if (personType.getValue().equals("CLIENT"))
            itAreaSelector.setVisible(false);
    }
}
