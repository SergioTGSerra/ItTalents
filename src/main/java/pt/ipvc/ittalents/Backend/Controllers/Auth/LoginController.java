package pt.ipvc.ittalents.Backend.Controllers.Auth;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.PersonType;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Backend.Exceptions.LoginException;
import pt.ipvc.ittalents.Routes.AuthRoutes;
import pt.ipvc.ittalents.Routes.ClientRoutes;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.util.Objects;

public abstract class LoginController {
    public TextField username;
    public PasswordField password;
    public Button goToRegisterBtn;
    public Button doLoginBtn;
    public Label infoLabel;
    public void goToRegister() {
        ViewFactory.closeStage((Stage)goToRegisterBtn.getScene().getWindow());
        AuthRoutes.showRegister();
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
        Persons.loged = this.validator();
        ViewFactory.closeStage((Stage)doLoginBtn.getScene().getWindow());
        if(Persons.loged.getPersonType().equals(PersonType.CLIENT))
            ClientRoutes.showDashboard();
        if(Persons.loged.getPersonType().equals(PersonType.PROFESSIONAL))
            ProfessionalRoutes.showDashboard();
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