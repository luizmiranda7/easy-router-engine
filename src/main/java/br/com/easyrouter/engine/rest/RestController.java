package br.com.easyrouter.engine.rest;

import java.io.Writer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.easyrouter.engine.api.RouteRequest;
import br.com.easyrouter.engine.api.RouteResponse;
import br.com.easyrouter.engine.solver.Solver;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.AbstractJsonWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;


@Path("/hello")
public class RestController {

    private static final XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {

        @Override
        public HierarchicalStreamWriter createWriter(Writer writer) {
            return new JsonWriter(writer, AbstractJsonWriter.DROP_ROOT_MODE);
        }
    });

    static {
        xstream.setMode(XStream.NO_REFERENCES);
    }

    /**
     * HTTP method that given a {@link RouteRequest} returns the {@link RouteResponse} calculated
     *
     * @param routeRequestAsString
     * @return {@link RouteResponse} calculated
     */
    @POST
    @Path("/solve")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMsg(String routeRequestAsString) {
        RouteRequest routeRequest = (RouteRequest) xstream.fromXML(routeRequestAsString);
        Solver solver = new Solver(routeRequest);
        return Response.status(200).entity(solver.solve()).build();
    }

}
