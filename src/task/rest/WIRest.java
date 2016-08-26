package task.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import task.services.WIService;


@Path("wi")
public class WIRest extends UtilRest {
	
	public WIRest(){
		
	}
	

	WIService wis = new WIService();

	@GET
	@Path("getWIs/{id}")
	@Produces("text/plain")
	public Response getIncomes(@PathParam("id") int id) {
		try {
			return this.buildResponse(wis.getWIs(id));
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
}
