package student.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GameState class where current location and current inventory are updated as the game goes on.
 */
public class GameState {

    private Room currentRoom; // current location
    private List<Item> inventory; // inventory list of things taken
    private Room nextRoom; // next location
    private Layout layout; // room layout

    /**
     * Constructor that initializes current location and inventory.
     *
     * @param setCurrentLocation as a Room
     * @param setInventory as a list of Items
     */
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

    /**
     * Method that converts my arraylist of inventory to String.
     *
     * @return inventory as a String
     */
    public String inventoryToString() {
        String[] inventoryToReturn = new String[inventory.size()];
        for (int i = 0; i < inventoryToReturn.length; i++) {
            inventoryToReturn[i] = inventory.get(i).getItemName();
        }
        return String.join(", ", inventoryToReturn);
    }

    /**
     * Helper method that updates and returns a new current room.
     *
     * @param gameState as a GameState
     * @param layout of room layout
     * @param direction that player is going
     * @param currentRoom current room that player is in
     * @return updated room as a Room
     * @throws NullPointerException if any of the update current room parameters is null
     */
    public Room updateCurrentRoom(GameState gameState, Layout layout, String direction, Room currentRoom) {
        if (layout == null || direction == null || currentRoom == null) {
            throw new NullPointerException();
        }
        Room updatedRoom = null;
        String updatedRoomName;
        List<Direction> directionList = gameState.getCurrentLocation().getDirections();
        for (int i = 0; i < directionList.size(); i++) {
            Direction directionName = directionList.get(i);
            if (directionName.getDirectionName().equalsIgnoreCase(direction)) {
                updatedRoomName = directionName.getRoom();
                for (int j = 0; j < layout.getRooms().size(); j++) {
                    Room newRoom = layout.getRooms().get(j);
                    if (newRoom.getName().equalsIgnoreCase(updatedRoomName)) {
                        updatedRoom = layout.getRooms().get(j);
                        return updatedRoom;
                    }
                }
            }
        }
        return updatedRoom;
    }

    /**
     * Helper method that takes items from rooms and updates the inventory and item lists.
     *
     * @param itemName item taking
     * @throws NullPointerException if item taken is null
     */
    public void take(String itemName) {
        Map<String, Item> itemDictionary = Room.createItemDictionary(currentRoom);
        if (itemName == null) {
            throw new NullPointerException();
        }
        String itemNameLowercase = itemName.toLowerCase();
        boolean itemInRoom = false;
        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (currentRoom.getItems().get(i).getItemName().equalsIgnoreCase(itemNameLowercase)) {
                inventory.add(itemDictionary.get(itemNameLowercase));
                System.out.println("Item " + itemName + " taken.");
                currentRoom.removeItem(itemDictionary.get(itemNameLowercase));
                itemInRoom = true;
                break;
            }
        }
        if (!itemInRoom) {
            System.out.println("There is no " + itemName + " in the room.");
        }
    }

    /**
     * Helper method that drops items from rooms and updates the inventory and item lists.
     *
     * @param itemName item dropping
     * @throws NullPointerException when item dropped is null
     */
    public void drop(String itemName) {
        if (itemName == null) {
            throw new NullPointerException();
        }
        boolean itemInInventory = false;
        for (int i = 0; i < getInventory().size(); i++) {
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
