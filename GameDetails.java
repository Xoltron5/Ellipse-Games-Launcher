public class GameDetails {
    private long id;
    private String name;
    private String releaseDate;
    private String developer;
    private String description;
    private String iconPath; 

    public GameDetails(long id, String name, String releaseDate, 
    String developer, String description, String iconPath) {
        this.setId(id);
        this.setName(name);
        this.setReleaseDate(releaseDate);
        this.setDeveloper(developer);
        this.setDescription(description);
        this.setIconPath(iconPath);
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
