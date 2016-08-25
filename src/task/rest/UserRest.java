package task.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import task.objects.User;
import task.rest.UtilRest;
import task.services.UserService;

@Path("user")
public class UserRest extends UtilRest {	
	
	public UserRest(){
		
	}
	
	UserService us = new UserService();
	
	@POST
	@Path("/add")
	@Consumes("application/*")
	@Produces("text/plain")
	public Response add(String userparams) {
		try {
			User user = new ObjectMapper().readValue(userparams, User.class);
			us.add(user);
			return this.buildResponse("User added with success!");
		} catch (Exception e) {
			return this.buildErrorResponse("Error!");
		}
	}
	
	@POST
	@Path("/getUserInfo")
	@Produces("text/plain")
	public Response getUserInfo() {
		try {
			Integer idUser = getUserId();
			return idUser == null ? null : this.buildResponse(us.getUserInfo(idUser));
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
