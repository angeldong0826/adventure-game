package student.adventure;

import java.util.List;

/**
 * IMPLEMENT
 */
public class Room {
    private String name; // name of room as a String
    private String description; // description of room as a String
    private List<Item> items; // list of items in room
    private List<Direction> directions; // list of directions and rooms they lead to from a room

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    /**
     * Helper method that prints all available directions from a room.
     */
    public void returnAvailableDirections() {
        String availableDirections = "From here, you can go: ";
        if (directions == null) {
            throw new NullPointerException();
        }
        if (directions.size() == 0) {
            availableDirections += "";
        }
        for (int i = 0; i < directions.size(); i++) {
            availableDirections += directions.get(i).getDirectionName() + " ";
        }
        System.out.println(availableDirections);
    }

    /**
     * Helper method that returns all available items in a row.
     * @throws NullPointerException when items is null
     * @param inventories as a list of Strings
     */
    // helper method that returns all available items in a room.
    public void returnAvailableItems(List<String> inventories) {
        String availableItems = "Items visible: ";
        if (items == null) {
            throw new NullPointerException();
        }
        if (items.size() == 0) {
            availableItems += "";
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && !(inventories.contains(items.get(i).getItemName()))) {
                availableItems += items.get(i).getItemName()  + " ";
            }
        }
        System.out.println(availableItems);
    }
}
