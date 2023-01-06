package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.Controllers.Professional.Components.OfferItemController;
import pt.ipvc.ittalents.Backend.Offer;
import pt.ipvc.ittalents.Models.Offers;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class ViewOffersController {
    public VBox vbox;
    public void initialize() {
        try {
            Offers.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        updateList();
    }
    public void goToDashboard() {
        ViewFactory.closeStage((Stage)vbox.getScene().getWindow());
        ProfessionalRoutes.showDashboard();
    }
    private void updateList() {
        for (Offer o : Offers.data.keySet()) {
            System.out.println(o.getId());
            if (Offers.data.get(o).getId() == Persons.loged.getId()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Professional/Components/OfferItem.fxml"));
                try {
                    AnchorPane pane = fxmlLoader.load();
                    OfferItemController oic = fxmlLoader.getController();
                    oic.setData(o);
                    vbox.getChildren().add(pane);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public void updateList(MouseEvent mouseEvent) {
        vbox.getChildren().removeAll(vbox.getChildren());
        updateList();
    }
}
