package pt.ipvc.ittalents.Backend.Controllers.Generic;

import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.*;
import pt.ipvc.ittalents.Backend.Controllers.Generic.Components.ProfessionalItemController;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.AdminRoutes;
import pt.ipvc.ittalents.Routes.ClientRoutes;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;
import pt.ipvc.ittalents.Routes.ViewFactory;

import java.io.IOException;

public class SearchTalents {
    public ListView<String> skills;
    public ComboBox<AreaType> itArea;
    public VBox listPersons;

    public void initialize(){
        itArea.getItems().add(AreaType.DEVELOPER);
        itArea.getItems().add(AreaType.UXSPECIALISTS);
        itArea.getItems().add(AreaType.PRODUCTMANAGER);
        itArea.getItems().add(AreaType.PROJECTMANAGER);
        skills.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setListPersons();
    }
    public void goBack() {
        if(Persons.loged.getPersonType().equals(PersonType.ADMIN))
            AdminRoutes.showDashboard();
        else if(Persons.loged.getPersonType().equals(PersonType.CLIENT))
            ClientRoutes.showDashboard();
        else if(Persons.loged.getPersonType().equals(PersonType.PROFESSIONAL))
            ProfessionalRoutes.showDashboard();
        ViewFactory.closeStage((Stage)skills.getScene().getWindow());
    }
    public void findTalents() {
        if(itArea.getValue()!=null && !skills.getSelectionModel().getSelectedItems().isEmpty()) {
            listPersons.getChildren().removeAll(listPersons.getChildren());
            for(Person p : Persons.data){
                boolean exit = false;
                if(p instanceof Professional && ((Professional) p).getiTArea().equals(AreaType.valueOf(itArea.getValue().toString())) && ((Professional) p).isPublished()){
                    for (String s : skills.getSelectionModel().getSelectedItems()){
                        for(Integer i : ((Professional) p).getSkills().keySet()){
                            if(s.equals(getSkillName(i))){
                                exit = true;
                                setListPersons((Professional)p);
                            }
                        }
                        if(exit)
                            break;
                    }
                }
            }
        }
    }
    private String getSkillName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }
    public void updateSkills() {
        skills.getItems().removeAll(skills.getItems());
        for(Skill s : Skills.data)
            if(s.getAreaType().equals(itArea.getValue()) && s.isPublished())
                skills.getItems().add(s.getName());
    }
    private void setListPersons(Professional p){
        fxml(p);
    }
    private void setListPersons(){
        for (Person p : Persons.data)
            if(p instanceof Professional && ((Professional)p).isPublished())
                fxml((Professional) p);
    }
    private void fxml(Professional p) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Generic/Components/ProfessionalItem.fxml"));
        try {
            AnchorPane pane = fxmlLoader.load();
            ProfessionalItemController pic = fxmlLoader.getController();
            pic.setData(p);
            listPersons.getChildren().add(pane);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
