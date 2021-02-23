package student.adventure;

/**
 * Item class that stores and returns item names.
 */
public class Item {

    private String itemName;

    public Item(String setItemName) {
        itemName = setItemName;
    }

    public String getItemName() {
        return itemName;
    }

    /**
     * Method to test for valid item.
     */
    public void isValidItem() {
        if (itemName == null) {
            throw new NullPointerException();
        }
    }
}
