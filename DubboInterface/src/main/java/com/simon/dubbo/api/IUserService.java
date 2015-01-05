package com.simon.dubbo.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.simon.dubbo.bean.User;

/**
 * http://{ip}:{$port}/{contextpath}/users/register
 */
@Path("/users")
public interface IUserService {

//	@POST
//	@Path("/register")
//	@Consumes({MediaType.APPLICATION_JSON})
	void register(User user);
	
//	@GET
//	@Path("{id : \\d+}")
//	@Produces({MediaType.APPLICATION_JSON})
	User getUser(@PathParam("id") Long id, @Context HttpServletRequest request);
	
}
