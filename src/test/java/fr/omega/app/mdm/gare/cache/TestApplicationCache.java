package fr.omega.app.mdm.gare.cache;

import org.junit.Test;

import fr.omega.app.mdm.gare.data.Position;
import fr.omega.app.mdm.gare.helper.DataHelper;
import fr.omega.app.mdm.gare.cache.ApplicationCache;

import static org.junit.Assert.*;

public class TestApplicationCache {

	/*
	 * Only for test purpose, this bean must picked from the spring context This
	 * bean is declared as singleton
	 */
	private final static ApplicationCache _cache = new ApplicationCache();

	@Test
	public void testInit() {
		// Lazy cache with only three applications
		assertTrue(_cache.mappingCount() == 3);

		// Check that mandatory applications exists on the cache
		assertTrue(_cache.containsKey(1));
		assertTrue(_cache.containsKey(2));
		assertTrue(_cache.containsKey(3));
	}

	@Test
	public void testGetApplication() {
		assertTrue(_cache.get(null) == null);
		assertTrue(_cache.get(1).getName().equals("Application 1"));
		assertTrue(_cache.get(2).getName().equals("Application 2"));
		assertTrue(_cache.get(3).getName().equals("Application 3"));
	}

	@Test
	public void testPutIntegerApplication() {
		// 1
		assertTrue(_cache.put(null, null) == null);

		// 2
		final Integer id = 1;
		final String name = "Application 4";
		final String description = "Description Application 4";
		final Position position = Position.FRONTOFFICE;

		_cache.put(4, DataHelper.createApplicationFrom(id, name, description, position));

		assertTrue(_cache.get(4).getId().intValue() == id);
		assertTrue(_cache.get(4).getName().equals(name));
		assertTrue(_cache.get(4).getDescription().equals(description));
		assertTrue(_cache.get(4).getPosition().equals(position));
	}

}
