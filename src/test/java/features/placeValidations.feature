Feature: validating Place API's
@Addplace
Scenario Outline: Verify if place is being successfully added using addplace API
	Given Add place payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "Post" http request 
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI"

	Examples:
	|name			 |language		|Address		   |
#	|Frontline house |French-IN 	|29, side layout   |
	|AA House		 |Spanish		|World Trade center|

@DeletePlace 	
Scenario: Verify if Delete place API functionality is working fine

	Given Deleteplace payload 
	When User calls "DeletePlaceAPI" with "Post" http request 
	Then the API call is success with status code 200
	And "status" in response body is "OK"	