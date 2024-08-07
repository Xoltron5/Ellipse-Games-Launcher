public class GamesUtils {
    final static long BASE_XP = 100;

    public static int determineLevel(long xp) {
        return (int) Math.sqrt(xp / BASE_XP);
    }

    public static long nextLevelXP(long xp) {
        int currentLevel = determineLevel(xp);
        int nextLevel = currentLevel + 1;

        // XP needed for next level = Base XP × (Current Level) ^ 2
        return (long) (BASE_XP * Math.pow(nextLevel, 2));
    }
}
