import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {

//    public int minPrice(ArrayList<Integer> price_list) {
//        int min = price_list.get(0);
//        for (int price : price_list) {
//            if (price < min) {
//                min = price;
//            }
//        }
//        return min;
//    }

    public TreeMap<Integer, Integer> occurrences(ArrayList<Integer> price_list) {
        // Make it sum quantities

        TreeMap<Integer, Integer> offer_numbers = new TreeMap<>();
        for (int price : price_list) {
            offer_numbers.compute(price, (k, counter) -> (counter == null) ? 1 : counter + 1);
        }
        return offer_numbers;
    }

    public int minPrice(TreeMap<Integer, Integer> price_list) {
        return price_list.firstEntry().getKey();
    }

    public Integer[] getMode(TreeMap<Integer, Integer> offer_numbers) {
        Integer[] mode = new Integer[2];
        int price = 0;
        int maxOccurrences = offer_numbers.firstEntry().getValue();
        for (Map.Entry<Integer, Integer> entry : offer_numbers.entrySet()) {
            if (entry.getValue() > maxOccurrences) {
                maxOccurrences = entry.getValue();
                price = entry.getKey();
            }
        }
        mode[0] = price;
        mode[1] = maxOccurrences;
        return mode;
    }

    public double priceDrop(TreeMap<Integer, Integer> order_numbers) {
        // Show difference between mode and minimum value

        Integer mode = getMode(order_numbers)[0];
        int leastPrice = minPrice(order_numbers);
        return ((double) (mode - leastPrice) / mode) * 100;
    }
}