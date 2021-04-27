package FoodSense.Sorter;

import FoodSense.InventoryService;
import FoodSense.inventory.InventoryController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;


/**
 * @author Benjamin Grimes
 */
@SpringBootApplication
@RestController
@Service
public class ReorganizeController {

    double progress;
    ReorganizeModel model;
    ReorganizeView view;
    ProximityCalculatorService proximCalc;
    InventoryController inventory;

    /**
     * constructor for reorganizeController. also need to
     * setInventory for it to work.
     */
    public ReorganizeController() {
        model = new ReorganizeModel();
        view = new ReorganizeView();
        progress = 0;
        proximCalc = new ProximityCalculatorService();
        inventory = new InventoryController();
        //inventory = inv;
        //InventoryService.setInventory(inv);
    }

    public void setInventory(InventoryController inventory) {
        this.inventory = inventory;
        InventoryService.setInventory(inventory);
    }

    /***************************************************************************
     * Called by Register to let the Sorter know of new purchases
     *
     * @param purchases
     * the IDs of purchased items. duplicate values will be ignored
     * multiple times
     *
     ****************************************************************************/
    public void addPurchase(LinkedList<Integer> purchases) {
        model.updateMatrix(AprioriCalculatorService.AprioriCalc(purchases));
    }

    /*****************************************************************************
     * The controller for the class. Used as the entry point for reorganization
     ********************************************************************************/
    public void reorganize() {
        progress = 0;
        proximCalc.setPurchaseData(model.getPurchaseData());
        Thread newThread = new Thread(proximCalc);
        view.displayProgressBar();
        newThread.start();
        boolean stillRunning = true;
        while(stillRunning){
            double progress = proximCalc.getProgress();
            view.updateProgressBar(progress);
            view.displayProgressBar();
            if(progress == 100){
                stillRunning = false;
            }
        }
        inventory.updateSorting(proximCalc.getLastSorting());
        view.displayCompletionScreen();
    }
    @GetMapping("/sorter")
    public String display(){
        return view.displayScreen();
    }
    @PostMapping("/sorter")
    @PutMapping("/sorter")
    public String run(){
        reorganize();
        return view.displayScreen();
    }
    /*
    public static void main(String[] args) {
        SpringApplication.run(ReorganizeController.class,args);
        SpringApplication app = new SpringApplication(ReorganizeController.class);
        //SpringApplication application = new SpringApplication();
        //InventoryController controller = new InventoryController();
        //SpringApplication.run(controller.class,args);
    }*/
}