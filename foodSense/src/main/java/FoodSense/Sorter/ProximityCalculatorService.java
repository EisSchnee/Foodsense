package FoodSense.Sorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * @author Benjamin Grimes
 */
public class ProximityCalculatorService {


    //shared between multiple concurrent methods to communicate estimated
    //work left until the method has finished
    private double progress;
    //used to make sure there are no concurrent accesses to progress
    private boolean progressLocked;
    /******************************
     * claim the lock on progress
     **********************************/
    private synchronized void lockProgress() throws InterruptedException {
        while(progressLocked){
            wait();
        }
        progressLocked = true;
    }
    /**************************************************
     * release the lock on progress
     ******************************************************/
    private synchronized void unlockProgress(){
        progressLocked = false;
        notifyAll();
    }

    /*****************************************************************************
     * calculates what should be near each other based of of the incoming purchase data
     *
     * @param purchaseData
     * All previous purchases pre-processed by Apriori's Algorithm
     *
     * @return
     * An ArrayList of ArrayLists of Integers. The outer arrayList represents the aisles
     * while the inner arrayList represents the items within said aisles.
     ****************************************************************************************/
    private ArrayList<ArrayList<Integer>> reorganizeItems(LinkedList<ReorganizeModel.MatrixNode> purchaseData){
        //TODO
        return new ArrayList<ArrayList<Integer>>();
    }

    public double getProgress(){
        //TODO
        return 100.0;
    }
}
