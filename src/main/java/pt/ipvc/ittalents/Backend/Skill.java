package pt.ipvc.ittalents.Backend;

import java.io.Serializable;

public class Skill implements Serializable {
    private String name;
    private String description;
    private AreaType category;

    public Skill(){}
    
    /**
     * @param name Skill name
     * @param description Description of skill
     * @param category Skill category in ITArea
     */
    public Skill(String name, String description, AreaType category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AreaType getCategory() {
        return category;
    }
}
