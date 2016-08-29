package task.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import task.objects.Work_Item;
import task.services.WIService;


@Path("wi")
public class WIRest extends UtilRest {
	
	public WIRest(){
		
	}
	
	WIService wis = new WIService();

	@GET
	@Path("getWIs/")
	@Produces("text/plain")
	public Response getWIs(@QueryParam("id") int id, @QueryParam("status") int status, @QueryParam("showAll") boolean showAll) {
		try {
			return this.buildResponse(wis.getWIs(id, getUserId(), status, showAll));
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@POST
	@Path("/add")
	@Consumes("application/*")
	@Produces("text/plain")
	public Response add(String wiparams) {
		try {
			Work_Item wi = new ObjectMapper().readValue(wiparams, Work_Item.class);
			wis.add(wi);
			return this.buildResponse("Work item added with success!");
		} catch (Exception e) {
			return this.buildErrorResponse("Error!");
		}
	}
	
	@GET
	@Path("getWILogs/{id}")
	@Produces("text/plain")
	public Response getWILogs(@PathParam("id") int id) {
		try {
			return this.buildResponse(wis.getWILogs(id));
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
}
