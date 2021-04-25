package inventory;

import java.util.ArrayList;

/**
 * @author Joseph Grieser
 */
public class InventoryView {

    private ArrayList<Item> inventory;

    public InventoryView(){
        inventory = null;
    }

    public void updateInventory(ArrayList<Item> newList){
        inventory = newList;
    }

    public String displayInventory(){
        //System.out.println("Inventory:\n");
        //for(com.example.demo.Item i: inventory){
        //    System.out.println(i.toString());
        //}
        String output = "Inventory:\n\n";
        if(inventory == null){
            output = output + "No items";
        }
        else {
            for (Item i : inventory) {
                output = output + i.toString();
            }
        }
        return output;
    }
}
