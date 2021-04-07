package FoodSense.Sorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * @author Benjamin Grimes
 */
public class ReorganizeModel {

    //used to store the items that were bought together (key) and the amount
    //of times said items have been bought together
    private HashMap<ArrayList<Integer>, Integer> matrix;

    /******************************************************************************
     * a subclass to store the list and occurences of items bought together.
     * list will always be sorted to avoid separate entries for the same group.
     *******************************************************************************/
    protected class MatrixNode {
        private ArrayList<Integer> items;
        private int count;

        MatrixNode(ArrayList<Integer> items, int count){
            this.count = count;
            items.sort(null);
            this.items = items;
        }

        protected ArrayList<Integer> getItems(){
            return items;
        }

        protected int getCount(){
            return count;
        }
    }

    /**********************************************************************
     * used to update the matrix with new entries or updated values
     *
     * @param data
     * purchase data that has been preprocessed by Apriori's Algorithm
     ************************************************************************/
    private void UpdateMatrix(LinkedList<MatrixNode> data){
        //TODO
    }


    /**
     * used to retrieve a full list of all processed data within matrix
     *
     * @return
     * All data stored within matrix in LinkedList format
     */
    private LinkedList<MatrixNode> getPurchaseData(){
        //TODO
        return new LinkedList<MatrixNode>();
    }
}
