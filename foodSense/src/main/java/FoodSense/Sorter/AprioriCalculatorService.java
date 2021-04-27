package FoodSense.Sorter;


//import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
//import ReorganizeModel.MatrixNode;

/**
 * @author Benjamin Grimes
 */
public class AprioriCalculatorService {

    /***************************************************************************************
     * runs Apriori's Algorithm on the incoming purchases and returns the results.
     *
     * @param  purchases
     * All items that were bought. Items bought multiple times are ignored
     *
     * @return
     * A linkedList of MatrixNodes for easy addition to matrix
     ******************************************************************************************/
    public static LinkedList<ArrayList<Integer>> AprioriCalc(LinkedList<Integer> purchases){
        LinkedList<ArrayList<Integer>> result = new LinkedList<>();

        ArrayList<Integer> uniquePurchases = convertToArrayList(removeDuplicates(purchases));
        return powerSet(uniquePurchases);
    }

    /**
     * removes duplicate values in a LinkedList. Does not modify original list
     * @param input
     * LinkedList to remove values from
     * @return
     * new list with no duplicates
     */
    protected static LinkedList<Integer> removeDuplicates(LinkedList<Integer> input){
        LinkedList<Integer> result = new LinkedList<>();
        Iterator<Integer> iter = input.iterator();
        while(iter.hasNext()){
            Integer next = iter.next();
            if(result.contains(next)) {
                result.add(next);
            }
        }
        return result;
    }

    /**
     * converts a linkedlist to an arraylist
     * @param input
     * the linkedList to be converted
     * @return
     * the input as an arraylist
     */
    protected static ArrayList<Integer> convertToArrayList(LinkedList<Integer> input){
        ArrayList<Integer> result = new ArrayList<>();
        Iterator<Integer> iter = input.iterator();
        while(iter.hasNext()){
            result.add(iter.next());
        }
        return result;
    }

    /**
     * Used to find the power set of an arraylist
     * @param set
     * an arraylist to find the powerset of
     * @return
     * a linkedList of all subsets of the input
     */
    protected static LinkedList<ArrayList<Integer>> powerSet(ArrayList<Integer> set){
        //LinkedList<ArrayList<Integer>> result = new LinkedList<>();
        return findSubsets(set, set.size());
    }

    /**
     * finds all subsets of length size or lower within the given set
     * @param set
     * the base set
     * @param size
     * the size of subsets to find
     * @return
     */
    private static LinkedList<ArrayList<Integer>> findSubsets(ArrayList<Integer> set, int size){
        LinkedList<ArrayList<Integer>> result = new LinkedList<>();
        if(size == 1) {
            for (int i = 0; i < set.size(); i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(set.get(i));
                result.add(temp);
            }
        }else if(size == 0){
            //do nothing
        }else{
            LinkedList<ArrayList<Integer>>  lowerSets = findSubsets(set, size-1);
            result.addAll(lowerSets);
            Iterator<ArrayList<Integer>> iter = lowerSets.iterator();
            while(iter.hasNext()){
                ArrayList<Integer> currentSet = iter.next();
                for(int i = 0; i < set.size(); i++) {
                    Integer nextVal = set.get(i);
                    if(!currentSet.contains(nextVal)){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.addAll(currentSet);
                        temp.add(nextVal);
                        result.add(temp);
                    }
                }
            }
        }
        return result;
    }
}
