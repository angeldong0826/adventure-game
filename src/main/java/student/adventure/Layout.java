package student.adventure;

import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Layout class that stores and returns the starting and ending rooms as Strings as well as rooms in the game as a List.
 */
public class Layout {
    private String startingRoom; // name of the starting room
    private String endingRoom; // name of the ending room
    private List<Room> rooms; // list of rooms available in game

    public String getStartingRoom() {
        return startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public List<Room> getRooms() {
        return rooms;
    }

//    public void validate() {
//        if (startingRoom == null || endingRoom == null || rooms == null) {
//            throw new JsonSyntaxException("Json file not valid.");
//        }
//    }
}
