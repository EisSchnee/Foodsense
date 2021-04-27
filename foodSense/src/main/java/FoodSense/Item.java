// package inventory;
package FoodSense;

/**
 * @author Joseph Grieser
 */
public class Item {

    private int ItemID;
    private String name;
    private boolean inStock;
    private String location;

    /**
     * Holds the information for an item in the store
     * @param name name of item
     * @param ItemID ID of item
     */
    public Item(String name, int ItemID){
        this.name = name;
        this.ItemID = ItemID;
        inStock = true;
        location = "";
    }

    /**
     * Retrieves the ID of the item
     * @return the ID of the item
     */
    public int getItemID() {
        return ItemID;
    }

    /**
     * Retrieves the name of the item
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if item is in stock
     * @return true if item is in stock; false if item is not in stock
     */
    public boolean isInStock() {
        return inStock;
    }

    public void removeFromStock(){
        inStock = false;
    }

    /**
     * Retrieves location of item
     * @return location of item
     */
    public String getLocation() {
        return location;
    }

    /**
     * Changes location of item
     * @param newLoc new location of item
     */
    public void setLocation(String newLoc){
        location = newLoc;
    }

    /**
     * String of item
     * @return String of attributes in item
     */
    public String toString(){
        return "Name: " + name + "  ID: " + ItemID + "\n";
    }
}
