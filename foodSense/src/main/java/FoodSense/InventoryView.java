package FoodSense.inventory;

import java.util.ArrayList;

/**
 * @author Joseph Grieser
 */
public class InventoryView {

    private ArrayList<Item> inventory;
    //private InventoryController controller;
    //private InventoryModel model;

    public InventoryView(){
        inventory = null;
    }

    //public void setController(InventoryController controller){
    //    this.controller = controller;
    //}

    //public void setModel(InventoryModel model){
    //    this.model = model;
    //}

    /**
     * Replaces the inventory with the new inventory
     * @param newList inventory items that will replace current inventory
     */
    public void updateInventory(ArrayList<Item> newList){
        inventory = newList;
    }

    /**
     * displays inventory for a web page
     * @return the HTML for a web page displaying the inventory
     */
    public String displayInventory(){
        //System.out.println("Inventory:\n");
        //for(com.example.demo.inventory.Item i: inventory){
        //    System.out.println(i.toString());
        //}
        String output = "<h3>Inventory:</h2><ul>";
        if(inventory == null){
            output += "<li>No items</li>";
        }
        else {
            for (Item i : inventory) {
                if(i.isInStock()) {
                    output += "<li>" + i + "</li>";
                }
            }
        }
        output += "</ul>";
        return output;
    }
}
