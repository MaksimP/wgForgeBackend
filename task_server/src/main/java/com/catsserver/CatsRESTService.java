package com.catsserver;

import com.catsserver.dao.CatsDAO;
import com.catsserver.dao.CatsDAOImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

@Path("/")
public class CatsRESTService {

    private CatsDAO catsDAO = new CatsDAOImpl();

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "\"Cats Service. Version 0.1\"";
    }

    @GET
    @Path("/cats")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getCats(@QueryParam("attribute") String attribute,
                                             @QueryParam("order") String order,
                                             @QueryParam("offset") String offset,
                                             @QueryParam("limit") String limit) {
        return catsDAO.getAll(attribute, order, limit, offset);
    }
}
