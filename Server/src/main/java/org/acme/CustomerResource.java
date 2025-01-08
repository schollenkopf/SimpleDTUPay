package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customer")
public class CustomerResource {

    CustomerService service = CustomerService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setCustomer(Customer customer){
        String customerId = service.setCustomer(customer);
        return Response.ok().entity(customerId).build();
    }

}
