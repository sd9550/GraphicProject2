public class PersonalInventory extends Inventory {

    public PersonalInventory() {

    }

    public void addRocksToMainInventory(int n) {
        returnedRocks += n;
    }

    public void addWoodToMainInventory(int n) {
        returnedWood += n;
    }
}
