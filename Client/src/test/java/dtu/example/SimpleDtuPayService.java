package dtu.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class SimpleDtuPayService {
    Client c = ClientBuilder.newClient();
    WebTarget baseURL =
            c.target("http://localhost:8080/");
    public String register(Customer customer) {
        Response response = baseURL.path("/customer").request().post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    public String register(Merchant merchant) {
        Response response = baseURL.path("/merchant").request().post(Entity.entity(merchant, MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }

    public void pay(Integer amount, String customerId, String merchantId) throws UnknownPersonException{
        Response response = baseURL.path("/payment").request().post(Entity.entity(new PayPost(amount,customerId,merchantId),MediaType.APPLICATION_JSON));
        if (response.getStatus() != 204) {
            throw new UnknownPersonException(response.readEntity(String.class));
        }
    }


    public PayListEntry[] getPayPosts() {
        Response response = baseURL.path("/payment").request().get();
        return response.readEntity(PayListEntry[].class);
    }
}
