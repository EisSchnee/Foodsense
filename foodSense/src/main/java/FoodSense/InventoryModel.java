import java.util.ArrayList;
import java.util.HashMap;

public class InventoryModel {

    private HashMap<Integer,Item> items;

    public InventoryModel(){
        items = new HashMap<>();
    }

    public void addItem(Item item){
        items.put(item.getItemID(),item);
    }

    public ArrayList<String> getItems() {
        return null;
    }

    public Item removeItem(int ID){
        return items.remove(ID);
    }


}
