public class Player {    
    private static String username;
    private static long xp;
    private static long coins; 

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
}
