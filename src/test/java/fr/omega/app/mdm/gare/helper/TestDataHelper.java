package fr.omega.app.mdm.gare.helper;

import static org.junit.Assert.*;
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
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testCreateApplicationFromNullName() {
		try {
			@SuppressWarnings("unused")
			final Application app = DataHelper.createApplicationFrom(1, null, null, null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testCreateApplicationFromGoodParams() {
		final Integer id = 1;
		final String name = "gare";
		final String description = "gare description";
		final Position position = Position.BACKOFFICE;

		final Application app = DataHelper.createApplicationFrom(id, name, description, position);

		assertTrue(app.getId().intValue() == id.intValue());
		assertTrue(app.getName().equals(name));
		assertTrue(app.getDescription().equals(description));
		assertTrue(app.getPosition() == Position.BACKOFFICE);
	}
}
