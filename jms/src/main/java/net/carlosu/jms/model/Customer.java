package net.carlosu.jms.model;

public class Customer {
	private long id;
	private String name;
	private String readFolder;
	private String saveFolder;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReadFolder() {
		return readFolder;
	}
	public void setReadFolder(String readFolder) {
		this.readFolder = readFolder;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
}
