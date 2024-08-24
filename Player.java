import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Player {    
    private static long id;
    private static String username;
    private static long xp;
    private static long coins; 
    private static ArrayList<String> inventory = new ArrayList<>();
    private static ArrayList<Long> inventoryItemsId = new ArrayList<>();
    private static ArrayList<String> purchasedItems = new ArrayList<>();

    static boolean saveData = false; 

    private static BufferedImage PlayerProfileIcon;

    private final static long BASE_XP = 100;

    public static int determineLevel(long xp) {
        return (int) Math.sqrt(xp / BASE_XP);
    }

    public static long nextLevelXP(long xp) {
        int currentLevel = determineLevel(xp);
        int nextLevel = currentLevel + 1;

        // XP needed for next level = Base XP × (Current Level) ^ 2
        return (long) (BASE_XP * Math.pow(nextLevel, 2));
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Player.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Player.username = username;
    }

    public static long getCoins() {
        return coins;
    }

    public static void setCoins(long coins) {
        Player.coins = coins;
    }

    public static long getXp() {
        return xp;
    }

    public static void setXp(long xp) {
        Player.xp = xp;
    }

    public static ArrayList<String> getInventory() {
        return inventory;
    }
    
    public static ArrayList<String> getPurchasedItems() {
        return purchasedItems;
    }

    public static long getBaseXp() {
        return BASE_XP;
    }

    public static BufferedImage getPlayerProfileIcon() {
        return PlayerProfileIcon;
    }

    public static void setPlayerProfileIcon(BufferedImage playerProfileIcon) {
        PlayerProfileIcon = playerProfileIcon;
    }

    public static ArrayList<Long> getInventoryItemsId() {
        return inventoryItemsId;
    }

    public static void setInventoryItemsId(ArrayList<Long> inventoryItemsId) {
        Player.inventoryItemsId = inventoryItemsId;
    }

    public static void setInventory(ArrayList<String> inventory) {
        Player.inventory = inventory;
    }

    public static void setPurchasedItems(ArrayList<String> purchasedItems) {
        Player.purchasedItems = purchasedItems;
    }
}
