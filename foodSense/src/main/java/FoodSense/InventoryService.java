package FoodSense;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Benjamin Grimes
 */
public class InventoryService {



    public LinkedList<Integer> getActiveIDs() {
        ArrayList<Items.Item> allItems = Items.getItems();
        LinkedList<Integer> IDs = new LinkedList<>();
        for(int i = 0; i < allItems.size(); i++){
            IDs.add(allItems.get(i).getItemID());
        }
        return IDs;
    }


    public ArrayList<Double> getPrices(ArrayList<Integer> IDs){
        ArrayList<Items.Item> allItems = Items.getItems();
        ArrayList<Double> prices = new ArrayList<>();
        prices.ensureCapacity(IDs.size());
        for(int i = 0; i < allItems.size(); i++){
            for(int j = 0; j < IDs.size(); j++){
                if(allItems.get(i).getItemID() == IDs.get(j)){
                    prices.set(j, allItems.get(i).getItemPrice());
                }
            }
        }
        return prices;
    }
}
