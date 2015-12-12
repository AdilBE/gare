package fr.omega.app.mdm.gare.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Java application definition
 * @author Adil BENDOULA, Global GECD Architecture
 *
**/

@XmlRootElement(name = "Application")
public class Application {

	private Integer id;
	private String name ;
	private String description ;
	private String programManager ;
	private Position position ;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setProgramManager(String programManager) {
		this.programManager = programManager;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getProgramManager() {
		return programManager;
	}
	
	public Position getPosition() {
		return position;
	}
}
