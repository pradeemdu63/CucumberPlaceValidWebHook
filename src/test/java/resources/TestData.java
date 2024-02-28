package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlacePOJO;
import POJO.locationPOJO;

public class TestData {

	public AddPlacePOJO addPlace(String name,String language,String address) {
		
	AddPlacePOJO p =new AddPlacePOJO();
	p.setAccuracy(50);
	p.setName(name);
	p.setLanguage(language);
	p.setWebsite("http://google.com");
	List<String> mylist = new ArrayList<String>();
	mylist.add("shoe park");
	mylist.add("park");
	p.setTypes(mylist);
	p.setPhoneNumber("(+91) 983 893 3937");
	p.setAddress(address);
	locationPOJO ln = new locationPOJO();
	ln.setLat(-38.383494);
	ln.setLng(33.427362);
	p.setLocation(ln);
	return p;
	}
	
	public String deletePayload(String placeId) {
		return("{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "");
	}
}
