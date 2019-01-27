#Weather App

This is a weather app that provides today and the following day weather (real data)

###Running the project

- This is a MAVEN spring boot project.
- Import the maven project into your favorite IDE 
- Start the spring boot server from command line or IDE and navigate to http://localhost:8080

### Implementation details

####UI
- A webpage that display the weather from two REST End Points.

####REST API
- There are two REST API's

##### First End Point - http://localhost:8080/api/day/zipcode
#####  Gives hourly weather forecast for a particular day and zipcode

Eg: http://localhost:8080/api/1/28217

- day - Valid values are 0 (today), 1 (tomorrow) 
- zipCode - Valid US Zip Code
- Data returned by this API is real and comes from weatherbit API.

##### Second End Point - http://localhost:8080/api/day/zipcode/lowest
#####  Gives lowest temperature for a particular day and zipcode

Eg: http://localhost:8080/api/1/28217/lowest

- day - Valid values are 0 (today), 1 (tomorrow) 
- zipCode - Valid US Zip Code
- Data returned by this API is real and comes from weatherbit API.

####Testing
- I have written two JUnit test cases for both of these End Points using Mockito to mock.
- They can be found in *AssignmentApplicationTests.java* class.


