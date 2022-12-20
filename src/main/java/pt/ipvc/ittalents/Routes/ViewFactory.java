package pt.ipvc.ittalents.Routes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ViewFactory {
    public static void closeStage(Stage stage){
        stage.close();
    }
    public static void createStage(String file){
        FXMLLoader fxmlLoader = new FXMLLoader(AuthRoutes.class.getResource("/pt/ipvc/ittalents/Fxml" + file + ".fxml"));
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
