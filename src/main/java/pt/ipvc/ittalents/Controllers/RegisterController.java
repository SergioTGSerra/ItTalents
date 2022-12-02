package pt.ipvc.ittalents.Controllers;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.AreaType;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Data;
import pt.ipvc.ittalents.Exceptions.RegisterException;
import pt.ipvc.ittalents.ViewFactory;
public class RegisterController{
    public TextField username;
    public Button goToLoginBtn;
    public PasswordField confirmPassword;
    public PasswordField password;
    public Label infoLabel;
    public ComboBox itAreaSelector;

    public void goToLogin(){
        ViewFactory.closeStage((Stage)goToLoginBtn.getScene().getWindow());
        ViewFactory.showLogin();
    }

    private void registerUser() throws RegisterException{
        if(username.getText().isEmpty()) throw new RegisterException("The username is empty");
        if(!password.getText().equals(confirmPassword.getText())) throw new RegisterException("As passwords nao sao iguais!");
        if(itAreaSelector.getValue() == null) throw new RegisterException("iTArea is empty!");
        if(password.getText().isEmpty()) throw new RegisterException("The password is empty!");

        boolean exist = false;
        for (Person p : Data.persons) {
            if (p.getUsername().equals(username.getText()))
                exist = true;
        }

        if(exist)
            throw new RegisterException("This username already exists, please choose another one.");
        else{
            Person person = new Person(username.getText(), password.getText(), AreaType.valueOf((String) itAreaSelector.getValue()));
            Data.persons.add(person);
            Data.logedUserId = person.getId();
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(0, 1, 0));
            infoLabel.setText("User successfully registered!");
        }
    }

    public void submitRegister() {
        try {
            this.registerUser();
        }catch(RegisterException e){
            infoLabel.setVisible(true);
            infoLabel.setTextFill(Color.color(1, 0, 0));
            infoLabel.setText(e.getMessage());
        }
    }
}
