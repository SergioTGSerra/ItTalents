package pt.ipvc.ittalents;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        ViewFactory.showRegister();
    }
}