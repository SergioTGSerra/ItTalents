package pt.ipvc.ittalents.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {

    public static void showLogin() {
        createStage("Login");
    }

    public static void showRegister() {
        createStage("Register");
    }

    public static void closeStage(Stage stage){
        stage.close();
    }

    private static void createStage(String file){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.class.getResource("/pt/ipvc/ittalents/Fxml/" + file + ".fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("ItTalents - " + file);
        stage.setScene(scene);
        stage.show();
    }

}
