import javafx.scene.input.MouseEvent;

public class ItemView extends EntityView {
    public ItemView(long id, String filePath) {
        super(id, filePath);
        
    }    

    @Override
    public void handleClick(MouseEvent event) {
        System.out.println("Hello5");
    }
}
