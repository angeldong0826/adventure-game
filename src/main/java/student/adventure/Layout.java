package student.adventure;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Layout class that stores and returns the starting and ending rooms as Strings as well as rooms in
 * the game as a List.
 */
public class Layout {

    private final String startingRoom; // name of the starting room
    private final String endingRoom; // name of the ending room
    private final List<Room> rooms; // list of rooms available in game
    private Map<String, Room> map; // map set up from the rooms

    public Layout(String setStartingRoom, String setEndingRoom, List<Room> setRooms,
        Map<String, Room> setMap) {
        startingRoom = setStartingRoom;
        endingRoom = setEndingRoom;
        rooms = setRooms;
        map = setMap;
    }

    public String getStartingRoom() {
        return startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public List<Room> getRooms() {
        return rooms;
    }

//    public Room getRoom(String name) {
//        Room room = map.get(name);
//        return new Room(room.getName(), room.getDescription(), room.getItems(), room.getDirections());
//    }

//    /**
//     * Method to add items to rooms.
//     * @param room to add in as a String
//     * @param item to add in as a String
//     */
//    public void addItem(String room, String item) {
//        Room newRoom = map.get(room);
//        newRoom.addItem(item);
//    }
//
//    /**
//     * Method to remove items from rooms.
//     * @param room to remove from as a String
//     * @param item to remove as a String
//     */
//    public void removeItem(String room, String item) {
//        Room newRoom = map.get(room);
//        newRoom.removeItem(item);
//    }

    /**
     * Creates map from the rooms.
     */
    public void createMap() {
        if (startingRoom == null || endingRoom == null || startingRoom.equalsIgnoreCase(endingRoom)
            || startingRoom.length() < 1 || endingRoom.length() < 1) {
            throw new JsonParseException("invalid map. check again.");
        }

        map = new HashMap<>();
        for (Room room : rooms) {
            map.put(room.getName(), room);
        }

        try {
            map = new HashMap<>();
            for (Room room : rooms) {
                map.put(room.getName(), room);
            }
        } catch (Exception e) {
            throw new JsonParseException("invalid map. check again.");
        }
    }
}
