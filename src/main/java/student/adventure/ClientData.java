package student.adventure;

import java.util.List;

/**
 * Client data class with data publicly returned to player.
 */
public class ClientData {

    private final String description; // room description
    private final List<String> itemsAvailable; // items available in room
    private final List<String> inventory; // inventory player holds
    private final List<String> directions; // directions available from room
    private final boolean finished; // to determine the end/win of game

    public ClientData(String description, List<String> itemsAvailable,
        List<String> inventory, List<String> directions, boolean finished) {
        this.description = description;
        this.itemsAvailable = itemsAvailable;
        this.inventory = inventory;
        this.directions = directions;
        this.finished = finished;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItemsAvailable() {
        return itemsAvailable;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public List<String> getDirections() {
        return directions;
    }

    public boolean isFinished() {
        return finished;
    }
}
