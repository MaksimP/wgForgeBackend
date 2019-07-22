import com.catsserver.controller.CatsRESTController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CatsRESTControllerTest extends JerseyTest {

    @Override
    public Application configure() {
        return new ResourceConfig(CatsRESTController.class);
    }


    @Test
    public void testGetAll() {

        Response response = target("/cats").queryParam("tt", "tt")
                .request().get();
        assertEquals("Http Response should be 200: ",
                Response.Status.OK.getStatusCode(), response.getStatus());
        System.out.println(response.getHeaders());
    }

    @Test
    public void testColorAddCat() {
        Response response = target("/cat").request()
                .post(Entity.json("{\"name\": \"Timmm\", \"color\":" +
                        " \"yy\", \"tail_length\": 15, \"whiskers_length\": 12}"));
        assertEquals("Http Response should be 400: ",
                Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testTailAddCat() {
        Response response = target("/cat").request()
                .post(Entity.json("{\"name\": \"Timmm\", \"color\":" +
                        " \"black & white\", \"tail_length\": -15, \"whiskers_length\": 12}"));
        assertEquals("Http Response should be 400: ",
                Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testWhiskersAddCatTail() {
        Response response = target("/cat").request()
                .post(Entity.json("{\"name\": \"Timmm\", \"color\":" +
                        " \"black & white\", \"tail_length\": 15, \"whiskers_length\": -12}"));
        assertEquals("Http Response should be 400: ",
                Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
