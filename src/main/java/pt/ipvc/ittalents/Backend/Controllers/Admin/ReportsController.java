package pt.ipvc.ittalents.Backend.Controllers.Admin;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.*;
import pt.ipvc.ittalents.Backend.Exceptions.OfferException;
import pt.ipvc.ittalents.Models.Offers;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AdminRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class ReportsController {
    public ComboBox<String> category;
    public ComboBox<String> country;
    public ComboBox<String> skill;
    public Label error;
    public Label report;

    public void initialize(){
        try{
            Offers.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        category.getItems().add(AreaType.DEVELOPER.toString());
        category.getItems().add(AreaType.UXSPECIALISTS.toString());
        category.getItems().add(AreaType.PROJECTMANAGER.toString());
        category.getItems().add(AreaType.PRODUCTMANAGER.toString());
        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1)
            country.getItems().add(countrylist);
        for (Skill s : Skills.data)
            skill.getItems().add(s.getName());
    }

    private void validator() throws OfferException {
        if (category.getValue()==null &&
        country.getValue()==null &&
        skill.getValue()==null) {
            throw new OfferException("Please fill in the details.");
        }
    }
    private void reportData() {
        double sum = 0;
        int cont = 0;
        int type = 0;
        /*
        category (selected category, blank country and blank skill) type 1
        country (selected country, blank category, blank skill) type 2
        category with skill without country (category and skill selected country blank) type 3
        category with skill with country (category, skill, country selected) type 4
        */
        for (Offer o : Offers.data.keySet()){
            System.out.println(1);
            if(o.isAcepted()){
                System.out.println(2);
                if(Offers.data.get(o).getiTArea().equals(AreaType.valueOf(category.getValue())) && country.getValue()==null && skill.getValue()==null){
                    sum += o.getPrice();
                    cont++;
                    type = 1;
                }
                if (Objects.equals(Offers.data.get(o).getCountry(), country.getValue()) && category.getValue()==null && skill.getValue()==null){
                    sum += o.getPrice();
                    cont++;
                    type = 2;
                }
                if(Offers.data.get(o).getiTArea() == AreaType.valueOf(category.getValue()) && country.getValue()==null){
                    for(Person p : Persons.data){
                        if(p instanceof Professional){
                            for(int idSkill : ((Professional) p).getSkills().keySet()) {
                                if(Objects.equals(getSkillName(idSkill), skill.getValue())){
                                    sum += o.getPrice();
                                    cont++;
                                    type = 3;
                                }
                            }
                        }
                    }
                }
                if(Offers.data.get(o).getiTArea() == AreaType.valueOf(category.getValue()) && Offers.data.get(o).getCountry().equals(country.getValue())){
                    for(Person p : Persons.data){
                        if(p instanceof Professional){
                            for(int idSkill : ((Professional) p).getSkills().keySet()) {
                                if(Objects.equals(getSkillName(idSkill), skill.getValue())){
                                    sum += o.getPrice();
                                    cont++;
                                    type = 4;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(type == 1) report.setText("The average price for the category " + category.getValue() + " is: " + sum/cont);
        else if(type == 2) report.setText("The average price for the country " + country.getValue() + " is: " + sum/cont);
        else if(type == 3) report.setText("The average price for the category " + category.getValue() + " and skill " + skill.getValue() + " is: " + sum/cont);
        else if(type == 4) report.setText("The average price for the category " + category.getValue() + " and skill " + skill.getValue() + " and country " + country.getValue() + " is: " + sum/cont);
        else report.setText("No reports available for the options presented.");
    }
    private String getSkillName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }
    public void goBack() {
        ViewFactory.closeStage((Stage) report.getScene().getWindow());
        AdminRoutes.showDashboard();
    }
    public void createReport() {
        try {
            validator();
            error.setVisible(true);
            error.setTextFill(Color.color(0, 1, 0));
            error.setText("Search carried out.");
            reportData();
        }catch (OfferException e){
            error.setVisible(true);
            error.setTextFill(Color.color(1, 0, 0));
            error.setText(e.getMessage());
        }
    }

    public void updateSkills() {
        skill.getItems().removeAll(skill.getItems());
        for (Skill s : Skills.data)
            if(s.getAreaType() == AreaType.valueOf(category.getValue()))
                skill.getItems().add(s.getName());
    }

    public void updateCategory() {
        for (Skill s : Skills.data)
            if(s.getName().equals(skill.getValue())){
                category.setValue(s.getAreaType().toString());
                break;
            }
    }
}
