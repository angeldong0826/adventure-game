package student.adventure;

import java.util.List;

public class Helper {

    // helper method that updates a new current room.
    public static Room updateCurrentRoom(Layout layout, String direction, Room currentRoom) {
        if (currentRoom == null) {
            throw new NullPointerException();
        }
        Room updatedRoom = null;
        String updatedRoomName = "";
        List<Direction> directionList = currentRoom.getDirections();
        for (int i = 0; i < directionList.size(); i++) {
            Direction directionName = directionList.get(i);
            if (directionName.getDirectionName().equals(direction)) {
                updatedRoomName = directionName.getRoom();
                for (int j = 0; j < layout.getRooms().size(); j++) {
                    Room newRoom = layout.getRooms().get(j);
                    if (newRoom.getName().equals(updatedRoomName)) {
                        updatedRoom = layout.getRooms().get(j);
                        return updatedRoom;
                    }
                }
            }
        }
        return updatedRoom;
    }
}
