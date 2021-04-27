package FoodSense.Register;

import java.util.ArrayList;

/**
 * Model class for the Register component
 * @author Nahom Ebssa
 */
public class RegisterModel {

    /**
     * Contains the list of purchased items
     */
    private ArrayList<PurchaseInfo> PurchaseItems;


    public RegisterModel() {
        super();
        PurchaseItems = new ArrayList<>();

        // mock model
        // TODO: replace this
        // ArrayList<PurchaseInfo> pinfo = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // pinfo.add(new PurchaseInfo(i, 20.20 + i, 1));
            PurchaseItems.add(new PurchaseInfo(i, 20.20 + i, 1));
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


    protected static class PurchaseInfo {

        /**
         * Item id for uniqueness
         */
        private int PurchaseId;
        
        /**
         * Item price
         */
        private double Price;
        
        /**
         * Item's quantity
         */
        private int Quantity;
    
        public PurchaseInfo(int id, double price, int quantity)
        {
            this.PurchaseId = id;
            this.Price = price;
            this.Quantity = quantity;
        }
    
        /**
         * Getter for a purchased item's id
         * @return the id
         */
        public int getPurchaseId()
        {
            return this.PurchaseId;
        }
    
        /**
         * Getter for a purchased item's price
         * @return the item's price
         */
        public double getPrice()
        {
            return this.Price;
        }
    
        /**
         * Getter for a purchased item's quantity
         * @return the item's quantity
         */
        public int getQuantity()
        {
            return this.Quantity;
        }   
    
    }

}
