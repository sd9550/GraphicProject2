public class TerrainData {

    public static final short[] WORLD_DATA = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static final int ROCKS = 0;
    public static final int ENTRANCE = 1;
    public static final int CRAFTING_AREA = 2;
    public static final int GOODS_AREA = 3;
    public static final int TREES = 4;
    public static final int BUILDING = 5;

    public static final int GOODS_X = 550, GOODS_Y = 360;
    public static final int[] ROCK_X = {200, 250}, ROCK_Y = {180, 180};
    public static final int TREE_X = 760, TREE_Y = 300;
    public static final int ENTRANCE_X = 450, ENTRANCE_Y = 400;
    public static final int CRAFT_X = 100, CRAFT_Y = 560;
    public static final int ALTAR_X = 400, ALTAR_Y = 125;
    public static int buildingX, buildingY;
    private static int rockIndex = -1;

    public static int getRockIndex() {
        rockIndex += 1;
        return rockIndex;
    }

    public static void setBuildingX(int x) {
        if (x % 5 != 0) {
            int remainder = x % 5;
            x -= remainder;
            buildingX = x;
        } else
            buildingX = x;
    }

    public static void setBuildingY(int y) {
        if (y % 5 != 0) {
            int remainder = y % 5;
            y -= remainder;
            buildingY = y;
        } else
            buildingY = y;
    }
}
