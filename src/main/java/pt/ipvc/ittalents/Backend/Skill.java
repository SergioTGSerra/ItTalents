package pt.ipvc.ittalents.Backend;

import java.util.ArrayList;
import java.util.List;

public class Skill {
    private String name;
    private String description;
    private AreaType category;
    
    private List<Skill> skills = new ArrayList<>();

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
    
    
}
