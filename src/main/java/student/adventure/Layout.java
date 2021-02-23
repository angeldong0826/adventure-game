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

    /**
     * Method to test for valid layout.
     */
    public void isValidLayout() {
        if (startingRoom == null || endingRoom == null || rooms == null) {
            throw new NullPointerException();
        }
    }
}
