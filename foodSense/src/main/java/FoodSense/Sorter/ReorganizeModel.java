package FoodSense.Sorter;

import java.util.*;


/**
 * @author Benjamin Grimes
 */

public class ReorganizeModel {

    //used to store the items that were bought together (key) and the amount
    //of times said items have been bought together
    private HashMap<ArrayList<Integer>, Integer> matrix;

    public ReorganizeModel(){
        matrix = new HashMap<>();
    }

    /**********************************************************************
     * used to update the matrix with new entries or updated values
     *
     * @param data
     * purchase data that has been preprocessed by Apriori's Algorithm
     ************************************************************************/
    protected void updateMatrix(LinkedList<ArrayList<Integer>> data){
        Iterator<ArrayList<Integer>> iter = data.iterator();
        while(iter.hasNext()){
            ArrayList<Integer> next = iter.next();
            next.sort(null);
            Integer count = 1;
            if(matrix.containsKey(next)){
                count += matrix.get(next);
            }
            matrix.put(next, count);
        }
    }


    /**
     * used to retrieve a full list of all processed data within matrix
     *
     * @return
     * All data stored within matrix in LinkedList format
     */
    protected LinkedList<MatrixNode> getPurchaseData(){
        LinkedList<MatrixNode> result = new LinkedList<>();
        Set<ArrayList<Integer>> keySet = matrix.keySet();
        Iterator<ArrayList<Integer>> iter = keySet.iterator();
        while(iter.hasNext()){
            ArrayList<Integer> nextKey = iter.next();
            MatrixNode temp = new MatrixNode(nextKey, matrix.get(nextKey));
            result.add(temp);
        }
        return result;
    }
}
