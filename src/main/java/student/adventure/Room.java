package student.adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private List<Item> items;
    private List<Direction> directions;
    public Room(String setName, String setDescription, List<Item> setItems, List<Direction> setDirections) {
        name = setName;
        description = setDescription;
        items = setItems;
        directions = setDirections;
    }

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

    // helper method that returns all available directions from a room.
    public void returnAvailableDirections() {
        String availableDirections = "From here, you can go: ";
        if (directions == null) {
            throw new NullPointerException();
        }
        if (directions.size() == 0) {
            availableDirections += "";
        }
        for (int i = 0; i < directions.size(); i++) {
            availableDirections += directions.get(i).getDirectionName();
        }
        System.out.println(availableDirections);
    }

    // helper method that returns all available items in a room.
    public void returnAvailableItems() {
        String availableItems = "Items visible: ";
        if (items == null) {
            throw new NullPointerException();
        }
        if (items.size() == 0) {
            availableItems += "";
        }
        for (int i = 0; i < items.size(); i++) {
            availableItems += items.get(i).getItemName();
        }
        System.out.println(availableItems);
    }
}
