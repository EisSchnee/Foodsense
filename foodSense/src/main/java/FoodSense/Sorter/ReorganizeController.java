package FoodSense.Sorter;

import FoodSense.inventory.InventoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * @author Benjamin Grimes
 */
@SpringBootApplication
@RestController
public class ReorganizeController {

    double progress;
    ReorganizeModel model;
    ReorganizeView view;
    ProximityCalculatorService proximCalc;
    InventoryController inventory;

    public ReorganizeController(InventoryController inv) {
        model = new ReorganizeModel();
        view = new ReorganizeView();
        progress = 0;
        proximCalc = new ProximityCalculatorService();
        inventory = inv;
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
        newThread.run();
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
    @Bean
    public InventoryController satisfyBean() {
        return new InventoryController();
    }
}