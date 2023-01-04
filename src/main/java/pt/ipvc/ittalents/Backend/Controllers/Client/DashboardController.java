package pt.ipvc.ittalents.Backend.Controllers.Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipvc.ittalents.Backend.*;
import pt.ipvc.ittalents.Backend.Controllers.Client.Components.ProfessionalItemController;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.*;

import java.io.IOException;

public class DashboardController {
    public ListView<String> skills;
    public ComboBox<AreaType> itArea;
    public VBox listPersons;

    public void initialize(){
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        itArea.getItems().add(AreaType.DEVELOPER);
        itArea.getItems().add(AreaType.UXSPECIALISTS);
        itArea.getItems().add(AreaType.PRODUCTMANAGER);
        itArea.getItems().add(AreaType.PROJECTMANAGER);
        skills.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setListPersons();
    }
    public void logout() {
        Persons.loged = null;
        ViewFactory.closeStage((Stage)skills.getScene().getWindow());
        AuthRoutes.showLogin();
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
        fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Client/Components/ProfessionalItem.fxml"));
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
