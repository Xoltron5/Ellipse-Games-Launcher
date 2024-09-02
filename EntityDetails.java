/*
    Author: Denis Bajgora
    Date: 1/9/2024
    
    Entity Details class is used to keep track of all the common things that entitys like games,
    items, etc would have like a name, a icon path and description. 
*/

import java.util.ArrayList;

public abstract class EntityDetails {
    private long id; // Entity's unqiue id 
    private String name; // Entity's name
    private String iconPath; // Entity's icon file path
    private String description; // Entity's description

    private ArrayList<String> filterContentContainer = new ArrayList<>();

    private EntityView entityView;

    public EntityDetails(long id, String name, String iconPath,
    String description) {
        setId(id);
        setName(name);
        setIconPath(iconPath);
        setDescription(description);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getFilterContentContainer() {
        return filterContentContainer;
    }

    public EntityView getEntityView() {
        return entityView;
    }

    public void setEntityView(EntityView entityView) {
        this.entityView = entityView;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
