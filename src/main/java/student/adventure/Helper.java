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
}
