public class GameDetails extends EntityDetails {
    private String releaseDate; // Release date of the game
    private String developer; // Developer of the game

    public GameDetails(long id, String name, String releaseDate, 
    String developer, String description, String iconPath) {
        super(id, name, iconPath , description);
        this.setReleaseDate(releaseDate);
        this.setDeveloper(developer);
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
}
