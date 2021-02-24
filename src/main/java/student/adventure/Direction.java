package student.adventure;

import com.google.gson.JsonSyntaxException;

/**
 * Direction class that initializes direction names and rooms that directions lead to as Strings.
 */
public class Direction {
    private String directionName; // specific direction name
    private String room; // room that the specific direction leads to

    public String getDirectionName() {
        return directionName;
    }

    public String getRoom() {
        return room;
    }

    /**
     * Method to test for valid direction.
     *
     * @throws NullPointerException if direction or room is not valid
     */
    public void isValidDirection() {
        if (directionName == null || room == null) {
            throw new NullPointerException();
        }
    }
}
