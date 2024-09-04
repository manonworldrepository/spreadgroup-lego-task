package net.sprd.homework.request;

public class Favorite {

	private String setId;
	
	public Favorite() {}
	
	public Favorite(String setId) {
		this.setId = setId;
	}
	
	public void setSetId(String setId) {
		this.setId = setId;
	}
	
	public String getSetId() {
		return setId;
	}
	
	public String getId() {
		return "fav-" + setId;
	}
	
}
