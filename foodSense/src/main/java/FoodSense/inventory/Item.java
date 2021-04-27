package FoodSense.inventory;

/**
 * @author Joseph Grieser
 */
public class Item {

    private int ItemID;
    private String name;
    private boolean inStock;
    private int aisle;
    private double price;

    /**
     * Holds the information for an item in the store
     * @param name name of item
     * @param ItemID ID of item
     */
    public Item(String name, int ItemID){
        this.name = name;
        this.ItemID = ItemID;
        inStock = true;
        aisle = -1;
        price = 0;
    }

    public Item(String name, int ItemID, int aisle){
        this.name = name;
        this.ItemID = ItemID;
        inStock = true;
        this.aisle = aisle;
        price = 0;
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
     * Retrieves aisle of item
     * @return location of item
     */
    public int getAisle() {
        return aisle;
    }

    /**
     * Changes aisle of item
     * @param newAisle new aisle of item
     */
    public void setAisle(int newAisle){
        aisle = newAisle;
    }

    /**
     * Retrieves price ot item
     * @return price of item
     */
    public double getPrice(){
        return price;
    }

    /**
     * Changes the price of item
     * @param price new price of item
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * String of item
     * @return String of attributes in item
     */
    public String toString(){
        return "Name: " + name + "  ID: " + ItemID + "  Aisle: " + aisle + "  Price: " + price;
    }
}
