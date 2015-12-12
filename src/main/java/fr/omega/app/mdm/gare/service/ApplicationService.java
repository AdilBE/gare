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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import fr.omega.app.mdm.gare.data.Application;

/**
 * Application API Contract
 * 
 * @author Adil BENDOULA, Global GECD Architecture
 *
 */
@Api(value = "/application-api", description = "All allowed operations about applications ")
public interface ApplicationService {

	@GET
	@Path("/applications/{id:[0-9]*}/")
	@Produces({ "application/xml", "application/json" })
	@ApiOperation(value = "Find application by ID", notes = "Look in GARE data base an application by id", response = Application.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Application ID exists"),
			@ApiResponse(code = 204, message = "Application not found"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	
	public Response getApplication(@PathParam("id") Integer appId);

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
			@ApiParam(value = "Application object that needs to be updated", required = true) Application app);
	
	
	@POST
	@Path("/application/")
	@Consumes({ "application/xml", "application/json" })
	@ApiOperation(value = "Add an new Application")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Application created"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	
	public Response addApplication(
			@ApiParam(value = "A new Application object that needs to be added", required = true) Application app) ;
	
	
	@DELETE
	@Path("/applications/{id:[0-9]*}/")
	@ApiOperation(value = "Delete an application by ID", notes = "Delete an application by id from GARE")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Application is deleted"),
			@ApiResponse(code = 204, message = "Application not found"),
			@ApiResponse(code = 404, message = "Application ID not found"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 500, message = "Unhandled Error") })
	
	public Response delApplication(@PathParam("id") Integer appId);
}
