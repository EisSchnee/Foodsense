import java.util.ArrayList;

public class InventoryController {

    public Item createItem(String name, int ID){
        return new Item(name,ID);
    }

    public ArrayList<Item> getItems(){
        return null;
    }

    public String lowStock(String name){
        return "Low on " + name;
    }
}
