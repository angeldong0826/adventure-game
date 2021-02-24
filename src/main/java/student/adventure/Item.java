package student.adventure;

/**
 * Item class that stores and returns item names.
 */
public class Item {
    private String itemName; // name of item

    /**
     * Constructor that initializes items.
     *
     * @param setItemName name of item
     */
    public Item(String setItemName) {
        itemName = setItemName;
    }

    public String getItemName() {
        return itemName;
    }

    /**
     * Method to test for item validity.
     *
     * @throws NullPointerException if item is null
     */
    public void isValidItem() {
        if (itemName == null) {
            throw new NullPointerException();
        }
    }
}
