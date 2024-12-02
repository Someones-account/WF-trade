import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {
    public TreeMap<Integer, Integer> occurrences(ArrayList<JsonNode> orders) {
        TreeMap<Integer, Integer> offer_numbers = new TreeMap<>();
        for (JsonNode order : orders) {
            offer_numbers.compute(order.get("platinum").asInt(), (k, counter) -> (counter == null) ?
                    order.get("quantity").asInt() : counter + order.get("quantity").asInt());
        }
        return offer_numbers;
    }

    public int minPrice(TreeMap<Integer, Integer> prices) {
        return prices.firstEntry().getKey();
    }

    public int minPrice(ArrayList<Integer> price_list) {
        int min = price_list.get(0);
        for (int price : price_list) {
            if (price < min) {
                min = price;
            }
        }
        return min;
    }

    public int maxPrice(ArrayList<Integer> price_list) {
        int max = price_list.get(0);
        for (int price : price_list) {
            if (price > max) {
                max = price;
            }
        }
        return max;
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

    public int priceSpread(ArrayList<Integer> selling, ArrayList<Integer> buying) {
        int maxBuy = maxPrice(buying);
        int minSell = minPrice(selling);
        return maxBuy - minSell;
    }
}