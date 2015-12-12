package fr.omega.app.mdm.gare.cache;

import java.util.concurrent.ConcurrentHashMap;

import fr.omega.app.mdm.gare.data.Application;
import fr.omega.app.mdm.gare.data.Position;
import fr.omega.app.mdm.gare.helper.DataHelper;

/**
 * GARE Database Cache
 * @author Adil BENDOULA, Global GECD Architecture
 *
 */
public class ApplicationCache extends ConcurrentHashMap<Integer, Application> {

	private static final long serialVersionUID = -4556479852666540794L;
	
	public ApplicationCache() {
		super();
		init();
	}
	
	@Override
	public Application get(Object key) { 
		return (key == null) ? null : super.get(key);
	}
	
	@Override
	public Application put(Integer key, Application value) {
		return (key == null) ? null : super.put(key, value);
	}
	
	private void init(){
		put(1, DataHelper.createApplicationFrom(1, "Application 1", "Application 1 description", Position.FRONTOFFICE));
		put(2, DataHelper.createApplicationFrom(2, "Application 2", "Application 2 description", Position.FRONTOFFICE));
	}
}
