package org.acme;

import io.cucumber.java.sl.In;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/merchant")
public class MerchantResource {

    MerchantService service = MerchantService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setCustomer(Merchant merchant){
        String merchantId = service.setMerchant(merchant);
        return Response.ok().entity(merchantId).build();
    }





}
