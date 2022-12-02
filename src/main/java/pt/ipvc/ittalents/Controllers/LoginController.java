package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Data;
import pt.ipvc.ittalents.Exceptions.LoginException;
import pt.ipvc.ittalents.Exceptions.RegisterException;
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

    private void doLogin() throws LoginException {
        if(username.getText().isEmpty()) throw new LoginException("The username is empty");
        if(password.getText().isEmpty()) throw new LoginException("The password is empty!");

        boolean exit = false;
        for (Person p : Data.persons) {
            if(Objects.equals(username.getText(), p.getUsername()) && Objects.equals(password.getText(), p.getPassword()))
                exit = true;
        }

        if(exit){
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(0, 1, 0));
            infoLabel.setText("Login com sucesso!");
        }else {
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(1,0,0));
            infoLabel.setText("Username ou password errados!");
        }
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
