package pt.ipvc.ittalents.Backend.Controllers.Client.Components;

import javafx.scene.control.Label;
import pt.ipvc.ittalents.Backend.Controllers.Client.MakeOfferController;
import pt.ipvc.ittalents.Backend.Professional;
import pt.ipvc.ittalents.Backend.Skill;
import pt.ipvc.ittalents.Models.Skills;
import pt.ipvc.ittalents.Routes.ClientRoutes;

public class ProfessionalItemController {
    public Label name;
    public Label skills;
    public Label itArea;
    public Label country;
    public Label price;
    Professional p;

    public void setData(Professional p){
        this.p = p;
        name.setText(p.getName());
        itArea.setText("IT Area: " + p.getiTArea().toString());
        country.setText("Country: " + p.getCountry());
        price.setText("Price: " + p.getPriceHour() + "€");
        StringBuilder sb = new StringBuilder();
        for(Integer i : p.getSkills().keySet())
            sb.append(getSkillName(i)).append(", ");
        skills.setText("Skills: " + sb);
    }
    private String getSkillName(int skillId){
        for(Skill s : Skills.data)
            if(s.getId() == skillId)
                return s.getName();
        return null;
    }
    public void makeOffer() {
        MakeOfferController.professional = p;
        ClientRoutes.ShowMakeOffer();
    }
}
