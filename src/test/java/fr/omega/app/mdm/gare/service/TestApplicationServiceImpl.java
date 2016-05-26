package fr.omega.app.mdm.gare.service;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.omega.app.mdm.gare.data.Application;
import fr.omega.app.mdm.gare.data.Position;
import fr.omega.app.mdm.gare.helper.DataHelper;

public class TestApplicationServiceImpl {

	private final static String ENDPOINT_ADDRESS = "http://localhost:8080/";
	private final static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-test.xml");
	private final static JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();

	private static Server server;

	@BeforeClass
	public static void initialize() throws Exception {
		startServer();
	}

	private static void startServer() throws Exception {
		serverFactory.setResourceClasses(Application.class);
		serverFactory.setServiceBean(context.getBean("app-api"));
		serverFactory.setAddress(ENDPOINT_ADDRESS);
		server = serverFactory.create();

	}

	@Test
	public void testGetApplicationMustResultOK() {
		WebClient client = WebClient.create(ENDPOINT_ADDRESS);
		final Response response = client.path("application-api/applications/1").accept(MediaType.APPLICATION_XML).get();

		assertTrue(response.getStatus() == 200);

		final Application app = response.readEntity(Application.class);

		assertTrue(app != null);
		assertTrue(app.getId().intValue() == 1);
		assertTrue(app.getPosition().equals(Position.FRONTOFFICE));

	}

	@Test
	public void testGetApplicationMustResultHttp204() {
		WebClient client = WebClient.create(ENDPOINT_ADDRESS);
		final Response response = client.path("application-api/applications/5").accept(MediaType.APPLICATION_XML).get();

		assertTrue(response.getStatus() == 204);

	}

	@Test
	public void testDeleteApplicationMustResulthttp201() {
		// Delete and check
		WebClient client = WebClient.create(ENDPOINT_ADDRESS).path("application-api/applications/3")
				.accept(MediaType.APPLICATION_XML);
		Response response = client.delete();
		assertTrue(response.getStatus() == 201);

		// Get the deleted application and check
		response = client.get();
		assertTrue(response.getStatus() == 204);

	}
	
	@Test
	public void testPostApplicationMustResulthttp201() {
		// Post and check
		final WebClient clientForPost = WebClient.create(ENDPOINT_ADDRESS).path("application-api/application/")
				.accept(MediaType.APPLICATION_XML);
		
		final Application app = DataHelper.createApplicationFrom(10, "Application 10", "Application 10 description", Position.BACKOFFICE);
		Response response = clientForPost.post(app);
		
		assertTrue(response.getStatus() == 201);

		// Get the deleted application and check
		final WebClient clientForGet = WebClient.create(ENDPOINT_ADDRESS).path("application-api/applications/10")
				.accept(MediaType.APPLICATION_XML);
		response = clientForGet.get();
		
		assertTrue(response.getStatus() == 200);

	}
	
	@Test
	public void testPutApplicationMustResulthttp201() {
		// Post and check
		final WebClient clientForPost = WebClient.create(ENDPOINT_ADDRESS).path("application-api/application/")
				.accept(MediaType.APPLICATION_XML);
		
		final String newName = "Change Application 1 name";
		
		final Application app = DataHelper.createApplicationFrom(1, newName, "Application 1 description", Position.FRONTOFFICE);
		Response response = clientForPost.put(app);
		
		assertTrue(response.getStatus() == 201);

		// Get the deleted application and check
		final WebClient clientForGet = WebClient.create(ENDPOINT_ADDRESS).path("application-api/applications/1")
				.accept(MediaType.APPLICATION_XML);
		response = clientForGet.get();
		
		assertTrue(response.getStatus() == 200);
		final Application appAfterUpdate = response.readEntity(Application.class);
	
		assertTrue(appAfterUpdate.getName().equals(newName));

	}

	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}

}
