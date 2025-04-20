package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace place = new AddPlace();
		
		Location location = new Location();
		location.setLat(-43.383);
		location.setLng(30.983494);
		place.setLocation(location);
		
		place.setAccuracy(44);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(address);
		
		List<String> types = new ArrayList<String>();
		types.add("Biswa Bangla");
		types.add("Newtown");
		place.setTypes(types);
		
		place.setWebsite("https:restassured.com");
		place.setLanguage(language);
		
		return place;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
