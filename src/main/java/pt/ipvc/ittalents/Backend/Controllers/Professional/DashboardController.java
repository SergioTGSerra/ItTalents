package pt.ipvc.ittalents.Backend.Controllers.Professional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.ProfessionalRoutes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    public Label usernameLabel;
    @FXML
    public Label iTAreaLabel;
    @FXML
    public ListView<String> mySkillsList;
    @FXML
    public VBox skillsLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Skills.loadData();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (int idskill : ((Professional)Persons.loged).getSkills().keySet()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/pt/ipvc/ittalents/Fxml/Professional/Components/SkillItem.fxml"));
            try {
                AnchorPane pane = fxmlLoader.load();
                SkillItemController sic = fxmlLoader.getController();
                sic.setData("Teste");
                skillsLayout.getChildren().add(pane);
                System.out.println("entrou");
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //this.setSkillsList();
        usernameLabel.setText(Persons.loged.getUsername());
        iTAreaLabel.setText(((Professional)Persons.loged).getiTArea().toString());
    }
    private void setSkillsList(){
        for (int idskill : ((Professional)Persons.loged).getSkills().keySet())
            mySkillsList.getItems().add(getSkillName(idskill));
    }
    private String getSkillName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }
    public void removeSkill() {
        if(mySkillsList.getSelectionModel().getSelectedItem() == null)
            return;
        for(int skillId : ((Professional)Persons.loged).getSkills().keySet())
            if(mySkillsList.getSelectionModel().getSelectedItem().equals(getSkillName(skillId))){
                ((Professional)Persons.loged).getSkills().remove(skillId);
                break;
            }
        mySkillsList.getItems().remove(mySkillsList.getSelectionModel().getSelectedItem());
        Persons.updatePersons();
    }
    public void goToAddSkill() {
        ProfessionalRoutes.showAddSkill();
    }
    public void goToMySettings() {
        ProfessionalRoutes.showMySettings();
    }
    public void goToAddExprience() {
        ProfessionalRoutes.showAddExprience();
    }
}