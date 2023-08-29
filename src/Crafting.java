public class Crafting {
    public static boolean altarWasCrafted, spawneryWasCrafted, wallWasCrafted;
    public static char WALL = 'w';
    public static char SPAWNERY = 's';
    public static int spawneryX, spawneryY;
    public static int WALL_CRAFT_TIMER = 100;
    public static int SPAWNERY_CRAFT_TIMER = 200;
    private static char currentBuilding;

    public Crafting() {
        altarWasCrafted = false;
        spawneryWasCrafted = false;
        wallWasCrafted = false;
    }

    public static void setBuilding(char b) {
        currentBuilding = b;
    }

    public static char getBuilding() {
        return currentBuilding;
    }

}
