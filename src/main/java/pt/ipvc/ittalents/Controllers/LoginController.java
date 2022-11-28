package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Views.ViewFactory;

public class LoginController {
    public TextField username;
    public PasswordField password;
    public Button goToRegisterBtn;

    public void goToRegister() {
        ViewFactory.closeStage((Stage)goToRegisterBtn.getScene().getWindow());
        ViewFactory.showRegister();
    }
}
