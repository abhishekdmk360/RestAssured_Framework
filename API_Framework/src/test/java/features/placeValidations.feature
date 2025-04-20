Feature: Validating Place API's

@AddPlaceAPI
Scenario Outline: Verify if Place is being added using AddPlaceAPI
	Given User has Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" with "POST" http request
	Then API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify PlaceId created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	|	name				|	language	|	address	|
	|Bisarjan Ghat|	Bengali		|	DF Block|
#	|	Shiv Pahar	|	Hindi			|	Dumka		|

@DeletePlaceAPI
Scenario: Verify if Place is being deleted using DeletePlaceAPI
	Given User has Delete Place Payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then API call is success with status code 200
	And "status" in response body is "OK"