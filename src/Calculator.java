import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {

    public int minPrice(ArrayList<Integer> price_list) {
        int min = price_list.get(0);
        for (int price : price_list) {
            if (price < min) {
                min = price;
            }
        }
        return min;
    }

    public TreeMap<Integer, Integer> occurrences(ArrayList<Integer> price_list) {
        TreeMap<Integer, Integer> offer_numbers = new TreeMap<>();
        for (int price : price_list) {
            offer_numbers.compute(price, (k, counter) -> (counter == null) ? 1 : counter + 1);
        }
        return offer_numbers;
    }

    public Integer[] getMode(TreeMap<Integer, Integer> offer_numbers) {
        Integer[] mode = new Integer[2];
        Map.Entry<Integer, Integer> entry = offer_numbers.firstEntry();
        mode[0] = entry.getKey();
        mode[1] = entry.getValue();
        return mode;
    }

    public TreeMap<Integer, Integer> defineStability(TreeMap<Integer, Integer> order_numbers) {
        // Show difference between mode and minimum value
    }
}