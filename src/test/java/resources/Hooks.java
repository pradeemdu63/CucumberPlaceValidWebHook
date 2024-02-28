package resources;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import stepDefinitions.stepDefs;

public class Hooks {

	@Before  
	public  void setup(Scenario scenario) {
		
		System.out.println("Execution started for - " + scenario.getName());
	}
	@Before("@DeletePlace")
	public void beforeDelete() throws IOException {
		
		stepDefs m = new stepDefs();
		if(stepDefs.placeId == null) {
		m.add_place_payload_with("CCHouse", "FRENCH", "Way Forward Street");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("CCHouse", "GET");
	///	m.in_response_body_is("status", "OK");
		}
		
	}
}
