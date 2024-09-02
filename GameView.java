/*
    Author: Denis Bajgora
    Date: 1/9/2024
    
    Game View is the image portion of the Game object that is displayed on a page. 
*/


import javafx.scene.input.MouseEvent;

public class GameView extends EntityView {
    public GameView(long id, String filePath,
    EntityDetails entityDetails, MainPage currentPage) {
        super(id, filePath, entityDetails, currentPage);
    }

    @Override
    public void handleClick(MouseEvent event) {
        GameDetails gameDetails = (GameDetails)getEntityDetails();

        System.out.println(gameDetails.getName());
    }
}
