package br.com.easyrouter.engine.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.easyrouter.engine.api.RouteRequest;
import br.com.easyrouter.engine.api.RouteResponse;
import br.com.easyrouter.engine.solver.Solver;

@Path("/")
public class RestController {

    private static Gson gson = new GsonBuilder()
    		.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    		.create();

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
    public String solve(String routeRequestAsString) {
        RouteRequest routeRequest = (RouteRequest) gson.fromJson(routeRequestAsString, RouteRequest.class);
        Solver solver = new Solver(routeRequest);
        return gson.toJson(solver.solve());
    }

}
