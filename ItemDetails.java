public class ItemDetails extends EntityDetails {
    private long gameId; // The game this item is part of.
    private int levelReq; // Level Requirment 
    private int cost; // Cost of the item

    public ItemDetails(long id, long gameId, String name,
    int levelReq, String description, String iconPath, int cost) {
        super(id, name, iconPath, description);
        setGameId(gameId);
        setLevelReq(levelReq);
        setCost(cost);
    }
    
    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

    public int getCost() {
        return cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }
}
