package FoodSense.Register;

public class PurchaseInfo {

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
