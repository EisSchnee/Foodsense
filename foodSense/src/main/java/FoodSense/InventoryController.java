package FoodSense.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


/**
 * @author Joseph Grieser
 */
@SpringBootApplication
@RestController
public class InventoryController {

    private InventoryView view;
    private InventoryModel model;

    public InventoryController(){
        view = new InventoryView();
        model = new InventoryModel();
        //view.setController(this);
        //view.setModel(model);
        //model.setController(this);
        model.setView(view);
        //createItem("Pear",0,"Left"); //test code
        //createItem("Apple",1,"Right");
    }

    /**
     * creates a new item
     * @param name name of item
     * @param ID ID of item
     */
    public void createItem(String name, int ID){
        //return new com.example.demo.inventory.Item(name,ID);
        Item item = new Item(name,ID);
        model.addItem(item);
        //view.updateInventory(model.getItems());
    }

    /**
     * creates a new item
     * @param name name of item
     * @param ID ID of item
     * @param location location of item
     */
    public void createItem(String name, int ID, String location){
        Item item = new Item(name,ID,location);
        model.addItem(item);
    }

    //public void createItem(Item item){
    //    model.addItem(item);
    //}

    /**
     * Retrieves list of items
     * @return list of items
     */
    public ArrayList<Item> getItems(){
        ArrayList<Item> list = model.getItems();
        return null;
    }

    /**
     * Sends a message that the inventory is low on stock of a specific item
     * @param name name of item
     * @return message of item with low stock
     */
    public String lowStock(String name){
        return "Low on " + name;
    }

    @GetMapping("/inventory")
    public String displayInventory1(){
        return view.displayInventory();
    }

    //@GetMapping("/inventory2")
    //public String displayInventory2(){
    //    createItem("Pear",0);
    //    createItem("Apple",1);
    //    return view.displayInventory();
    //}

    //@GetMapping("/inventory3")
    //public String displayInventory3(){
    //    model.removeItem(0);
    //    return view.displayInventory();
    //}


    /**
     * To see if the inventory is displaying properly, run this class and go to http:localhost:8080/inventory.
     * If the contents of displayInventory are shown, the class is working.
     */
    public static void main(String[] args) {
        SpringApplication.run(InventoryController.class,args);
        //SpringApplication application = new SpringApplication();
        //InventoryController controller = new InventoryController();
        //SpringApplication.run(controller.class,args);
    }
}
