package FoodSense;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import FoodSense.Sorter.ReorganizeController;
import FoodSense.inventory.InventoryController;
import FoodSense.Register.RegisterController;

@SpringBootApplication
public class FoodSenseApplication {

    private InventoryController inventory;
    private ReorganizeController sorter;
    private RegisterController register;

    public FoodSenseApplication(){
        inventory = new InventoryController();
        sorter = new ReorganizeController(inventory);
        register = new RegisterController(inventory);
    }


    public static void main(String[] args) {
        SpringApplication.run(FoodSenseApplication.class, args);
    }

}
