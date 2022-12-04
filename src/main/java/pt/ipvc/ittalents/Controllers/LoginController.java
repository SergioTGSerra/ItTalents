package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Exceptions.LoginException;
import pt.ipvc.ittalents.ViewFactory;

import java.util.Objects;

public class LoginController {
    public TextField username;
    public PasswordField password;
    public Button goToRegisterBtn;
    public Button doLoginBtn;
    public Label infoLabel;
    public void goToRegister() {
        ViewFactory.closeStage((Stage)goToRegisterBtn.getScene().getWindow());
        ViewFactory.showRegister();
    }
    private Person validator() throws LoginException {
        if(username.getText().isEmpty()) throw new LoginException("The username is empty");
        if(password.getText().isEmpty()) throw new LoginException("The password is empty!");
        for (Person p : Persons.data)
            if(Objects.equals(username.getText(), p.getUsername()) && Objects.equals(password.getText(), p.getPassword()))
                return p;
        throw new LoginException("User not found!");
    }
    private void doLogin() throws LoginException {
        Persons.logedPerson = this.validator();
        ViewFactory.closeStage((Stage)doLoginBtn.getScene().getWindow());
        ViewFactory.showDashboard();
    }
    public void submitLogin() {
        try{
            this.doLogin();
        }catch (LoginException e){
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(1,0,0));
            infoLabel.setText(e.getMessage());
        }
    }
}