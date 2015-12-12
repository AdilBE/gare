package fr.omega.app.mdm.gare.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import fr.omega.app.mdm.gare.cache.ApplicationCache;
import fr.omega.app.mdm.gare.data.Application;

/**
 * Application API
 * 
 * @author Adil BENDOULA, Global GECD Architecture
 *
 */

@Path("/application-api/")
@Api(value = "/application-api", description = "All allowed operations about applications ")
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationCache cache;

	@GET
	@Path("/applications/{id:[0-9]*}/")
	@Produces({ "application/xml", "application/json" })
	@ApiOperation(value = "Find application by ID", notes = "Look in GARE data base an application by id", response = Application.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Application ID exists"),
			@ApiResponse(code = 204, message = "Application not found"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	public Response getApplication(
			@ApiParam(value = "Application Id", required = true) @PathParam("id") Integer appId) {
		
		if (StringUtils.isEmpty(appId)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		final Application app = cache.get(appId);
		if (StringUtils.isEmpty(app)) {
			return Response.noContent().build();
		}
		return Response.ok(app).build();
	}

	public void setCache(final ApplicationCache cache) {
		this.cache = cache;
	}

	@POST
	@Path("/application/")
	@Consumes({ "application/xml", "application/json" })
	@ApiOperation(value = "Add an new Application")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Application created"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	public Response addApplication(
			@ApiParam(value = "A new Application object that needs to be added", required = true) Application app) {

		if (StringUtils.isEmpty(app.getId())) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		cache.put(app.getId(), app);

		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("/application/")
	@Consumes({ "application/xml", "application/json" })
	@ApiOperation(value = "Update an existing Application")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Application updated"),
			@ApiResponse(code = 204, message = "Application not found"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	public Response updateApplication(
			@ApiParam(value = "Application object that needs to be updated", required = true) Application app) {

		
		if (StringUtils.isEmpty(app.getId())) {
			return Response.noContent().build();
		}

		cache.put(app.getId(), app);
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("/applications/{id:[0-9]*}/")
	@ApiOperation(value = "Delete an application by ID", notes = "Delete an application by id from GARE")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Application is deleted"),
			@ApiResponse(code = 204, message = "Application not found"),
			@ApiResponse(code = 404, message = "Application ID not found"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	public Response delApplication(@PathParam("id") Integer appId) {

		final Application app = cache.get(appId);

		if (StringUtils.isEmpty(app)) {
			return Response.noContent().build();
		}

		cache.remove(appId);

		return Response.status(Status.CREATED).build();

	}

}
