package student.adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Room class that stores and returns the names and descriptions of rooms as Strings, items in rooms and directions rooms lead to as a List.
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

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Helper method that prints out all available directions from a room.
     * @throws NullPointerException when directions is null
     */
    public void printAvailableDirections() {
        String availableDirections = "From here, you can go: ";
        if (directions == null) {
            throw new NullPointerException();
        }
        if (directions.size() == 0) {
            availableDirections += "";
        }
        for (int i = 0; i < directions.size(); i++) {
            if (i <= directions.size() - 2) {
                availableDirections += directions.get(i).getDirectionName() + ", ";
            } else if (i == directions.size() - 1) {
                availableDirections += directions.get(i).getDirectionName() + ".";
            }
        }
        System.out.println(availableDirections);
    }

    /**
     * Helper method that prints out all available items in a room.
     * @param room as a Room
     */
    public void printAvailableItems(Room room) {
        String availableItems = "Items available: ";
        for (int i = 0; i < room.getItems().size(); i++) {
            if (i <= room.getItems().size() - 2) {
                availableItems += room.getItems().get(i).getItemName() + ", ";
            } else if (i == room.getItems().size() - 1) {
                availableItems += room.getItems().get(i).getItemName() + ".";
            }
        }
        System.out.println(availableItems);
    }


    public static Map<String, Item> createItemDictionary(Room room) {
        Map<String, Item> itemDictionary = new HashMap<>();
        for (Item item : room.getItems()) {
            itemDictionary.put(item.getItemName().toLowerCase(), item);
        }
        return itemDictionary;
    }
}
