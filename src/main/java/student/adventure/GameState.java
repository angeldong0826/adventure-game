package student.adventure;

public class GameState {
    private Room currentRoom;
    private Item inventory;

    public GameState(Room setCurrentLocation, Item setInventory) {
        currentRoom = setCurrentLocation;
        inventory = setInventory;
    }

    public Room getCurrentLocation() {
        return currentRoom;
    }

    public void setCurrentLocation(Room currentLocation) {
        this.currentRoom = currentLocation;
    }

    public Item getInventory() {
        return inventory;
    }

    public void setInventory(Item inventory) {
        this.inventory = inventory;
    }
}
