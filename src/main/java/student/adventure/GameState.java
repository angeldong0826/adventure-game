package student.adventure;

import java.util.List;
import java.util.Map;

/**
 * GameState class where current location and current inventory are updated as the game goes on.
 */
public class GameState {
    private Room currentRoom;
    private List<Item> inventory;
    private Room nextRoom;
    private Layout layout;

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
     * Helper method that updates and returns a new current room.
     * @param layout as a Layout
     * @param direction as a String
     * @param currentRoom as a Room
     * @return updated/new current room as a Room
     * @throws NullPointerException if layout, direction, or currentRoom input is null
     */
    public static Room updateCurrentRoom(Layout layout, String direction, Room currentRoom) {
        if (layout == null || direction == null || currentRoom == null) {
            throw new NullPointerException();
        }
        Room updatedRoom = null;
        String updatedRoomName;
        List<Direction> directionList = GameEngine.gameState.getCurrentLocation().getDirections();
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

//    /**
//     * Helper method that moves player around.
//     * @param command as a String
//     */
//    public void go(String command) {
//        // String[] splitCommand = command.split("\\s+"); // to hold input split by whitespace in an arraylist.
//        nextRoom = updateCurrentRoom(layout, command, getCurrentLocation());
//        boolean valid = false;
//        while(!valid) {
//            if (nextRoom == null) {
//                System.out.println("You can't go " + command + "!");
//                continue;
//            }
//            setCurrentLocation(nextRoom);
//            System.out.println(getCurrentLocation().getDescription());
//            if (!(getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom()))) {
//                getCurrentLocation().printAvailableDirections();
//                getCurrentLocation().printAvailableItems(getCurrentLocation());
//                valid = true;
//            }
//        }
//    }

    /**
     * Helper method that takes items from rooms and updates the inventory and item lists.
     * @param itemName as a String
     * @throws NullPointerException if room, itemName, or inventories input is null
     */
    public void take(String itemName) {
        Map<String, Item> itemDictionary = Room.createItemDictionary(currentRoom);
        if (itemName == null) {
            throw new NullPointerException();
        }
        boolean itemInRoom = false;
        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (currentRoom.getItems().get(i).getItemName().equalsIgnoreCase(itemName)) {
                inventory.add(itemDictionary.get(itemName));
                System.out.println("Item " + itemName + " taken.");
                currentRoom.removeItem(itemDictionary.get(itemName));
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
