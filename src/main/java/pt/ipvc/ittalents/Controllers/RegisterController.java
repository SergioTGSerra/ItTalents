package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Views.ViewFactory;

public class RegisterController{

    public TextField username;
    public Button goToLoginBtn;
    public PasswordField confirmPassword;
    public PasswordField password;

    public void goToLogin(){
        ViewFactory.closeStage((Stage)goToLoginBtn.getScene().getWindow());
        ViewFactory.showLogin();
    }
}
