package student.adventure;

import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Room class that initializes room name, description, items in room, list of directions, imageUrl,
 * and videoUrl.
 */
public class Room {
    private String name; // name of room as a String
    private String description; // description of room as a String
    private List<Item> items; // list of items in room
    private List<Direction> directions; // list of directions and rooms they lead to from a room
    private String imageUrl; // urls of room images
    private String videoUrl; // urls of room videos

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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Method to remove item from item list.
     *
     * @param item to be removed
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Method to add item from item list.
     *
     * @param item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Method to test for room validity.
     *
     * @throws NullPointerException if any of the room elements is null
     */
    public void isValidRoom() {
        if (name == null || description == null || items == null || directions == null ||
            imageUrl == null || videoUrl == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Helper method that prints out all available directions from a room.
     *
     * @throws NullPointerException if direction is null
     */
    public void printAvailableDirections() {
        String availableDirections = "";
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
                availableDirections += directions.get(i).getDirectionName();
            }
        }
        System.out.println("From here, you can go: " + availableDirections);
    }

    /**
     * Helper method that adds all available directions to a list.
     *
     * @return direction as a list of Strings
     */
    public List<String> directionToList() {
        List<String> directionList = new ArrayList<>();
        for (Direction direction: directions) {
            directionList.add(direction.getDirectionName());
        }
        return directionList;
    }

    /**
     * Helper method that prints out all available items in a room.
     *
     * @param room to print available items from
     */
    public void printAvailableItems(Room room) {
        String availableItems = "";
        for (int i = 0; i < room.getItems().size(); i++) {
            if (i <= room.getItems().size() - 2) {
                availableItems += room.getItems().get(i).getItemName() + ", ";
            } else if (i == room.getItems().size() - 1) {
                availableItems += room.getItems().get(i).getItemName();
            }
        }
        System.out.println("Items available: " + availableItems);
    }

    /**
     * Helper method that puts items into a list.
     *
     * @return items as a list
     */
    public List<String> itemToList() {
        List<String> itemList = new ArrayList<>();
        for (Item item: items) {
            itemList.add(item.getItemName());
        }
        return itemList;
    }

    /**
     * Item dictionary that directs to items from names.
     *
     * @param room to put item in
     * @return map of item dictionary with room names and keys and item names as values
     */
    public static Map<String, Item> createItemDictionary(Room room) {
        Map<String, Item> itemDictionary = new HashMap<>();
        for (Item item : room.getItems()) {
            itemDictionary.put(item.getItemName().toLowerCase(), item);
        }
        return itemDictionary;
    }
}
