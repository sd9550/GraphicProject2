public class Inventory {

    private int totalRocks, totalWood;
    public static int returnedRocks;
    public static int returnedWood;

    public Inventory() {
        this.totalRocks = 0;
        this.totalWood = 0;
        returnedRocks = 0;
        returnedWood = 0;
    }

    public int getTotalRocks() {
        this.totalRocks += returnedRocks;
        returnedRocks -= returnedRocks;
        return totalRocks;
    }

    public void setTotalRocks(int totalRocks) {
        this.totalRocks += totalRocks;
    }

    public int getTotalWood() {
        this.totalWood += returnedWood;
        returnedWood -= returnedWood;
        return totalWood;
    }

    public void setTotalWood(int totalWood) {
        this.totalWood += totalWood;
    }
}
