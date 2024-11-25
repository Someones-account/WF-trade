import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Retriever {

    private final HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<JsonNode> activeOrders = new ArrayList<>();

    public ArrayList<JsonNode> getActiveOrders(String item) throws IOException, InterruptedException {
        String URLString = String.format("https://api.warframe.market/v1/items/%s/orders", item);
        HttpRequest getRequest = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(
                URI.create(URLString)).build();
        HttpResponse<String> response = this.client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        JsonNode itemOrders = mapper.readTree(response.body());
        try {
            JsonNode allOrders = itemOrders.get("payload").get("orders");

            for (JsonNode order : allOrders) {
                String orderType = order.get("order_type").asText();
                String orderStatus = order.get("user").get("status").asText();
                if (orderType.equals("sell") && orderStatus.equals("ingame")) {
                    activeOrders.add(order);
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Incorrect input");
        }

        return activeOrders;
    }

    public ArrayList<Integer> getPrices() throws IOException, InterruptedException {
        ArrayList<Integer> prices = new ArrayList<>();

        if (activeOrders.isEmpty()) {
            prices.add(0);
        }
        for (JsonNode order : activeOrders) {
            prices.add(order.get("platinum").asInt());
        }
        return prices;
    }
}
