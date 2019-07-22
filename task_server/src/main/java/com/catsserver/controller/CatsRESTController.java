package com.catsserver.controller;

import com.catsserver.dao.AddCatsDAO;
import com.catsserver.dao.AddCatsDAOImpl;
import com.catsserver.dao.GetCatsDAO;
import com.catsserver.dao.GetCatsDAOImpl;
import com.catsserver.model.Cat;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/")
public class CatsRESTController {

    private GetCatsDAO getCatsDAO = new GetCatsDAOImpl();
    private AddCatsDAO addCatsDAO = new AddCatsDAOImpl();

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
        return getCatsDAO.getAll(attribute, order, limit, offset);
    }

    @POST
    @Path("/cat")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCats(@Valid Cat cat) {
        try {
            addCatsDAO.addCats(cat);
        } catch (SQLException e) {
            String error = e.getLocalizedMessage();
            return Response.status(Response.Status.BAD_REQUEST).entity(createEntityMessage(error))
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }

    private String createEntityMessage(String error) {
        int index = error.indexOf(" ");
        String errorColor = "Неверно задан цвет";
        String errorName = "Кот с таким именем уже существует ";
        String entityMessage = null;

        if (error.startsWith("duplicate", index + 1)) {
            entityMessage = errorName;
        } else {
            entityMessage = errorColor;
        }
        return entityMessage;
    }
}
