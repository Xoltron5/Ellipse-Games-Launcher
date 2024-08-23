import javafx.scene.input.MouseEvent;

public class GameView extends EntityView {
    public GameView(long id, String filePath) {
        super(id, filePath);
    }

    @Override
    public void handleClick(MouseEvent event) {
        System.out.println("Hello10");
    }
}
