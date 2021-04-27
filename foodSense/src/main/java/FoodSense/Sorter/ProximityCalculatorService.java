package FoodSense.Sorter;

import FoodSense.InventoryService;
import java.util.*;


/**
 * @author Benjamin Grimes
 */
public class ProximityCalculatorService implements Runnable{


    //ReorganizeModel model;

    //All previous purchases pre-processed by Apriori's Algorithm. Must be
    //inputted before calling reorganizeItems
    LinkedList<MatrixNode> purchaseData;

    //the result from the last call of reorganizeItems
    ArrayList<ArrayList<Integer>> lastSorting;

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
    /**
     * constructor for ProximityCalculatorService
     *
     */
    public ProximityCalculatorService() {
        progress = 0;
        progressLocked = false;
        purchaseData = new LinkedList<>();
    }


    public double getProgress(){
        try{
            lockProgress();
            return this.progress;
        } catch (InterruptedException e) {
            return -1;
        }finally{
            unlockProgress();
        }
    }

    public void setProgress(double progress){
        try{
            lockProgress();
            this.progress = progress;
        } catch (InterruptedException e) {
            //do nothing
        }finally {
            unlockProgress();
        }
    }

    public void setPurchaseData(LinkedList<MatrixNode> data){
        purchaseData = data;
    }

    public ArrayList<ArrayList<Integer>> getLastSorting(){
        return lastSorting;
    }
    /*****************************************************************************
     * calculates what should be near each other based of of the incoming purchase data
     *
     */
    public void reorganizeItems(){
        setProgress(5);

        //key = item id
        //value = aisle of item
        HashMap<Integer, Integer> activeAislesAndIDs = InventoryService.getActiveAislesAndIDs();
        Set<Integer> IDKeySet = activeAislesAndIDs.keySet();

        //sort the incoming purchase data into cross aisle and fully in aisle purchases
        //also delete any purchase data that has a value that does not show up in activeIDs
        LinkedList<MatrixNode> inAislePurchases = new LinkedList<>();
        LinkedList<MatrixNode> crossAislePurchases = new LinkedList<>();
        double divider = purchaseData.size();
        int nextStep = 15;
        double jump = nextStep/ divider;
        Iterator<MatrixNode> iter = purchaseData.iterator();
        while(iter.hasNext()){
            MatrixNode nextNode = iter.next();
            ArrayList<Integer> items = nextNode.getItems();
            int aisle = items.get(0);
            boolean inAisle = true;
            boolean active = activeAislesAndIDs.containsKey(items.get(0));
            for(int i = 1; i < items.size() && inAisle && active; i++){
                if(!activeAislesAndIDs.containsKey(items.get(i))){
                    active = false;
                }else if(activeAislesAndIDs.get(items.get(i)) != aisle){
                    inAisle = false;
                }
            }
            if(active && inAisle){
                inAislePurchases.add(nextNode);
            }else if(active){
                crossAislePurchases.add(nextNode);
            }
            double temp = getProgress();
            setProgress(temp+jump);
        }
        setProgress(15);


        //inner LikedList should be sorted by MatrixNode count in descending
        //middle(inAisle)/outer(crossAisle) LinkedList should be sorted by MatrixNode items.size() in ascending
        //outer(inAisle) represents their aisles
        LinkedList<LinkedList<LinkedList<MatrixNode>>> inAisleSorted = new LinkedList<>();

        //key = aisle number
        //value = index
        HashMap<Integer, Integer> seenAisles = new HashMap<>();
        //ArrayList<ListIterator<LinkedList<MatrixNode>> >middleIters = new ArrayList<>();
        //ArrayList<ListIterator<MatrixNode>> innerIters = new ArrayList<>();

        iter = inAislePurchases.iterator();
        divider = inAislePurchases.size();
        nextStep = 40;
        jump = nextStep/ divider;
        while(iter.hasNext()){
            MatrixNode next = iter.next();
            int size = next.getItems().size();
            int aisle = activeAislesAndIDs.get(next.getItems().get(0));
            if(seenAisles.containsKey(aisle)){
                int OuterIndex = seenAisles.get(aisle);
                boolean sizeFound= false;
                Iterator<LinkedList<MatrixNode>> iter2 = inAisleSorted.get(OuterIndex).iterator();
                LinkedList<MatrixNode> current = new LinkedList<>();
                while(iter2.hasNext() && !sizeFound){
                    current = iter2.next();
                    sizeFound = current.getFirst().getItems().size() == size;
                }
                if(sizeFound){

                   ListIterator<MatrixNode> listIter =  current.listIterator();
                   boolean added = false;
                   while(listIter.hasNext() && !added){
                       if(listIter.next().getCount()<next.getCount()){
                           listIter.previous();
                           listIter.add(next);
                           added=true;
                       }
                   }
                   if(!added) {
                       current.add(next);
                   }
                }else{
                    current = new LinkedList<>();
                    current.add(next);
                    LinkedList<LinkedList<MatrixNode>> outerList = inAisleSorted.get(OuterIndex);
                    ListIterator<LinkedList<MatrixNode>> listIter = outerList.listIterator();
                    boolean added = false;
                    while(listIter.hasNext() && !added){
                        if(listIter.next().getFirst().getItems().size()>next.getItems().size()){
                            listIter.previous();
                            listIter.add(current);
                            added=true;
                        }
                    }
                    if(!added) {
                        outerList.add(current);
                    }
                }
            }else{
                LinkedList<MatrixNode> innerList = new LinkedList<>();
                innerList.add(next);
                LinkedList<LinkedList<MatrixNode>> outerList = new LinkedList<>();
                outerList.add(innerList);
                seenAisles.put(aisle, inAisleSorted.size()-1);
                inAisleSorted.add(outerList);
            }
            double temp = getProgress();
            setProgress(temp+jump);
        }
        setProgress(55);

        //should process this data to make it based off aisle rather than id
        //LinkedList<LinkedList<MatrixNode>> crossAisleSorted = new LinkedList<>();


        //start with [0][0] (highest count of the single item purchases), then find the first value
        //in [1] that contains the item ID [0][0]. Go to [2] and find the first value that contains both
        //IDs. Continue doing this until a there is no purchase that has all values. At this point,
        //go down one level, find the first value that contains all ids but the first, and continue.
        //do this until all in aisle purchase data has been processed. Then add any other activeIDs at the
        //end, sorted by their IDs numerically.
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Iterator<LinkedList<LinkedList<MatrixNode>>> aisleIter = inAisleSorted.iterator();

        divider = inAisleSorted.size();
        nextStep = 40;
        jump = nextStep/ divider;
        while(iter.hasNext()){
            //all purchases within the aisle
            LinkedList<LinkedList<MatrixNode>> aisle = aisleIter.next();

            //iterates through the list of purchases that have been organized based on size
            ListIterator<LinkedList<MatrixNode>> sizeIter = aisle.listIterator();

            //the current group of items that are being examined in purchases
            LinkedList<Integer> itemGroup = new LinkedList<>();

            //the resulting sorted aisle
            ArrayList<Integer> sortedAisle = new ArrayList<>();
            boolean cont = true;

            //iterate aisle
            while(cont){
                //all purchases of next size up length
                LinkedList<MatrixNode> currentList = sizeIter.next();

                ListIterator<MatrixNode> compareIter = currentList.listIterator();
                int maxVal = -1;
                MatrixNode maxNode = new MatrixNode(null, -1);

                //find max
                while(compareIter.hasNext()){
                    MatrixNode current = compareIter.next();
                    if(current.getCount() > maxVal && current.getItems().containsAll(itemGroup)){
                        boolean inList = true;
                        for(int i = 0; i < sortedAisle.size(); i++){
                            if(current.getItems().contains(sortedAisle.get(i))){
                                inList = false;
                                break;
                            }
                        }
                        if(!inList) {
                            maxNode = current;
                            maxVal = current.getCount();
                        }
                    }
                }
                if(maxNode.getCount() == -1){
                    sizeIter.previous();
                    if(sizeIter.hasPrevious()){
                        sizeIter.previous();
                        sortedAisle.add(itemGroup.getFirst());
                        itemGroup.removeFirst();
                    }else{
                        cont = false;
                    }
                }else{
                    itemGroup = new LinkedList<>(maxNode.getItems());

                }
            }
            Iterator<Integer> keyIter = IDKeySet.iterator();
            int aisleNum = activeAislesAndIDs.get(aisle.getFirst().getFirst().getItems().get(0));
            while(iter.hasNext()){
                Integer next = keyIter.next();
                if(activeAislesAndIDs.get(next) == aisleNum && !sortedAisle.contains(next)){
                    sortedAisle.add(next);
                }
            }
            double temp = getProgress();
            setProgress(temp+jump);
            result.add(sortedAisle);
        }
        lastSorting = result;
        setProgress(100);
    }


    @Override
    public void run() {
        reorganizeItems();
    }
}
