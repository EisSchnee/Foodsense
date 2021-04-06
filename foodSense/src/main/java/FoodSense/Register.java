/**
 * Class for the Register component
 * @author Nahom Ebssa
 * Last updated 4/6/2021
 */

package FoodSense;

public class Register {
    
    private RegisterController _controller;
    private RegisterView _view;
    private RegisterMode _model;


    /**
     * Gives access to the purchase item's prices by id
     * @return list of prices
     */
    public ArrayList<Double> getPrices(ArrayList<Integer> listIds) {
        ArrayList<PurchaseInfo> items = _model.getPurchaseItems();
        ArrayList<Double> listPrices = new ArrayList<>();
        for (int i = 0; i < listIds.size(); i++)
        {
            listPrices.add(items.get(listIds.get(i)));
        }
        return listPrices
    }
    
    /**
     * Adds a purchase to the list
     * @param list list of purchase infos
     */
    public void AddPurchase(ArrayList<Integer>) {
        
    }
    
}


class RegisterController {

    /**
     * Takes a list of purchases and peforms the transaction
     * @param list list of purchase infos
     */
    public void RegisterPurchaseTransaction(ArrayList<PurchaseInfo> list)
    {
        // TODO: handle transaction
    }
}
class RegisterView {

    /**
     * Attempts to display the list of purchased items
     * @param list list of purchase infos
     * @return true on success false otherwise
     */
    public boolean DisplayPurchase(ArrayList<PurchaseInfo> list)
    {
        // TODO: perform ui actions to render the list of purchased items
        return false;
    }
}
class RegisterModel {
    
    /**
     * Contains the list of purchased items
     */
    private ArrayList<PurchaseInfo> PurchaseItems;
    
    /**
     * Returns the list of purchased items
     * @return list of purchased items
     */
    public ArrayList<PurchaseInfo> getPurchaseItems()
    {
        return this.PurchaseItems
    }
    
    /**
     * @param id index of the item to edit
     * @param info purchase item info
     * Edits the list of purchased items at index id
     */
    public void setPurchaseItems(int id, PurchaseInfo info)
    {
        PurchaseItems.remove(id);
		PurchaseItems.add(id, info);
    }
}
class PurchaseInfo {

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