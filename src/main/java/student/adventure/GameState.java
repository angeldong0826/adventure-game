package student.adventure;

import java.util.List;
import java.util.Map;

public class GameState {
    private Room currentRoom;
    private List<Item> inventory;

    public GameState(Room setCurrentLocation, List<Item> setInventory) {
        currentRoom = setCurrentLocation;
        inventory = setInventory;
    }

    public Room getCurrentLocation() {
        return currentRoom;
    }

    public void setCurrentLocation(Room currentLocation) {
        this.currentRoom = currentLocation;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Helper method that takes items from rooms and updates the inventory list.
     * @param itemName as a String
     * @throws NullPointerException if room, itemName, or inventories input is null
     */
    public void take(String itemName) {
        Map<String, Item> itemDictionary = Room.createItemDictionary(currentRoom);
        if (itemName == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if ((i == currentRoom.getItems().size() - 1) && !(currentRoom.getItems().get(i).getItemName().equalsIgnoreCase(itemName))) {
                System.out.println("There is no " + itemName + " in the room.");
            }
            if (currentRoom.getItems().get(i).getItemName().equalsIgnoreCase(itemName)) {
                addItem(itemDictionary.get(itemName));
                System.out.println("Item " + itemName + " taken.");
                currentRoom.removeItem(itemDictionary.get(itemName));
                break;
            }
        }
    }

    /**
     * Helper method that drops items from rooms and updates the inventory list.
     * @param itemName as a String
     * @throws NullPointerException if room, itemName, or inventories input is null
     */
    public void drop(String itemName) {
        if (itemName == null) {
            throw new NullPointerException();
        }
        boolean itemInInventory = false;
        for (int i = 0; i < GameEngine.gameState.getInventory().size(); i++) {
            if (inventory.get(i).getItemName().equalsIgnoreCase(itemName)) {
                Item item = inventory.get(i);
                inventory.remove(item);
                System.out.println("Item " + itemName + " dropped.");
                currentRoom.addItem(item);
                itemInInventory = true;
                break;
            }
        }
        if (!itemInInventory) {
            System.out.println("You don't have " + itemName + "!");
        }
    }
}
