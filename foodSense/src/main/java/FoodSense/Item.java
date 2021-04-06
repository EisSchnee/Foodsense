public class Item {

    private int ItemID;
    private String name;
    private boolean inStock;
    private String location;

    public Item(String name, int ItemID){
        this.name = name;
        this.ItemID = ItemID;
        inStock = true;
        location = "";
    }

    public int getItemID() {
        return ItemID;
    }

    public String getName() {
        return name;
    }

    public boolean isInStock() {
        return inStock;
    }

    public String getLocation() {
        return location;
    }

    public String toString(){
        return "Name: " + " ID: " + " In Stock: " + " Location: ";
    }
}
