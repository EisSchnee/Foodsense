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
            PurchaseItems.add(new PurchaseInfo(i, 20.20 + i, 1));
    }
    public RegisterModel(InventoryController ic) {
        PurchaseItems = new ArrayList<>();
        inventory = ic;

        if (inventory != null) {
            // TODO: keys or values
            Map<Integer, Integer> idMap = FoodSense.InventoryService.getActiveAislesAndIDs();
            FoodSense.InventoryService.getPrices(inventory, new ArrayList<Integer>(idMap.values()));
        }
    }
    
    /**
     * Returns the list of purchased items
     * @return list of purchased items
     */
    public ArrayList<PurchaseInfo> getPurchaseItems()
    {
        
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
