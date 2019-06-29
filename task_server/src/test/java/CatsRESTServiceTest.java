import com.catsserver.CatsRESTService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;



public class CatsRESTServiceTest extends JerseyTest {

    //private WebTarget webTarget;
    //private

    @Override
    public Application configure() {
        return new ResourceConfig(CatsRESTService.class);
    }


    @Test
    public void testGetAll() {

        Response response = target("/cats").queryParam("tt", "tt")
                .request().get();
        assertEquals("Http Response should be 200: ",
                Response.Status.OK.getStatusCode(), response.getStatus());
        System.out.println(response.getHeaders());
    }
    /*@Test
    public void testAddCat() {
        Response response = target("/cat").request().get();
        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
    }*/
}
