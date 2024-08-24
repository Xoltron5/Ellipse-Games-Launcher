import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class ItemView extends EntityView {

    public ItemView(long id, String filePath,
    EntityDetails entityDetails, MainPage currentPage) {
        super(id, filePath, entityDetails, currentPage);
    }    

    @Override
    public void handleClick(MouseEvent event) {
        ItemDetails itemDetails = (ItemDetails) getEntityDetails();

        if (getCurrentPage() instanceof StorePage) {
            StorePage storePage = (StorePage)getCurrentPage();
            storePage.getErrorlabel().setText("");
            storePage.getItemView().setImage(new Image(itemDetails.getIconPath()));
            storePage.getItemNameLabel().setText(itemDetails.getName());
            storePage.getLevelReqLabel().setText("Level: " + itemDetails.getLevelReq());
            storePage.getCostLabel().setText("Coins: " + itemDetails.getCost());
            storePage.getActionButton().setVisible(true);
            storePage.getItemDescLabel().setText(itemDetails.getDescription());
            storePage.getCurrentLevelLabel().setText("Current Level: " + Player.determineLevel(Player.getXp()));
            storePage.getCurrentCoinsLabel().setText("Current Coins: " + Player.getCoins());

            for (String item : Player.getInventory()) {
                if (item.equals(itemDetails.getName())) {
                    storePage.getActionButton().setText("Owned");
                    storePage.getActionButton().setStyle("-fx-background-color: #D45D2A");
                    return;
                }
            }
            for (String item : Player.getPurchasedItems()) {
                if (item.equals(itemDetails.getName())) {
                    storePage.getActionButton().setText("Owned");
                    storePage.getActionButton().setStyle("-fx-background-color: #D45D2A");
                    return;
                }
            }
            storePage.getActionButton().setText("Purchase");
            storePage.getActionButton().setStyle("-fx-background-color: #D42A2A");
            // Link the action button to the handlePurchase method
            storePage.getActionButton().setOnMouseClicked(e -> handlePurchase(itemDetails));
        }
        if (getCurrentPage() instanceof InventoryPage) {
            InventoryPage inventoryPage = (InventoryPage)getCurrentPage();
            inventoryPage.getErrorlabel().setText("");
            inventoryPage.getItemView().setImage(new Image(itemDetails.getIconPath()));
            inventoryPage.getItemNameLabel().setText(itemDetails.getName());
            inventoryPage.getLevelReqLabel().setText("Level: " + itemDetails.getLevelReq());
            inventoryPage.getCostLabel().setText("Coins: " + itemDetails.getCost());
            inventoryPage.getItemDescLabel().setText(itemDetails.getDescription());
        }
    }

    private void handlePurchase(ItemDetails itemDetails) {
        StorePage storePage = (StorePage)getCurrentPage();

        if (storePage.getActionButton().getText().equals("Owned")) return;

        if ((Player.determineLevel(Player.getXp()) < itemDetails.getLevelReq())) {
            if (getCurrentPage() instanceof StorePage) {
                storePage.getErrorlabel().setText("Not high enough level");
            }
            return;
        }

        if ((Player.getCoins() < itemDetails.getCost())) {
            if (getCurrentPage() instanceof StorePage) {
                storePage.getErrorlabel().setText("Not enough coins");
            }
            return;
        }

        System.out.println("Purchasing item: " + itemDetails.getName());
        Player.setCoins(Player.getCoins() - itemDetails.getCost());
        
        storePage.getCurrentLevelLabel().setText("Current Level: " + Player.determineLevel(Player.getXp()));
        storePage.getCurrentCoinsLabel().setText("Current Coins: " + Player.getCoins());

        
        Player.getPurchasedItems().add(itemDetails.getName());
        Player.getInventory().add(itemDetails.getName());
        Player.getInventoryItemsId().add(itemDetails.getId());
        storePage.getActionButton().setText("Owned");
        storePage.getActionButton().setStyle("-fx-background-color: #D45D2A");
    }
}
