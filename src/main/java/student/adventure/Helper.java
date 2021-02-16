package student.adventure;

import java.util.List;

public class Helper {

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
        List<Direction> directionList = currentRoom.getDirections();
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
     * Helper method that takes items from rooms and updates the inventory list.
     * @param room as a Room
     * @param item as a String
     * @param inventories as a list of Strings
     * @throws NullPointerException if room, item, or inventories input is null
     */
    public static void take(Room room, String item, List<String> inventories) {
        if (room == null || item == null || inventories == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < room.getItems().size(); i++) {
            if ((i == room.getItems().size() - 1) && !(room.getItems().get(i).getItemName().equalsIgnoreCase(item))) {
                System.out.println("There is no item " + item + " in the room.");
            }
            if (room.getItems().get(i).getItemName().equalsIgnoreCase(item)) {
                if (!(inventories.contains(item))) {
                    inventories.add(room.getItems().get(i).getItemName());
                    System.out.println("Item " + item + " taken.");
                } else {
                    System.out.println("You have already acquired " + item + ".");
                }
            }
        }
    }

    /**
     * Helper method that drops items from rooms and updates the inventory list.
     * @param room as a Room
     * @param item as a String
     * @param inventories as a list of Strings
     * @throws NullPointerException if room, item, or inventories input is null
     */
    public static void drop(Room room, String item, List<String> inventories) {
        if (room == null || item == null || inventories == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < inventories.size(); i++) {
            if ((i == inventories.size() - 1) && !(inventories.get(i).equalsIgnoreCase(item))) {
                System.out.println("You don't have " + item + "!");
            }
            if (inventories.get(i).equalsIgnoreCase(item)) {
                inventories.remove(inventories.get(i));
                System.out.println("Item " + item + " dropped.");
            }
        }
    }
}
