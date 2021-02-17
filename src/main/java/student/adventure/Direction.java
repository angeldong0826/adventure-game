package student.adventure;

import com.google.gson.JsonSyntaxException;

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

//    public void validate() {
//        if (directionName == null || room == null) {
//            throw new JsonSyntaxException("Json file not valid.");
//        }
//    }
}
