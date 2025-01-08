package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/payment")
public class PaymentResource {

    PaymentService service = new PaymentService();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(PayPost paypost){
        try{
            service.pay(paypost);
            return Response.noContent().build();
        }catch (UnknownPersonException e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayments(){
        PayListEntry[] payments = service.getPayments(); // Ensure this is a List or array
        return Response.ok(payments).build();
        //return Response.ok().entity(Entity.entity(service.getPayments(), MediaType.APPLICATION_JSON)).build();
    }

}
