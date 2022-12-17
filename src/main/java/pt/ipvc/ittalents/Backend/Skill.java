package pt.ipvc.ittalents.Backend;

import pt.ipvc.ittalents.Models.Persons;
import pt.ipvc.ittalents.Models.Skills;

import java.io.Serializable;

public class Skill implements Serializable {
    private int id = 1;
    private String name;
    private String description;
    private AreaType areaType;

    public Skill(){}
    
    /**
     * @param name Skill name
     * @param description Description of skill
     * @param areaType Skill category in ITArea
     */
    public Skill(String name, String description, AreaType areaType) {
        if(!Skills.data.isEmpty())
            this.id += Skills.data.get(Skills.data.size() - 1).getId();
        this.name = name;
        this.description = description;
        this.areaType = areaType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AreaType getAreaType() {
        return areaType;
    }
}
