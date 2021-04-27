package FoodSense.Sorter;

import java.util.ArrayList;

/******************************************************************************
 * a class to store the list and occurences of items bought together.
 * list will always be sorted to avoid separate entries for the same group.
 *
 * moved from ReorganizeModel for easier access for ProximityCalculator.
 *******************************************************************************/
public class MatrixNode {
    private ArrayList<Integer> items;
    private int count;

    MatrixNode(ArrayList<Integer> items, int count){
        this.count = count;
        if(items != null){
            items.sort(null);
        }
        this.items = items;
    }

    protected ArrayList<Integer> getItems(){
        return items;
    }

    protected int getCount(){
        return count;
    }
}
