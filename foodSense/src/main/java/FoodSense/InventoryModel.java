// package inventory;
package FoodSense;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joseph Grieser
 */

public class InventoryModel {

    private HashMap<Integer, Item> items;
    //private InventoryController controller;
    private InventoryView view;

    /**
     * Holds the inventory
     */
    public InventoryModel(){
        items = new HashMap<>();
    }

    //public void setController(InventoryController controller){
    //    this.controller = controller;
    //}

    public void setView(InventoryView view){
        this.view = view;
    }

    /**
     * Adds item to inventory
     * @param item item to be added to inventory
     */
    public void addItem(Item item){
        items.put(item.getItemID(),item);
        view.updateInventory(getItems());
    }

    /**
     * Retrieves list of items in inventory
     * @return list of items in inventory
     */
    public ArrayList<Item> getItems() {
        ArrayList<Item> list = new ArrayList<Item>(items.values());
        return list;
    }

    /**
     * Removes item in invventory
     * @param ID ID of item to be removed
     * @return removed item
     */
    public Item removeItem(int ID){
        //return items.remove(ID);
        if(items.get(ID) != null) {
            items.get(ID).removeFromStock();
            Item temp = items.get(ID);
            view.updateInventory(getItems());
            return temp;
        }
        return null;
    }

}
