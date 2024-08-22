public class GameDetails extends ItemsDetails {
    private String releaseDate;
    private String developer;
    private String description;

    public GameDetails(long id, String name, String releaseDate, 
    String developer, String description, String iconPath) {
        super(id, name);
        this.setReleaseDate(releaseDate);
        this.setDeveloper(developer);
        this.setDescription(description);
        this.setIconPath(iconPath);
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
}
