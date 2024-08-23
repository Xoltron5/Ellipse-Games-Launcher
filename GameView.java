import javafx.scene.input.MouseEvent;

public class GameView extends EntityView {
    public GameView(long id, String filePath,
    EntityDetails entityDetails) {
        super(id, filePath, entityDetails);
    }

    @Override
    public void handleClick(MouseEvent event) {
        GameDetails gameDetails = (GameDetails)getEntityDetails();

        System.out.println(gameDetails.getName());
    }
}
