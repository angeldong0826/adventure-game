package student.adventure;

import com.google.gson.JsonSyntaxException;

/**
 * Direction class that initializes direction names and rooms that directions lead to as Strings.
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

    /**
     * Method to test for valid direction.
     */
    public void isValidDirection() {
        if (directionName == null || room == null) {
            throw new NullPointerException();
        }
    }
}
