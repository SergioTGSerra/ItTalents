package pt.ipvc.ittalents.Backend.Controllers.Client;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Exceptions.OfferException;
import pt.ipvc.ittalents.Backend.Offer;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Offers;

import java.io.IOException;

public class MakeOfferController {
    public static Professional professional;
    public Label title;
    public AnchorPane makeOffer;
    public TextArea description;
    public TextField price;
    public Label error;
    public TextField requiredHours;
    private int cont;

    public void initialize(){
        title.setText("Make a Offer to " + professional.getName());
    }
    private void validator() throws OfferException {
        if(description.getText().isEmpty()) throw new OfferException("Description is empty!");
        if(cont != 0) throw new OfferException("Offer already maked!");
        try {
           Double.parseDouble(price.getText());
        }catch (NumberFormatException e) {
            throw new OfferException("Price must be a double number!");
        }
        try {
            Integer.parseInt(requiredHours.getText());
        }catch (NumberFormatException e){
            throw new OfferException("Required hours must be a integer!");
        }
    }
    private void createOffer(){
        Offer offer = new Offer(description.getText(), Double.parseDouble(price.getText()), Integer.parseInt(requiredHours.getText()));
        Offers.data.put(offer, professional);
        try {
            Offers.saveData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void makeOffer() {
        try {
            validator();
            createOffer();
            error.setVisible(true);
            error.setTextFill(Color.color(0, 1, 0));
            error.setText("Offer made successfully!");
            cont++;
        }catch (OfferException e){
            error.setVisible(true);
            error.setTextFill(Color.color(1, 0, 0));
            error.setText(e.getMessage());
        }
    }
}
