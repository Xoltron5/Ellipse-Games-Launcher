import javafx.scene.input.MouseEvent;

public class ItemView extends EntityView {
    public ItemView(long id, String filePath,
    EntityDetails entityDetails) {
        super(id, filePath, entityDetails);
    }    

    @Override
    public void handleClick(MouseEvent event) {

        ItemDetails itemDetails = (ItemDetails)getEntityDetails();

        System.out.println(itemDetails.getName());
    }
}
