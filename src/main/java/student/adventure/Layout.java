package student.adventure;

import java.util.List;

/**
 * IMPLEMENT
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
}
