package pt.ipvc.ittalents.Backend.Controllers.Professional.Components;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pt.ipvc.ittalents.Backend.Exprience;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Models.Persons;

public class ExprienceItemController {
    public Label companyName;
    public Label startDate;
    public Label endDate;
    public Label errorMessage;
    private Exprience exprience;
    private int cont;
    public void setData(Exprience e){
        this.exprience = e;
        companyName.setText(e.getCompany());
        startDate.setText(e.getStartDate().toString());
        if(e.getEndDate() == null)
            endDate.setText("Currently working at this company.");
        else
            endDate.setText(e.getEndDate().toString());
    }
    public void removeExprience() {
        cont++;
        if(cont == 1){
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(1, 0, 0));
            errorMessage.setText("Press again to remove!");
        }else if(cont == 2){
            ((Professional)Persons.loged).getExpriences().remove(this.exprience);
            Persons.updatePersons();
            errorMessage.setVisible(true);
            errorMessage.setTextFill(Color.color(0, 1, 0));
            errorMessage.setText("Exprience removed successfully!");
        }
    }
}
