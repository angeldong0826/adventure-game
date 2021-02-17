package student.adventure;

/**
 * Direction class that stores and returns direction names and rooms directions lead to as Strings.
 */
public class Direction {
    private String directionName; // specific direction
    private String room; // the room that the specific direction leads to

    public String getDirectionName() {
        return directionName;
    }

    public String getRoom() {
        return room;
    }
}
