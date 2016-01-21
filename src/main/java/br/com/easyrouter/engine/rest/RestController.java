package br.com.easyrouter.engine.rest;
 
import java.io.Writer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import br.com.easyrouter.engine.api.RouteRequest;
import br.com.easyrouter.engine.api.RouteResponse;
import br.com.easyrouter.engine.solver.Solver;
 
@Path("/hello")
public class RestController {
	
	private static final XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
	    public HierarchicalStreamWriter createWriter(Writer writer) {
	        return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
	    }
	});
	
	static {
	    xstream.setMode(XStream.NO_REFERENCES);
	}
	
	@POST
	@Path("/solve")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMsg(String routeRequestAsString) {
		RouteRequest routeRequest = (RouteRequest) xstream.fromXML(routeRequestAsString);
		
		RouteResponse routeResponse = Solver.solve(routeRequest);
		
		
		return Response.status(200).entity(routeResponse).build();
	}
 
}