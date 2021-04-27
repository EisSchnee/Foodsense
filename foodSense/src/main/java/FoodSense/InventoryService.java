package FoodSense;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.LinkedList;

import FoodSense.inventory.InventoryController;
import FoodSense.inventory.Item;

/**
 * @author Benjamin Grimes
 */
public class InventoryService {

   static InventoryController inventory;
   public static void setInventory(InventoryController inv){
       inventory = inv;
   }

    static public HashMap<Integer, Integer> getActiveAislesAndIDs() {
        HashMap<Integer, Integer> result = new HashMap<>();
        if(inventory != null) {
            ArrayList<Item> allItems = inventory.getItems();
            if(allItems != null) {
                for (int i = 0; i < allItems.size(); i++) {
                    Item item = allItems.get(i);
                    //result.put(item.getItemID(), item.getAisle());
                }
            }
        }
        return result;
    }


    public static ArrayList<Double> getPrices(InventoryController inventory, ArrayList<Integer> IDs){
        ArrayList<Double> result = new ArrayList<>();
        if(inventory != null) {
            ArrayList<Item> allItems = inventory.getItems();

            for (int i = 0; i < allItems.size(); i++) {
                Item item = allItems.get(i);
                //result.add(item.getPrice());
            }
        }
        return result;
    }
}
