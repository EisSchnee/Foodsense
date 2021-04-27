package FoodSense.Register;

import java.util.ArrayList;
import java.util.Map;

import FoodSense.inventory.InventoryController;

/**
 * Model class for the Register component
 * @author Nahom Ebssa
 */
public class RegisterModel {

    /**
     * Contains the list of purchased items
     */
    private ArrayList<PurchaseInfo> PurchaseItems;

    /**
     * InventoryController instance
     */
    private FoodSense.inventory.InventoryController inventory;


    public RegisterModel() {
        PurchaseItems = new ArrayList<>();
        // TODO: replace this
        for (int i = 0; i < 5; i++)
            PurchaseItems.add(new PurchaseInfo(i, 20.20 + i/100, 1));        
    }
    public RegisterModel(InventoryController ic) {
        PurchaseItems = new ArrayList<>();
        inventory = ic;

        if (inventory != null) {
            getPurchaseItems();
        } else {
            // TODO: replace this
            for (int i = 0; i < 5; i++)
                PurchaseItems.add(new PurchaseInfo(i, 20.20 + i, 1));
        }
    }
    
    /**
     * Returns the list of purchased items
     * @return list of purchased items
     */
    public ArrayList<PurchaseInfo> getPurchaseItems()
    {
        
        Map<Integer, Integer> idMap = FoodSense.InventoryService.getActiveAislesAndIDs();
        System.out.println(idMap);
        ArrayList<Integer> ids = new ArrayList<Integer>(idMap.keySet());
        System.out.printf("[RegisterModel(ic)] ids: %s\n", ids);
        ArrayList<Double> prices = FoodSense.InventoryService.getPrices(inventory, ids);
        System.out.printf("[RegisterModel(ic)] prices: %s\n", prices);
        
        // PurchaseItems.clear();
        for (int i = 0; i < ids.size() && i < prices.size(); i++)
            PurchaseItems.add(new PurchaseInfo(ids.get(i), prices.get(i), 1));

        return this.PurchaseItems;
    }
    
    /**
     * Edits the list of purchased items at index id
     * @param id index of the item to edit
     * @param info purchase item info
     */
    public void setPurchaseItems(int id, PurchaseInfo info)
    {
        PurchaseItems.remove(id);
		PurchaseItems.add(id, info);
    }
}
