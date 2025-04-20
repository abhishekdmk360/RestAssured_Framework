package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void addPlace() throws IOException
	{
		if(Place_API.placeID==null)
		{
			Place_API place = new Place_API();
			place.addPlacePayload("Shiv Pahar", "Hindi", "Dumka");
			place.callAPI("addPlaceAPI", "POST");
			place.verifyPlaceID_Name("Shiv Pahar", "getPlaceAPI");
		}
	}

}
