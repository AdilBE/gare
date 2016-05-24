package fr.omega.app.mdm.gare.cache;

import org.junit.Assert;
import org.junit.Test;

import fr.omega.app.mdm.gare.data.Position;
import fr.omega.app.mdm.gare.helper.DataHelper;

public class TestApplicationCache {
	
	/*
	 * Only for test purpose, this bean must picked from the spring context
	 * This bean is declared as singleton
	 */
	private final static ApplicationCache _cache = new ApplicationCache();

	@Test
	public void testInit() {
		// Lazy cache with only three applications 
		Assert.assertTrue(_cache.mappingCount() == 3);
		
		// Check that mandatory applications exists on the cache
		Assert.assertTrue(_cache.containsKey(1));
		Assert.assertTrue(_cache.containsKey(2));
		Assert.assertTrue(_cache.containsKey(3));
	}

	
	@Test
	public void testGetApplication() {
		Assert.assertTrue(_cache.get(null) == null);
		Assert.assertTrue(_cache.get(1).getName().equals("Application 1"));
		Assert.assertTrue(_cache.get(2).getName().equals("Application 2"));
		Assert.assertTrue(_cache.get(3).getName().equals("Application 3"));
	}
	
	@Test
	public void testPutIntegerApplication() {
		// 1
		Assert.assertTrue(_cache.put(null, null) == null);
		
		// 2
		final Integer id = 1;
		final String name = "Application 4";
		final String description = "Description Application 4";
		final Position position = Position.FRONTOFFICE;
		
		_cache.put(4, DataHelper.createApplicationFrom(id, name, description, position));
		
		Assert.assertTrue(_cache.get(4).getId().intValue() == id);
		Assert.assertTrue(_cache.get(4).getName().equals(name));
		Assert.assertTrue(_cache.get(4).getDescription().equals(description));
		Assert.assertTrue(_cache.get(4).getPosition().equals(position));
	}

}
