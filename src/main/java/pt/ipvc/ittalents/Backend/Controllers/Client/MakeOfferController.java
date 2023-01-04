package pt.ipvc.ittalents.Backend.Controllers.Client;

import javafx.scene.control.Label;
import pt.ipvc.ittalents.Backend.Professional;

public class MakeOfferController {
    public static Professional professional;
    public Label label;

    public void initialize(){
        label.setText("Make a Offer to " + professional.getName());
    }
}
