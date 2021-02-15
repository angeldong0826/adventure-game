package student.adventure;

public class GameState {
    private Room currentLocation;
    private Item inventory;

    public GameState(Room setCurrentLocation, Item setInventory) {
        currentLocation = setCurrentLocation;
        inventory = setInventory;
    }

    public Room getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Room currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Item getInventory() {
        return inventory;
    }

    public void setInventory(Item inventory) {
        this.inventory = inventory;
    }
}
