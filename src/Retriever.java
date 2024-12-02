import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Retriever {

    private final HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<JsonNode> activeSellOrders = new ArrayList<>();
    ArrayList<JsonNode> activeBuyOrders = new ArrayList<>();

    public void getActiveOrders(String item) throws IOException, InterruptedException {
        String URLString = String.format("https://api.warframe.market/v1/items/%s/orders", item);
        HttpRequest getRequest = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(
                URI.create(URLString)).build();
        HttpResponse<String> response = this.client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        JsonNode itemOrders = mapper.readTree(response.body());
        try {
            JsonNode allOrders = itemOrders.get("payload").get("orders");
            activeSellOrders.clear();
            activeBuyOrders.clear();

            for (JsonNode order : allOrders) {
                String orderType = order.get("order_type").asText();
                String orderStatus = order.get("user").get("status").asText();
                if (orderType.equals("sell") && orderStatus.equals("ingame")) {
                    activeSellOrders.add(order);
                }
                else if (orderType.equals("buy") && orderStatus.equals("ingame")) {
                    activeBuyOrders.add(order);
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Incorrect input");
        }
    }

    public ArrayList<Integer> getPrices(String ordersType) throws IOException, InterruptedException {
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<JsonNode> orders = ordersType == "sell" ? activeSellOrders : activeBuyOrders;

        if (orders.isEmpty()) {
            prices.add(0);
        }
        for (JsonNode order : orders) {
            prices.add(order.get("platinum").asInt());
        }
        return prices;
    }
}
