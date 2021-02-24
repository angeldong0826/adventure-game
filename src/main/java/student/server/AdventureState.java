package student.server;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import student.adventure.Item;

/**
 * A class to represent values in a game state.
 *
 * Note: these fields should be JSON-serializable values, like Strings, ints, floats, doubles, etc.
 * Please don't nest objects, as the frontend won't know how to display them.
 *
 * Good example:
 * private String shoppingList;
 *
 * Bad example:
 * private ShoppingList shoppingList;
 */
@JsonSerialize
public class AdventureState {
    // TODO: Add any additional state your game needs to this object.

    private String locationHistory; // string of location that player has been
    private String inventoryDisplay; // string of user's updating inventory

    /**
     * Constructor that initializes location history and inventory display/
     *
     * @param locationHistory string of location history
     * @param inventoryDisplay string of inventory to display
     */
    public AdventureState(String locationHistory, String inventoryDisplay) {
        this.locationHistory = locationHistory;
        this.inventoryDisplay = inventoryDisplay;
    }

    public String getLocationHistory() {
        return locationHistory;
    }

    public String getInventoryDisplay() {
        return inventoryDisplay;
    }
}
