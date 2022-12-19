package pt.ipvc.ittalents.Backend;

import java.io.Serializable;

public class Skill implements Serializable {
    private int id;
    private static int numberSkills = 0;
    private String name;
    private String description;
    private AreaType areaType;
    private boolean published;

    public Skill(){}
    
    /**
     * @param name Skill name
     * @param description Description of skill
     * @param areaType Skill category in ITArea
     */
    public Skill(String name, String description, AreaType areaType) {
        this.id = ++numberSkills;
        this.name = name;
        this.description = description;
        this.areaType = areaType;
        this.published = false;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
