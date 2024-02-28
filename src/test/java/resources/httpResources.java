package resources;

public enum httpResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	//For assigning the resource from above
	httpResources(String resource) {
		
		this.resource = resource;
	
	}
	//For returning the resource which was assigned in constructor
	public String getResource() {
		return resource;
	}

}
