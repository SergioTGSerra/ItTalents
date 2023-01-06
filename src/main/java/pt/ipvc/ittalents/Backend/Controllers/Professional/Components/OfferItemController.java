package pt.ipvc.ittalents.Backend.Controllers.Professional.Components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Offer;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Offers;
import pt.ipvc.ittalents.Models.Persons;

import java.io.IOException;
import java.time.LocalDate;

public class OfferItemController {
    public Button b1;
    public Button b2;
    public Label acceptedLabel;
    public Label errorMessage;
    public Label description;
    public Label price;
    public Label hours;
    private Offer offer;
    private int acceptCount;
    private int removeCount;
    public void setData(Offer o){
        if(o.isAcepted()){
            b1.setVisible(false);
            b2.setVisible(false);
            acceptedLabel.setVisible(true);
        }
        this.offer = o;
        description.setText("Description: " + o.getText());
        price.setText("Price: " + o.getPrice());
        hours.setText("Hours: " + o.getRequiredHours());
    }
    public void acceptOffer() {
        removeCount = 0;
        acceptCount++;
        if(acceptCount == 1){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Press again to accept offer!");
        }else if(acceptCount == 2){
            offer.setAcepted(true);
            offer.setDateAccepted(LocalDate.now());
            Offers.data.put(offer, ((Professional)Persons.loged));
            try {
                Offers.saveData();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Offer accepted successfully!");
        }
    }
    public void removeOffer() {
        acceptCount = 0;
        removeCount++;
        if(removeCount == 1){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText("Press again to remove!");
        }else if(removeCount == 2){
            Offers.data.remove(offer);
            try {
                Offers.saveData();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Offer removed successfully!");
        }
    }
}
