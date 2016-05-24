package fr.omega.app.mdm.gare.helper;

import org.junit.Assert;
import org.junit.Test;

import fr.omega.app.mdm.gare.data.Application;
import fr.omega.app.mdm.gare.data.Position;

public class TestDataHelper {

	@Test
	public void testCreateApplicationFromNullId() {
		
		try {
			@SuppressWarnings("unused")
			final Application app = DataHelper.createApplicationFrom(null, null, null, null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testCreateApplicationFromNullName() {
		try {
			@SuppressWarnings("unused")
			final Application app = DataHelper.createApplicationFrom(1, null, null, null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	
	@Test
	public void testCreateApplicationFromGoodParams() {
		final Integer id = 1;
		final String name = "gare" ;
		final String description = "gare description" ;
		final Position position = Position.BACKOFFICE ;
		
		final Application app = DataHelper.createApplicationFrom(id, name, description, position);
		
		Assert.assertTrue(app.getId().intValue() == id.intValue());
		Assert.assertTrue(app.getName().equals(name));
		Assert.assertTrue(app.getDescription().equals(description));
		Assert.assertTrue(app.getPosition() == Position.BACKOFFICE);
	}
}
