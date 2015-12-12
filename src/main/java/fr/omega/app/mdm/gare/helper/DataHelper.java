package fr.omega.app.mdm.gare.helper;

import org.springframework.util.Assert;

import fr.omega.app.mdm.gare.data.Application;
import fr.omega.app.mdm.gare.data.Position;


/**
 * GARE Data Helper Class
 * @author Adil BENDOULA, Global GECD Architecture
 *
 */
public final class DataHelper {

	
	public static Application createApplicationFrom(
			final Integer id,
			final String name, 
			final String description, 
			final Position position) {
		
		//TODO Bean validation
		Assert.notNull(id);
		Assert.notNull(name);
		
		final Application app = new Application();
		app.setId(id);
		app.setName(name);
		app.setDescription(description);
		app.setPosition(position);
		
		return app;
	}
}
