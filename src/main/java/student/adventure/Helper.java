package student.adventure;

import java.util.List;

public class Helper {

    // helper method that updates a new current room.
    public static Room updateCurrentRoom(Layout layout, String direction, Room currentRoom) {
        if (currentRoom == null) {
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

    public static void take(Room room, String item, List<String> inventories) {
        if (room == null) {
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
                    System.out.println("You have already acquired this item.");
                }
            }
        }
    }
}
