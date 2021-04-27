package FoodSense;

import java.util.ArrayList;

public class Items {
    // public static class Item extends FoodSense.Item {
    public static class Item extends FoodSense.inventory.Item {
        public Item(String name, int id) {
            super(name, id);
        }
        public double getItemPrice() {
            return -1;
        }
    }
    public static ArrayList<Items.Item> getItems() {
        return null;
    }
}
